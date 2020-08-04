package com.github.danimaniarqsoft.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Actividad.
 */
@Document(collection = "actividades")
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("contexto")
    private String contexto;

    @Field("valor")
    private String valor;

    @Field("desc")
    private String desc;

    @Field("fecha")
    private Instant fecha;

    @Field("evento")
    private String evento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContexto() {
        return contexto;
    }

    public Actividad contexto(String contexto) {
        this.contexto = contexto;
        return this;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getValor() {
        return valor;
    }

    public Actividad valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDesc() {
        return desc;
    }

    public Actividad desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Instant getFecha() {
        return fecha;
    }

    public Actividad fecha(Instant fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public String getEvento() {
        return evento;
    }

    public Actividad evento(String evento) {
        this.evento = evento;
        return this;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Actividad)) {
            return false;
        }
        return id != null && id.equals(((Actividad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Actividad{" +
            "id=" + getId() +
            ", contexto='" + getContexto() + "'" +
            ", valor='" + getValor() + "'" +
            ", desc='" + getDesc() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
