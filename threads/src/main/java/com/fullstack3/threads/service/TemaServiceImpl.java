package com.fullstack3.threads.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;
import com.fullstack3.threads.model.Tema;
import com.fullstack3.threads.repository.TemaRepository;
import com.fullstack3.threads.exception.TemaNotFoundExceptionHandler;
import com.fullstack3.threads.exception.TemaAlreadyExistsExceptionHandler;

@Service
public class TemaServiceImpl implements TemaService {

    private final TemaRepository temaRepository;

    public TemaServiceImpl(TemaRepository temaRepository){
        this.temaRepository = temaRepository;
    }

    @Override
    public List<Tema> getAllTemas() {
        return temaRepository.findAll();
    }

    @Override
    public Tema getTemaById(Long id) {
        return temaRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Tema createTema(Tema tema) {
        if (temaRepository.existsByCodigoTema(tema.getCodigoTema())) {
            throw new TemaAlreadyExistsExceptionHandler("El tema con el código " + tema.getCodigoTema() + " ya existe.");
        }
        tema.setFechaCreacion(LocalDateTime.now());
        return temaRepository.save(tema);
    }

    @Override
    public Tema updateTema(Long id, Tema tema) {
        Tema existingTema = temaRepository.findById(id)
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + id));

        existingTema.setTitulo(tema.getTitulo());
        existingTema.setCodUsuario(tema.getCodUsuario());
        existingTema.setNombreUsuario(tema.getNombreUsuario());
        existingTema.setCodigoTema(tema.getCodigoTema());
        existingTema.setFechaModificacion(LocalDateTime.now());

        return temaRepository.save(existingTema);
    }

    @Override
    public void deleteTema(Long id) {
        Tema existingTema = temaRepository.findById(id)
                .orElseThrow(() -> new TemaNotFoundExceptionHandler("Tema no encontrado con id: " + id));
        temaRepository.delete(existingTema);
    }

}
