package br.com.feltex.desafio.uol.jogador;

import br.com.feltex.desafio.uol.jogador.modelo.Grupo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.PostConstruct;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Service
@Slf4j
@AllArgsConstructor
public class GrupoService {

  private List<String> vingadores;
  private List<String> ligaDaJustica;
  private Environment environment;
  private ObjectMapper objectMapper;

  @PostConstruct
  private void lerVingadores() {
    try {
      var uri = URI.create(Objects.requireNonNull(environment.getProperty("app.vingadores.url")));
      final var jsonNode = objectMapper.readTree(uri.toURL());
      final var listaVingadores = (ArrayNode) jsonNode.get("vingadores");

      for(JsonNode item : listaVingadores){
         this.vingadores.add(item.get("codinome").asText());
      }

    } catch (Exception e) {
      log.error("Não foi possível ler arquivo de Vingadores", e);
    }
  }


  @PostConstruct
  private void lerLigaDaJustica() {
    try{
      final var factory = DocumentBuilderFactory.newInstance();
      final var builder = factory.newDocumentBuilder();
      final var document = builder.parse(environment.getProperty("app.liga.da.justica.url"));

      NodeList listaCodinomes = document.getElementsByTagName("codinome");
      for (int i = 0; i < listaCodinomes.getLength(); i++) {
        Element codinomeElement = (Element) listaCodinomes.item(i);
        this.ligaDaJustica.add(codinomeElement.getTextContent());
      }
    }catch (Exception e){

      log.error("Não foi possível ler arquivo de Liga de Justiça", e);
    }


  }


  public String getCodinome(Grupo grupo) {

    if (Grupo.VINGADORES.equals(grupo)) {
      final var vingadorMembro = this.vingadores.stream().findFirst()
          .orElseThrow(() -> new NoSuchElementException("Não há mais elementos para a lista " + Grupo.VINGADORES));

      this.vingadores.remove(vingadorMembro);
      return vingadorMembro;
    }

    final var ligaJusticaMembro = this.ligaDaJustica.stream().findFirst()
        .orElseThrow(() -> new NoSuchElementException("Não há mais elementos para a lista " + Grupo.LIGA_DA_JUSTICA));

    this.ligaDaJustica.remove(ligaJusticaMembro);
    return ligaJusticaMembro;
  }
}
