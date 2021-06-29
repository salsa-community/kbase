package com.github.danimaniarqsoft.domain;

import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;

/**
 * Mensaje.
 */
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("orden")
    private Integer orden;

    @Field("desc")
    private String desc;

    @Field("descEn")
    private String descEn;

    public Integer getOrden() {
        return orden;
    }

    public Mensaje orden(Integer orden) {
        this.orden = orden;
        return this;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getDesc() {
        return desc;
    }

    public Mensaje desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getDescEn() {
        return descEn;
    }

    public Mensaje descEn(String descEn) {
        this.descEn = descEn;
        return this;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mensaje)) {
            return false;
        }
        return orden != null && orden.equals(((Mensaje) o).orden);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "orden=" + getOrden() + ", desc='" + getDesc() + "'" + "}";
    }
}
