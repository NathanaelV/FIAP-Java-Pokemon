package br.com.fiap.bo;

import br.com.fiap.dao.PokemonDAO;
import br.com.fiap.to.PokemonTO;

import java.util.ArrayList;

// BO: Business Object
// Essa classe implementa a regra de negócio da aplicação.
public class PokemonBO {
    private PokemonDAO pokemonDAO;

    public ArrayList<PokemonTO> findAll() {
        pokemonDAO = new PokemonDAO();
        // Implementação da regra de negócio.
        return pokemonDAO.findAll();
    }

    public PokemonTO save(PokemonTO pokemon) {
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.save(pokemon);
    }

    public PokemonTO findByCodigo(Long codigo) {
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.findByCodigo(codigo);
    }

    public boolean delete(Long codigo) {
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.delete(codigo);
    }

    public PokemonTO update(PokemonTO pokemon) {
        pokemonDAO = new PokemonDAO();
        return pokemonDAO.update(pokemon);
    }
}
