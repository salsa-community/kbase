package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Contexto} entity.
 */
public class ContextoDTO implements Serializable {

    private String id;

    private String nombre;

    private String clave;

    private String desc;

    private String descEn;

    private String objetivo;

    private String objetivoEn;

    private String organizacion;

    private String loginMessage;

    private String loginMessageEn;

    private String welcomeMessage;
    
    private String welcomeMessageEn;

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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObjetivoEn() {
        return objetivoEn;
    }

    public void setObjetivoEn(String objetivoEn) {
        this.objetivoEn = objetivoEn;
    }

    public String getOrganizacion() {
        return organizacion;
    }

        
    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    public String getLoginMessageEn() {
        return loginMessageEn;
    }

    public void setLoginMessageEn(String loginMessageEn) {
        this.loginMessageEn = loginMessageEn;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getWelcomeMessageEn() {
        return welcomeMessageEn;
    }

    public void setWelcomeMessageEn(String welcomeMessageEn) {
        this.welcomeMessageEn = welcomeMessageEn;
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
