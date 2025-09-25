package com.grupo11.sistema_tele_pizza_backend.dominio.dados;

import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Receita;

public interface ReceitasRepository {
    Receita recuperaReceita(long id);
    
}
