package com.github.danimaniarqsoft.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A MensajeError.
 */
@Document(collection = "errores")
public class MensajeError implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("clave")
    private String clave;

    @NotNull
    @Field("desc")
    private String desc;

    @Field("descEn")
    private String descEn;

    @Field("instrucciones")
    private Instruccion instruccion;

    @Field("links")
    private List<Link> links = new ArrayList<>();

    @Field("contextos")
    private List<String> contextos = new ArrayList<>();

    @Field("orden")
    private Integer orden;

    @NotNull
    @Field("tipo")
    private String tipo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public MensajeError clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDesc() {
        return desc;
    }

    public MensajeError desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Instruccion getInstruccion() {
        return instruccion;
    }

    public MensajeError instruccion(Instruccion instruccion) {
        this.instruccion = instruccion;
        return this;
    }

    public void setInstruccion(Instruccion instruccion) {
        this.instruccion = instruccion;
    }

    public List<Link> getLinks() {
        return links;
    }

    public MensajeError links(List<Link> links) {
        this.links = links;
        return this;
    }

    public MensajeError addLinks(Link link) {
        this.links.add(link);
        return this;
    }

    public MensajeError removeLinks(Link link) {
        this.links.remove(link);
        return this;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public MensajeError addContextos(String string) {
        this.contextos.add(string);
        return this;
    }

    public List<String> getContextos() {
        return contextos;
    }

    public void setContextos(List<String> contextos) {
        this.contextos = contextos;
    }

    	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public String getTipo() {
		return tipo;
	}

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MensajeError)) {
            return false;
        }
        return id != null && id.equals(((MensajeError) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MensajeError{" + "id=" + getId() + ", clave='" + getClave() + "'" + ", desc='" + getDesc() + "'"
                + ", orden='" + getOrden() + "'" + ", tipo='" + getTipo() + "'" + "}";
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }
    
}
