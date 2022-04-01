package com.springbatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatch.entity.Parametros;
import com.springbatch.repository.ParametrosRepository;

@RestController
@RequestMapping(value = "/parametros")
public class ParametrosController {
	
	@Autowired
	private ParametrosRepository parametrosRepository;
	
	@GetMapping
	public List<Parametros> getParameters() {
		return parametrosRepository.findAll();
	}

}
