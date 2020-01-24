package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Instruccion} entity.
 */
public class InstruccionDTO implements Serializable {

    private String id;

    private String desc;


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

        InstruccionDTO instruccionDTO = (InstruccionDTO) o;
        if (instruccionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instruccionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstruccionDTO{" +
            "id=" + getId() +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
