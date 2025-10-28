package br.com.fiap.resource;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.camel.component.properties.RefPropertiesSource;

import java.awt.*;
import java.lang.annotation.Repeatable;
import java.util.ArrayList;

// Path: define o caminho base para todos os métodos de da classe PokemonResource.
@Path("/pokemon")
// Classe que receberá as requisições HTTP.
public class PokemonResource {
    private PokemonBO pokemonBO = new PokemonBO();

    // GET: responde por requisições GET, que é o verbo HTTP que mapeia o method findAll. Verbo de solicitação.
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

        // entity: forma o Body/entity da resposta HTTP
        response.entity(resultado);
        // build: forma o response, unindo o status, o body e o header
        return response.build();
    }

    // POST: responde por requisições POST. Verbo HTTP usado para enviar dados
    @POST
    // Consumes: Define o tipo de informação que o recurso recebe
    @Consumes(MediaType.APPLICATION_JSON)
    // @Valid Ativa as validações feitas no PokemonTO
    public Response save(@Valid PokemonTO pokemon) {
        PokemonTO resultado = pokemonBO.save(pokemon);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);  // 201 - CREATED
        } else {
            response = Response.status(400);            // 401 - BAD REQUEST
        }

        response.entity(resultado);
        return response.build();
    }

    @GET
    // Path: acrescenta um novo path ao "/pokemon", as chaves {} indicam que codigo é uma variável.
    // Ex.: /pokemon/6, o valor de codigo1 será 6
    @Path("/{codigo1}")
    @Produces(MediaType.APPLICATION_JSON)
    // PathParam: extrai o valor da variável codigo1 que foi passado no Path, e passa para codigo2, já convertendo para
    // o tipo especificado, nesse caso Long
    public Response findByCodigo(@PathParam("codigo1") Long codigo2) {
        PokemonTO resulado = pokemonBO.findByCodigo(codigo2);
        Response.ResponseBuilder response = null;

        if (resulado != null) {
            response = Response.ok();           // 200 (OK)
        } else {
            response = Response.status(404);    // 404 (NOT FOUND)
        }

        response.entity(resulado);
        return response.build();
    }

    // DELETE: responde por requisições DELETE.
    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        Response.ResponseBuilder response = null;

        if (pokemonBO.delete(codigo)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }

        return response.build();
    }
}
