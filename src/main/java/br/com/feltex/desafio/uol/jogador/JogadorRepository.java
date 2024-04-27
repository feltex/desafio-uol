package br.com.feltex.desafio.uol.jogador;

import br.com.feltex.desafio.uol.jogador.modelo.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

}
