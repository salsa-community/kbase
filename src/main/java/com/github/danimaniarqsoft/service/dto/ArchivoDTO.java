package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Archivo} entity.
 */
public class ArchivoDTO implements Serializable {

    private String id;

    private String nombre;

    private byte[] dato;

    private String datoContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getDato() {
        return dato;
    }

    public void setDato(byte[] dato) {
        this.dato = dato;
    }

    public String getDatoContentType() {
        return datoContentType;
    }

    public void setDatoContentType(String datoContentType) {
        this.datoContentType = datoContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArchivoDTO archivoDTO = (ArchivoDTO) o;
        if (archivoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), archivoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ArchivoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", dato='" + getDato() + "'" +
            "}";
    }
}
