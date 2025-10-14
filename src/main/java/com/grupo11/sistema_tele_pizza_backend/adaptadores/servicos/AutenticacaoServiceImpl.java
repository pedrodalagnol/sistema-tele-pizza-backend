package com.grupo11.sistema_tele_pizza_backend.adaptadores.servicos;

import com.grupo11.sistema_tele_pizza_backend.dominio.dados.ClienteRepository;
import com.grupo11.sistema_tele_pizza_backend.dominio.entidades.Cliente;
import com.grupo11.sistema_tele_pizza_backend.dominio.servicos.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public AutenticacaoServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Cliente> autenticar(String email, String senha) {
        return clienteRepository.findByEmail(email)
                .filter(cliente -> cliente.getSenha().equals(senha));
    }
}
