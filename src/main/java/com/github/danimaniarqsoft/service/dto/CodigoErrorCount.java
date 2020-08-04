package com.github.danimaniarqsoft.service.dto;
import java.io.Serializable;

/**
 * A DTO for the {@link com.github.danimaniarqsoft.domain.Actividad} entity.
 */
public class CodigoErrorCount implements Serializable {

	private static final long serialVersionUID = 1L;

	private String valor;

	private long total;

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}


}
