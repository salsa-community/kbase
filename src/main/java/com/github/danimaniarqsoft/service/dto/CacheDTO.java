package com.github.danimaniarqsoft.service.dto;

public class CacheDTO {
 
    private String clave;

    private String type;

    public CacheDTO(String type) {
        this.type = type;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(final String clave) {
        this.clave = clave;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
