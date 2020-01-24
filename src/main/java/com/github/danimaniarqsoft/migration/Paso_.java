
package com.github.danimaniarqsoft.migration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "$numberInt"
})
public class Paso_ {

    @JsonProperty("$numberInt")
    private String $numberInt;

    @JsonProperty("$numberInt")
    public String get$numberInt() {
        return $numberInt;
    }

    @JsonProperty("$numberInt")
    public void set$numberInt(String $numberInt) {
        this.$numberInt = $numberInt;
    }

}
