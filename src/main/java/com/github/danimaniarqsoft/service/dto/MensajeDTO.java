package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Mensaje} entity.
 */
public class MensajeDTO implements Serializable {

    private String id;

    private Integer orden;

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    private String desc;

    private String descEn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

        PasoDTO pasoDTO = (PasoDTO) o;
        if (pasoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pasoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PasoDTO{" +
            "id=" + getId() +
            ", orden=" + getOrden() +
            ", desc='" + getDesc() + "'" +
            "}";
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }
}
