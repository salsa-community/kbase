package com.github.danimaniarqsoft.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import com.github.danimaniarqsoft.domain.enumeration.EstadoContacto;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Contacto} entity.
 */
public class ContactoDTO implements Serializable {

    private String id;

    private String context;

    private String correo;

    private String rfc;

    private EstadoContacto estado;

    private String desc;

    private LocalDate lastModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public EstadoContacto getEstado() {
        return estado;
    }

    public void setEstado(EstadoContacto estado) {
        this.estado = estado;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactoDTO contactoDTO = (ContactoDTO) o;
        if (contactoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactoDTO{" +
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
