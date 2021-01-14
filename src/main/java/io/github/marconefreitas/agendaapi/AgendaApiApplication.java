package io.github.marconefreitas.agendaapi;

import io.github.marconefreitas.agendaapi.model.entity.Contato;
import io.github.marconefreitas.agendaapi.model.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgendaApiApplication {

	/*@Bean
	public CommandLineRunner teste(
			@Autowired ContatoRepository co){
		return args -> {
			Contato c = new Contato();
			c.setNome("Marcone");
			c.setEmail("marcfreitas0@gmail.com");
			c.setFavorito(Boolean.FALSE);

			co.save(c);
		};

	}*/

	public static void main(String[] args) {
		SpringApplication.run(AgendaApiApplication.class, args);
	}

}
