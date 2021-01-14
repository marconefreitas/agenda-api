package io.github.marconefreitas.agendaapi.model.repository;

import io.github.marconefreitas.agendaapi.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
