package com.grupo11.sistema_tele_pizza_backend.dominio.dados;

import java.util.List;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Ingrediente;

public interface IngredientesRepository {
    List<Ingrediente> recuperaTodos();
    List<Ingrediente> recuperaIngredientesReceita(long id);
}
