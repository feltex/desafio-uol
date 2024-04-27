package br.com.feltex.desafio.uol.jogador;

import br.com.feltex.desafio.uol.jogador.modelo.JogadorRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class JogadorController {

  private JogadorService service;

  @PostMapping("/salvarJogador")
 public String salvar(@Valid JogadorRequest jogadorRequest, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "jogador-form";
    }

    service.salvar(jogadorRequest);
    return "redirect:/";
  }

  @GetMapping("/jogadorForm")
  public String mostrarJogadorForm(JogadorRequest jogadorRequest) {
    return "jogador-form";
  }

}
