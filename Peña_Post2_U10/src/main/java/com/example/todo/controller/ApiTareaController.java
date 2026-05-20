package com.example.todo.controller;

import com.example.todo.model.Tarea;
import com.example.todo.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/tareas")
public class ApiTareaController {
    private final TareaService service;

    public ApiTareaController(TareaService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<Tarea> crear(@RequestBody Tarea payload) {
        Tarea t = service.crear(payload.getDescripcion());
        return ResponseEntity.created(URI.create("/api/tareas/" + t.getId())).body(t);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarea> obtener(@PathVariable Long id) {
        Tarea t = service.obtener(id);
        if (t == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(t);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarea> completar(@PathVariable Long id) {
        Tarea t = service.completar(id);
        if (t == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(t);
    }
}
