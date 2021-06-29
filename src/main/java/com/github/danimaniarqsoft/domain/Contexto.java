package com.github.danimaniarqsoft.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Contexto.
 */
@Document(collection = "contexto")
public class Contexto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Indexed
    @Field("clave")
    private String clave;

    @Field("desc")
    private String desc;

    @Field("descEn")
    private String descEn;

    @Field("organizacion")
    private String organizacion;

    @Field("mensajes")
    private List<Mensaje> mensajes = new ArrayList<>();

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public Contexto mensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
        return this;
    }

    public Contexto addMensajes(Mensaje mensaje) {
        this.mensajes.add(mensaje);
        return this;
    }

    public Contexto removeMensaje(Mensaje mensaje) {
        this.mensajes.remove(mensaje);
         return this;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Contexto nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public Contexto clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDesc() {
        return desc;
    }

    public Contexto desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contexto)) {
            return false;
        }
        return id != null && id.equals(((Contexto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Contexto{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", clave='" + getClave() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
