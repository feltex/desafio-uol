package br.com.feltex.desafio.uol.jogador;

import br.com.feltex.desafio.uol.jogador.modelo.Jogador;
import br.com.feltex.desafio.uol.jogador.modelo.JogadorRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JogadorService {

  private JogadorRepository repository;
  private GrupoService grupoService;

  public Jogador salvar(JogadorRequest jogadorRequest) {
    final var jogador = new Jogador(jogadorRequest);

    var codinome = grupoService.getCodinome(jogadorRequest.grupo());
    jogador.setCodinome(codinome);
    return repository.save(jogador);
  }

  public List<Jogador> buscarTodos() {
    return repository.findAll();
  }

}
