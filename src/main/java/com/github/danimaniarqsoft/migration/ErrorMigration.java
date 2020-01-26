
package com.github.danimaniarqsoft.migration;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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

}
