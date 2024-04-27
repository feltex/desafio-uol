package br.com.feltex.desafio.uol.jogador.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogadorRequest(

    @NotBlank(message = "Nome é obrigatório.")
    String nome,

    @NotBlank(message = "Email é obrigatório.")
    String email,

    String telefone,

    @NotNull(message = "Grupo é obrigatório.")
    Grupo grupo
) {

}




