package br.com.feltex.desafio.uol.jogador.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jogador {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nome;

  @NotBlank
  private String email;

  private String telefone;

  private String codinome;

  private Grupo grupo;

  public Jogador(JogadorRequest jogadorRequest) {
    this.nome = jogadorRequest.nome();
    this.email = jogadorRequest.email();
    this.telefone = jogadorRequest.telefone();
    this.grupo = jogadorRequest.grupo();
  }
}
