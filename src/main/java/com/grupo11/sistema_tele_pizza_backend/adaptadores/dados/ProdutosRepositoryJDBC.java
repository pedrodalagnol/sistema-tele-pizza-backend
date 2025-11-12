package com.grupo11.sistema_tele_pizza_backend.adaptadores.dados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ProdutosRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ReceitasRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Produto;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Receita;

@Component
public class ProdutosRepositoryJDBC implements ProdutosRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ReceitasRepository receitasRepository;

    @Autowired
    public ProdutosRepositoryJDBC(JdbcTemplate jdbcTemplate,ReceitasRepository receitasRepository){
        this.jdbcTemplate = jdbcTemplate;
        this.receitasRepository = receitasRepository;
    }

    @Override
    public List<Produto> recuperaProdutosCardapio(long id) {
        String sql = "SELECT p.id, p.descricao, p.preco, p.disponivel, pr.receita_id " +
                     "FROM produtos p " +
                     "JOIN cardapio_produto cp ON p.id = cp.produto_id " +
                     "JOIN produto_receita pr ON p.id = pr.produto_id " +
                     "WHERE cp.cardapio_id = ? AND p.disponivel = true";
        return this.jdbcTemplate.query(
            sql,
            ps -> ps.setLong(1, id),
            (rs, rowNum) -> {
                long produtoId = rs.getLong("id");
                String descricao = rs.getString("descricao");
                int preco = rs.getInt("preco");
                boolean disponivel = rs.getBoolean("disponivel");
                long receitaId = rs.getLong("receita_id");
                Receita receita = receitasRepository.recuperaReceita(receitaId);
                Produto produto = new Produto(produtoId, descricao, receita, preco);
                produto.setDisponivel(disponivel);
                return produto;
            }
        );
    }

    @Override
    public Produto recuperaProdutoPorid(long id) {
        String sql = "SELECT p.id, p.descricao, p.preco, p.disponivel, pr.receita_id " +
                     "FROM produtos p " +
                     "JOIN produto_receita pr ON p.id = pr.produto_id " +
                     "WHERE p.id = ?";
        List<Produto> produtos = this.jdbcTemplate.query(
            sql,
            ps -> ps.setLong(1, id),
            (rs, rowNum) -> {
                long produtoId = rs.getLong("id");
                String descricao = rs.getString("descricao");
                int preco = rs.getInt("preco");
                boolean disponivel = rs.getBoolean("disponivel");
                long receitaId = rs.getLong("receita_id");
                Receita receita = receitasRepository.recuperaReceita(receitaId);
                Produto produto = new Produto(produtoId, descricao, receita, preco);
                produto.setDisponivel(disponivel);
                return produto;
            }
        );
        if (produtos.isEmpty()) {
            return null;
        }
        return produtos.get(0);
    }

    @Override
    public void marcarComoIndisponivel(long id) {
        String sql = "UPDATE produtos SET disponivel = false WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
