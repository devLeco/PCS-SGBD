/*
 * utf-8
 */
package view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TelaInicialPesquisarEndereco extends SelectorComposer<Window> implements Serializable {

    private static final long serialVersionUID = 1L;
//    private static final String URL = "http://localhost:8080/delirandodefome-TT/delirandodefome.zul";
    private static final String URL = "http://localhost:8080/delirando-de-fome/delirandodefome.zul";

    private String localizacao;
    public Map<String, String> map = new HashMap<String, String>();

    @Init
    public void init() {
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Command
    public void pesquisarProximo() {
        Executions.sendRedirect(URL + "?" + new SafeParams("local", this.getLocalizacao()));
    }


    @Wire
    private Div locationContainer;

    @Listen("onLocation=#locationContainer")
    public void pegarCoordenadasNavegador(Event event) {
        Map<String, Object> data = (Map<String, Object>) event.getData();

        map.put("latitude", data.get("latitude").toString());
        map.put("longitude", data.get("longitude").toString());

        Executions.sendRedirect(URL + "?" + new SafeParams("coordenadas", map));
    }

}
