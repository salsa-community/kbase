package com.github.danimaniarqsoft.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.MensajeError} entity.
 */
public class MensajeErrorDTO implements Serializable {

    private String id;

    private String clave;

    private String desc;

    private String instruccionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getInstruccionId() {
        return instruccionId;
    }

    public void setInstruccionId(String instruccionId) {
        this.instruccionId = instruccionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MensajeErrorDTO mensajeErrorDTO = (MensajeErrorDTO) o;
        if (mensajeErrorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mensajeErrorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MensajeErrorDTO{" +
            "id=" + getId() +
            ", clave='" + getClave() + "'" +
            ", desc='" + getDesc() + "'" +
            ", instruccion=" + getInstruccionId() +
            "}";
    }
}
