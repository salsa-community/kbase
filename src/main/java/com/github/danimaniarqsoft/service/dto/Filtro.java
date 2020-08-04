package com.github.danimaniarqsoft.service.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Filtro {

    private List<String> contexto;
    private List<String> evento;
    private Instant fechaInicio;
    private Instant fechaFin;

	public List<String> getContexto() {
		return contexto;
	}

    public void setContexto(final List<String> contexto) {
        if (contexto == null) {
            this.contexto = new ArrayList<>();
        } else {
            this.contexto = contexto;
        }
	}
	public List<String> getEvento() {
		return evento;
	}

    public void setEvento(final List<String> evento) {
        if (evento == null) {
            this.evento = new ArrayList<>();
        } else {
            this.evento = evento;
        }
	}
	public Instant getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(final Instant fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Instant getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(final Instant fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public boolean isEmpty() {
		return this.contexto.isEmpty() && this.evento.isEmpty() && this.fechaInicio == null && this.fechaFin == null;
	}
    
}