package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Actividad} entity.
 */
public class ReporteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String topCodigoError;

    private String countCodigoError;

    private String numContactos;

	public String getTopCodigoError() {
		return topCodigoError;
	}

	public void setTopCodigoError(String topCodigoError) {
		this.topCodigoError = topCodigoError;
	}

	public String getCountCodigoError() {
		return countCodigoError;
	}

	public void setCountCodigoError(String countCodigoError) {
		this.countCodigoError = countCodigoError;
	}

	public String getNumContactos() {
		return numContactos;
	}

	public void setNumContactos(String numContactos) {
		this.numContactos = numContactos;
	}

}
