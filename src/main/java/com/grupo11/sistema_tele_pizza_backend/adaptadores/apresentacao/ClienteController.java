package com.grupo11.sistema_tele_pizza_backend.adaptadores.apresentacao;

import com.grupo11.sistema_tele_pizza_backend.aplicacao.AutenticarClienteUC;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.RegistrarClienteUC;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.requests.AutenticarClienteRequest;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.requests.RegistrarClienteRequest;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.AutenticarClienteResponse;
import com.grupo11.sistema_tele_pizza_backend.aplicacao.responses.ClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final RegistrarClienteUC registrarClienteUC;
    private final AutenticarClienteUC autenticarClienteUC;

    @Autowired
    public ClienteController(RegistrarClienteUC registrarClienteUC, AutenticarClienteUC autenticarClienteUC) {
        this.registrarClienteUC = registrarClienteUC;
        this.autenticarClienteUC = autenticarClienteUC;
    }

    @PostMapping("/cadastro")
    @CrossOrigin("*")
    public ClienteResponse registrarCliente(@RequestBody RegistrarClienteRequest request) {
        return registrarClienteUC.run(request);
    }

    @PostMapping("/login")
    @CrossOrigin("*")
    public AutenticarClienteResponse autenticarCliente(@RequestBody AutenticarClienteRequest request) {
        return autenticarClienteUC.run(request);
    }
}
