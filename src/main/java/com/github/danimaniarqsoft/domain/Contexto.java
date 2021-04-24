package com.github.danimaniarqsoft.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Contexto.
 */
@Document(collection = "contexto")
public class Contexto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("clave")
    private String clave;

    @Field("desc")
    private String desc;

    @Field("descEn")
    private String descEn;

    @Field("objetivo")
    private String objetivo;

    @Field("objetivoEn")
    private String objetivoEn;

    @Field("organizacion")
    private String organizacion;

    @Field("loginMessage")
    private String loginMessage;

    @Field("loginMessageEn")
    private String loginMessageEn;
    
    @Field("welcomeMessage")
    private String welcomeMessage;
    
    @Field("welcomeMessageEn")
    private String welcomeMessageEn;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Contexto nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public Contexto clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDesc() {
        return desc;
    }

    public Contexto desc(String desc) {
        this.desc = desc;
        return this;
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

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contexto)) {
            return false;
        }
        return id != null && id.equals(((Contexto) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Contexto{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", clave='" + getClave() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
