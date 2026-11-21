package com.fullstack3.threads.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.fullstack3.threads.dto.UsuarioExternoDTO;

@Component
public class UsuarioClient {

    private final RestClient restClient;

    public UsuarioClient(@Value("${usuarios.service.url}") String usuariosServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(usuariosServiceUrl)
                .build();
    }

    public UsuarioExternoDTO obtenerUsuarioPorCodigo(Integer codUsuario) {
        return restClient.get()
                .uri("/usuarios/codigo/{codUsuario}", codUsuario)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RuntimeException("El usuario con código " + codUsuario + " no existe en usercontrol");
                })
                .body(UsuarioExternoDTO.class);
    }
}