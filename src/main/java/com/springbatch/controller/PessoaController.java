package com.springbatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatch.entity.Pessoas;
import com.springbatch.repository.PessoasRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoasRepository pessoasRepository;	
	
	@GetMapping
	private List<Pessoas> getPessoas() {
		return pessoasRepository.findAll();
	}
	
}
