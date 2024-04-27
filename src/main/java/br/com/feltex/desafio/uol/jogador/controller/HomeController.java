package br.com.feltex.desafio.uol.jogador.controller;

import br.com.feltex.desafio.uol.jogador.JogadorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class HomeController {

  private JogadorService jogadorService;

  @RequestMapping("/")
  public ModelAndView jogadores() {
    final var modelAndView = new ModelAndView();
    modelAndView.setViewName("jogador-lista.html");
    final var jogadores = jogadorService.buscarTodos();
    modelAndView.getModel().put("jogadores", jogadores.isEmpty()? null : jogadores);
    return modelAndView;
  }

}
