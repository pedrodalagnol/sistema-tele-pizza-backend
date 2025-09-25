
package com.grupo11.sistema_tele_pizza_backend.adaptadores.dados;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Ingrediente;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Receita;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.IngredientesRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ReceitasRepository;

@Repository
public class ReceitasRepositoryJDBC implements ReceitasRepository {
	private JdbcTemplate jdbcTemplate;
    private IngredientesRepository ingredientesRepository;

	@Autowired
	public ReceitasRepositoryJDBC(JdbcTemplate jdbcTemplate,IngredientesRepository ingredientesRepository) {
		this.jdbcTemplate = jdbcTemplate;
        this.ingredientesRepository = ingredientesRepository;
	}

    @Override
    public Receita recuperaReceita(long id) {
        String sql = "SELECT r.id, r.titulo FROM receitas r WHERE r.id = ?";
        List<Receita> receitas = this.jdbcTemplate.query(
            sql,
            ps -> ps.setLong(1, id),
            (rs, rowNum) -> {
                long receitaId = rs.getLong("id");
                String titulo = rs.getString("titulo");
                List<Ingrediente> ingredientes = ingredientesRepository.recuperaIngredientesReceita(receitaId);
                return new Receita(receitaId, titulo, ingredientes); 
            }
        );
        return receitas.isEmpty() ? null : receitas.get(0);
    }

}


