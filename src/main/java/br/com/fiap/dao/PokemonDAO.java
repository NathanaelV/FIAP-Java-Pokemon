package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// DAO: Data Access Object
// Classe responsável por interagir com o Banco de Dados.
public class PokemonDAO {
    // Method responsável por retornar todos os dados salvos no banco de dados, nesse caso, Pokemon.
    public ArrayList<PokemonTO> findAll() {
        // Cria um Array para guardar objetos do tipo PokemonTO
        ArrayList<PokemonTO> pokemons = new ArrayList<PokemonTO>();

        // Query: comando de consulta ao banco de dados para retornar todos os itens da tabela ddd_pokemons.
        String sql = "select * from ddd_pokemons order by codigo";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    PokemonTO pokemon = new PokemonTO();
                    pokemon.setCodigo(rs.getLong("codigo"));
                    pokemon.setNome(rs.getString("nome"));
                    pokemon.setAltura(rs.getDouble("altura"));
                    pokemon.setPeso(rs.getDouble("peso"));
                    pokemon.setCategoria(rs.getString("categoria"));
                    pokemon.setDataDaCaptura(rs.getDate("data_da_captura").toLocalDate());
                    System.out.println(pokemon);
                    pokemons.add(pokemon);
                }
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return pokemons;
    }
}
