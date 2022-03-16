package com.springbatch.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springbatch.entity.Senhas;

public class SenhasItemProcessor implements ItemProcessor<Senhas,Senhas>{

	@Autowired(required=true)
	@Qualifier("passwordEncoder")
	private PasswordEncoder encoder;
	
	@Override
	public Senhas process(Senhas senhas) throws Exception {
		return new Senhas(senhas.getId(), senhas.getSenha1(), encoder.encode(senhas.getSenha1()));
	}

}
