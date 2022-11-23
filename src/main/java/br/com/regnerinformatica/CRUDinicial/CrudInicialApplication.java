package br.com.regnerinformatica.CRUDinicial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CrudInicialApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudInicialApplication.class, args);
	}

}
