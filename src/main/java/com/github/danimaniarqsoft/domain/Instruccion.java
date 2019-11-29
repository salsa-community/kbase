package com.github.danimaniarqsoft.domain;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Instruccion.
 */
public class Instruccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("desc")
    private String desc;

    @Field("pasos")
    private Set<Paso> pasos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getDesc() {
        return desc;
    }

    public Instruccion desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<Paso> getPasos() {
        return pasos;
    }

    public Instruccion pasos(Set<Paso> pasos) {
        this.pasos = pasos;
        return this;
    }

    public Instruccion addPasos(Paso paso) {
        this.pasos.add(paso);
        return this;
    }

    public Instruccion removePasos(Paso paso) {
        this.pasos.remove(paso);
         return this;
    }

    public void setPasos(Set<Paso> pasos) {
        this.pasos = pasos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public String toString() {
        return "Instruccion{" +
            "desc='" + getDesc() + "'" +
            "}";
    }
}
