package com.github.danimaniarqsoft.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UnknowIntents.
 */
@Document(collection = "unknowIntents")
public class UnknowIntents implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("word")
    private String word;

    @Field("last_modified")
    private LocalDate lastModified;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public UnknowIntents word(String word) {
        this.word = word;
        return this;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public UnknowIntents lastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnknowIntents)) {
            return false;
        }
        return id != null && id.equals(((UnknowIntents) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UnknowIntents{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
