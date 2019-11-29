package com.github.danimaniarqsoft.domain;

import org.springframework.data.mongodb.core.mapping.Field;
import java.io.Serializable;

/**
 * A Paso.
 */
public class Paso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Field("paso")
    private Integer paso;

    @Field("desc")
    private String desc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Integer getPaso() {
        return paso;
    }

    public Paso paso(Integer paso) {
        this.paso = paso;
        return this;
    }

    public void setPaso(Integer paso) {
        this.paso = paso;
    }

    public String getDesc() {
        return desc;
    }

    public Paso desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Paso)) {
            return false;
        }
        return paso != null && paso.equals(((Paso) o).paso);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Paso{" + "paso=" + getPaso() + ", desc='" + getDesc() + "'" + "}";
    }
}
