
package com.github.danimaniarqsoft.migration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "_id",
    "clave",
    "desc",
    "instrucciones",
    "links"
})
public class ErrorMigration {

    @JsonProperty("_id")
    private Id id;
    @JsonProperty("clave")
    private String clave;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("instrucciones")
    private Instrucciones instrucciones;
    @JsonProperty("links")
    private List<Link> links = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_id")
    public Id getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("clave")
    public String getClave() {
        return clave;
    }

    @JsonProperty("clave")
    public void setClave(String clave) {
        this.clave = clave;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("instrucciones")
    public Instrucciones getInstrucciones() {
        return instrucciones;
    }

    @JsonProperty("instrucciones")
    public void setInstrucciones(Instrucciones instrucciones) {
        this.instrucciones = instrucciones;
    }

    @JsonProperty("links")
    public List<Link> getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
