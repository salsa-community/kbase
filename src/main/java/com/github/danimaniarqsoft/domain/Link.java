package com.github.danimaniarqsoft.domain;

import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Link.
 */
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Field("url")
    private String url;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public String getUrl() {
        return url;
    }

    public Link url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Link)) {
            return false;
        }
        return url != null && url.equals(((Link) o).url);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Link{" + " url='" + getUrl() + "'" + "}";
    }
}
