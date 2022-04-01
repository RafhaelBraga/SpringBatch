package com.springbatch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parametros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long ultimoId;
	private String tabela;
	
	public Parametros() {
		super();
	}
	public Parametros(long id, long ultimoId, String tabela) {
		super();
		this.id = id;
		this.ultimoId = ultimoId;
		this.tabela = tabela;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUltimoId() {
		return ultimoId;
	}
	public void setUltimoId(long ultimoId) {
		this.ultimoId = ultimoId;
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

}
