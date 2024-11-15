package com.examenRest.ExamenRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenRest.ExamenRest.entity.ComponentePC;
import com.examenRest.ExamenRest.repository.ComponentePCRepository;

@Service
public class ComponentePCService {

    @Autowired
    private ComponentePCRepository componentePCRepository;

    public ComponentePC guardarComponente(ComponentePC componentePC) {
        return componentePCRepository.save(componentePC);
    }

    public List<ComponentePC> obtenerTodos() {
        return componentePCRepository.findAll();
    }
}