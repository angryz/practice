package spring.gs.restconsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by zzp on 7/25/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    private String type;
    private Value value;

    public String getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
