package com.github.danimaniarqsoft.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Link} entity.
 */
public class LinkDTO implements Serializable {

    private String id;

    @NotNull
    private String url;


    private String mensajeErrorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMensajeErrorId() {
        return mensajeErrorId;
    }

    public void setMensajeErrorId(String mensajeErrorId) {
        this.mensajeErrorId = mensajeErrorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinkDTO linkDTO = (LinkDTO) o;
        if (linkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), linkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LinkDTO{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", mensajeError=" + getMensajeErrorId() +
            "}";
    }
}
