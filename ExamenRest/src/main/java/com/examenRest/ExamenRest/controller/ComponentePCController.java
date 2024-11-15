package com.examenRest.ExamenRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenRest.ExamenRest.entity.ComponentePC;
import com.examenRest.ExamenRest.service.ComponentePCService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/componentes")
public class ComponentePCController {

    @Autowired
    private ComponentePCService componentePCService;

    @GetMapping
    public List<ComponentePC> obtenerComponentes() {
        return componentePCService.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<?> crearComponente(@Valid @RequestBody ComponentePC componentePC, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        ComponentePC componenteGuardado = componentePCService.guardarComponente(componentePC);
        return ResponseEntity.ok(componenteGuardado);
    }
}