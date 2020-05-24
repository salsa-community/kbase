package com.github.danimaniarqsoft.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.UnknowIntents} entity.
 */
public class UnknowIntentsDTO implements Serializable {

    private String id;

    private String word;

    private LocalDate lastModified;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
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

        UnknowIntentsDTO unknowIntentsDTO = (UnknowIntentsDTO) o;
        if (unknowIntentsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unknowIntentsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UnknowIntentsDTO{" +
            "id=" + getId() +
            ", word='" + getWord() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
