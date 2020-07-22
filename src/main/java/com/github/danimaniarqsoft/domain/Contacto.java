package com.github.danimaniarqsoft.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

import com.github.danimaniarqsoft.domain.enumeration.EstadoContacto;

/**
 * A Contacto.
 */
@Document(collection = "contactos")
public class Contacto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("context")
    private String context;

    @Field("correo")
    private String correo;

    @Field("rfc")
    private String rfc;

    @Field("estado")
    private EstadoContacto estado;

    @Field("desc")
    private String desc;

    @Field("last_modified")
    private LocalDate lastModified;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public Contacto context(String context) {
        this.context = context;
        return this;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCorreo() {
        return correo;
    }

    public Contacto correo(String correo) {
        this.correo = correo;
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public Contacto rfc(String rfc) {
        this.rfc = rfc;
        return this;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public EstadoContacto getEstado() {
        return estado;
    }

    public Contacto estado(EstadoContacto estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(EstadoContacto estado) {
        this.estado = estado;
    }

    public String getDesc() {
        return desc;
    }

    public Contacto desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public Contacto lastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contacto)) {
            return false;
        }
        return id != null && id.equals(((Contacto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Contacto{" +
            "id=" + getId() +
            ", context='" + getContext() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", rfc='" + getRfc() + "'" +
            ", estado='" + getEstado() + "'" +
            ", desc='" + getDesc() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
