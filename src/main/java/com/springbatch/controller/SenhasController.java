package com.springbatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatch.entity.Senhas;
import com.springbatch.repository.SenhasRepository;

@RestController
@RequestMapping("/senhas")
public class SenhasController {

	@Autowired
	private SenhasRepository senhasRepository;
	
	@GetMapping
	public List<Senhas> getSenhas() {
		return senhasRepository.findAll();		
	}
}
