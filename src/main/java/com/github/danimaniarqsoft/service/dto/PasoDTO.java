package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Paso} entity.
 */
public class PasoDTO implements Serializable {

    private String id;

    private Integer paso;

    private String desc;

    private String descEn;

    private String instruccionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPaso() {
        return paso;
    }

    public void setPaso(Integer paso) {
        this.paso = paso;
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
            ", paso=" + getPaso() +
            ", desc='" + getDesc() + "'" +
            ", instruccion=" + getInstruccionId() +
            "}";
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }
}
