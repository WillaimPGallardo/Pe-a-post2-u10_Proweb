package com.example.todo.service;

import com.example.todo.model.Tarea;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TareaService {
    private final Map<Long, Tarea> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public Tarea crear(String descripcion) {
        Long id = idGen.getAndIncrement();
        Tarea t = new Tarea(id, descripcion);
        store.put(id, t);
        return t;
    }

    public Tarea obtener(Long id) {
        return store.get(id);
    }

    public Tarea completar(Long id) {
        Tarea t = store.get(id);
        if (t != null) {
            t.setCompletada(true);
        }
        return t;
    }

    public Collection<Tarea> todas() { return store.values(); }
}
