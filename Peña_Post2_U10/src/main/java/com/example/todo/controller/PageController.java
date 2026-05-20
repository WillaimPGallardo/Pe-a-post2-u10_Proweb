package com.example.todo.controller;

import com.example.todo.service.TareaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    private final TareaService service;

    public PageController(TareaService service) { this.service = service; }

    @GetMapping("/tareas")
    @ResponseBody
    public String tareas() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html><html><head><title>Tareas</title></head><body>");
        sb.append("<h1>Tareas</h1>");
        sb.append("<button id=\"btn-nueva\">Nueva tarea</button>");
        sb.append("<ul id=\"lista\">");
        service.todas().forEach(t -> sb.append("<li class=\"tarea-item\">" + t.getDescripcion() + "</li>"));
        sb.append("</ul>");
        sb.append("</body></html>");
        return sb.toString();
    }

    @GetMapping("/tareas/nueva")
    @ResponseBody
    public String nueva() {
        return "<!doctype html><html><head><title>Nueva</title></head><body>" +
                "<form id=\"form-nueva\"><input id=\"desc\" name=\"desc\"/><button id=\"guardar\" type=\"button\">Guardar</button></form>" +
                "</body></html>";
    }
}
