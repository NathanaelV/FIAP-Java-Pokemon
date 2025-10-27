package br.com.fiap.resource;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.util.ArrayList;

// Path: define o caminho base para todos os métodos de da classe PokemonResource.
@Path("/pokemon")
// Classe que receberá as requisições HTTP.
public class PokemonResource {
    private PokemonBO pokemonBO = new PokemonBO();

    // GET: é o verbo HTTP que mapeia o method findAll.
    @GET
    // Produces: define o tipo de informação que o method irá produzir.
    // MediaType.APPLICATION_JSON: constante que representa o formato JSON.
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<PokemonTO> resultado = pokemonBO.findAll();
        // response: Ajuda a criar a resposta HTTP
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();           // 200 (OK)
        } else {
            response = Response.status(404);    // 404 (NOT FOUND)
        }

        // Forma o Body/entity da resposta HTTP
        response.entity(resultado);
        return response.build();
    }

}
