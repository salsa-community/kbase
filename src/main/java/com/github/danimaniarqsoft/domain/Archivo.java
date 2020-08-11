package com.github.danimaniarqsoft.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Archivo.
 */
@Document(collection = "archivo")
public class Archivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("dato")
    private byte[] dato;

    @Field("dato_content_type")
    private String datoContentType;

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

    public Archivo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getDato() {
        return dato;
    }

    public Archivo dato(byte[] dato) {
        this.dato = dato;
        return this;
    }

    public void setDato(byte[] dato) {
        this.dato = dato;
    }

    public String getDatoContentType() {
        return datoContentType;
    }

    public Archivo datoContentType(String datoContentType) {
        this.datoContentType = datoContentType;
        return this;
    }

    public void setDatoContentType(String datoContentType) {
        this.datoContentType = datoContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Archivo)) {
            return false;
        }
        return id != null && id.equals(((Archivo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Archivo{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", dato='" + getDato() + "'" +
            ", datoContentType='" + getDatoContentType() + "'" +
            "}";
    }
}
