package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.danimaniarqsoft.domain.Mensaje;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Contexto} entity.
 */
public class ContextoDTO implements Serializable {

    private String id;

    private String nombre;

    private String clave;

    private String desc;

    private String descEn;

    private String organizacion;

    private List<MensajeDTO> mensajes = new ArrayList<>();

    public List<MensajeDTO> getMensajes() {
        return mensajes;
    }

    public ContextoDTO mensajes(List<MensajeDTO> mensajes) {
        this.mensajes = mensajes;
        return this;
    }

    public ContextoDTO addMensajes(MensajeDTO mensaje) {
        this.mensajes.add(mensaje);
        return this;
    }

    public ContextoDTO removeMensaje(MensajeDTO mensaje) {
        this.mensajes.remove(mensaje);
         return this;
    }

    public void setMensajes(List<MensajeDTO> mensajes) {
        this.mensajes = mensajes;
    }
    
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDesc() {
        return desc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContextoDTO contextoDTO = (ContextoDTO) o;
        if (contextoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contextoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContextoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", clave='" + getClave() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
