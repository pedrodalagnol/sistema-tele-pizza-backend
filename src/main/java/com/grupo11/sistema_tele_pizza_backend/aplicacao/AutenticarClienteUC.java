package com.grupo11.sistema_tele_pizza_backend.aplicacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.requests.AutenticarClienteRequest;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.AutenticarClienteResponse;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cliente;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutenticarClienteUC {

    private final AutenticacaoService autenticacaoService;

    @Autowired
    public AutenticarClienteUC(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    public AutenticarClienteResponse run(AutenticarClienteRequest request) {
        Cliente cliente = autenticacaoService.autenticar(request.getEmail(), request.getSenha())
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha inválidos."));

        return new AutenticarClienteResponse(cliente.getId());
    }
}
