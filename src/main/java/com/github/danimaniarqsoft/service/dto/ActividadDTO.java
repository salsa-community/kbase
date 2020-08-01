package com.github.danimaniarqsoft.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Actividad} entity.
 */
public class ActividadDTO implements Serializable {

    private String id;

    private String contexto;

    private String valor;

    private String desc;

    private Instant fecha;


    private String eventoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    public String getEventoId() {
        return eventoId;
    }

    public void setEventoId(String eventoId) {
        this.eventoId = eventoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActividadDTO actividadDTO = (ActividadDTO) o;
        if (actividadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), actividadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActividadDTO{" +
            "id=" + getId() +
            ", contexto='" + getContexto() + "'" +
            ", valor='" + getValor() + "'" +
            ", desc='" + getDesc() + "'" +
            ", fecha='" + getFecha() + "'" +
            ", evento=" + getEventoId() +
            "}";
    }
}
