package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Evento} entity.
 */
public class EventoDTO implements Serializable {

    private String id;

    private String nombre;

    private String desc;


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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EventoDTO eventoDTO = (EventoDTO) o;
        if (eventoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), eventoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EventoDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
