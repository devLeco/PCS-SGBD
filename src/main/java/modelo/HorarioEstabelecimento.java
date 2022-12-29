package modelo;

public class HorarioEstabelecimento {

    private Integer dia;
    private String isOvernight;
    private Integer hrInicio;
    private Integer hrFim;
    
    public Integer getDia() {
        return dia;
    }
    public void setDia(Integer dia) {
        this.dia = dia;
    }
    public String getIsOvernight() {
        return isOvernight;
    }
    public void setIsOvernight(String isOvernight) {
        if("false".equals(isOvernight)){
            this.isOvernight = "Não";
        }else{
            this.isOvernight = "Sim";
        }
        this.isOvernight = isOvernight;
    }
    public Integer getHrInicio() {
        return hrInicio;
    }
    public void setHrInicio(Integer hrInicio) {
        this.hrInicio = hrInicio;
    }
    public Integer getHrFim() {
        return hrFim;
    }
    public void setHrFim(Integer hrFim) {
        this.hrFim = hrFim;
    };
    
    
    
}
