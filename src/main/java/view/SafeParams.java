package view;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

public class SafeParams {

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public static final String DEFAULT_DATA_PARAM = "d";

    private static final String DEFAULT_TIMESTAMP = "param_timestamp";

    private static Gson GSON = new Gson();

    private Map<String, Object> parameters = new HashMap<String, Object>();

    public SafeParams() {
    }

    public SafeParams(String encryptedJson) {
        fromJson(encryptedJson);
    }

    public SafeParams(Map<? extends String, ? extends Object> values) {
        putAll(values);
    }

    public SafeParams(String key, Object value) {
        put(key, value);
    }

    public SafeParams putAll(Map<? extends String, ? extends Object> values) {
        parameters.putAll(values);
        return this;
    }

    public SafeParams put(String key, Object value) {
        if (value != null && value instanceof Boolean)
            if (!(Boolean) value)
                return this;
        parameters.put(key, value);
        return this;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) parameters.get(key);
    }

    public Integer getInteger(String key) {
        final Object val = parameters.get(key);
        if (val != null && val instanceof Number) {
            return ((Number) val).intValue();
        }
        return null;
    }

    public Long getLong(String key) {
        final Object val = parameters.get(key);
        if (val != null && val instanceof Number) {
            return ((Number) val).longValue();
        }
        return null;
    }

    public Long parseLong(String key) {
        Object object = parameters.get(key);
        if (object != null) {
            if (object instanceof Number) {
                return ((Number) object).longValue();
            }
            return Long.parseLong(object.toString());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, T defaultValue) {
        final T v = (T) parameters.get(key);
        if (v == null)
            return defaultValue;
        return v;
    }

    public <T> T getOrDie(String key) {
        final T object = get(key);
        if (object == null)
            throw new RuntimeException("O parâmetro " + key + " é obrigatório.");
        return object;
    }

    public String toJson() throws RuntimeException {
        return toJson(DEFAULT_DATA_PARAM);
    }

    public String toJson(String paramName) throws RuntimeException {
        try {
            paramName = paramName != null ? paramName + "=" : "";
            appendTimestamp();
            final String json = GSON.toJson(parameters);
            return paramName + codificaBase64Encoder(json);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void appendTimestamp() {
        parameters.put(DEFAULT_TIMESTAMP, formatter.format(new Date()));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public SafeParams fromJson(String encryptedJson) throws RuntimeException {
        try {
            parameters.clear();
            if(StringUtils.isEmpty(encryptedJson)) {
                return this;
            }
            HashMap fromJson = GSON.fromJson(decodificaBase64Decoder(encryptedJson), HashMap.class);
            if (fromJson != null) {
                parameters.putAll(fromJson);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return this;
    }

    public Date getTimestamp() {
        final String ts = (String) parameters.get(DEFAULT_TIMESTAMP);
        if (StringUtils.isEmpty(ts)) {
            return null;
        }
        try {
            return formatter.parse(ts);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return toJson();
    }
    
    	public static String codificaBase64Encoder(String str) {
            return new Base64().encodeToString(str.getBytes());
        }
        /** credito para alura
         * @brief DesCodifica string na base 64 (Decoder)
         * @param string ofuscada - string pra à ser decodificada
         * @return string desofuscada
         * @date 12/04/2012
         * @see codificaBase64Encoder(String str) Encoder [lib apache commons codec]
         */
        public static String decodificaBase64Decoder(String str) {
            return new String(new Base64().decode(str));
        }
    
}
