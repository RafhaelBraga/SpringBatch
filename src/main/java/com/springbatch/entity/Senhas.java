package com.springbatch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Senhas")
public class Senhas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "senha1")
	private String senha1;
	
	@Column(name = "senha2")	
	private String senha2;

	public Senhas(long id, String senha1, String senha2) {
		super();
		this.id = id;
		this.senha1 = senha1;
		this.senha2 = senha2;
	}
	
	public Senhas(Senhas senhas) {
		super();
		this.id = senhas.getId();
		this.senha1 = senhas.getSenha1();
		this.senha2 = senhas.getSenha2();
	}
	
	public Senhas() {
		super();
	}

	public String getSenha1() {
		return senha1;
	}
	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
