Sanjuan_Post2_U10_ProWeb
Proyecto de la actividad "Post-Contenido 2 — Pruebas E2E con Selenium, Postman y Newman".



Resumen:

Aplicación Spring Boot mínima todo-app con endpoints REST en /api/tareas.
Tests E2E con Selenium (Page Object Model) en src/test/java/e2e.
Colección Postman en postman/ColeccionToDo.json y entornos en postman/.
Workflow de GitHub Actions en .github/workflows/api-tests.yml que ejecuta Newman.
Requisitos locales:

Java 17
Maven
Node.js 18+ y newman (para ejecución local)
Google Chrome (para ejecutar Selenium localmente)
Cómo ejecutar localmente (desarrollo):

Construir y ejecutar la aplicación:
mvn -B package
java -jar target/*.jar
Ejecutar la colección Postman con Newman (suponiendo app en http://localhost:8080):
npm install -g newman
newman run postman/ColeccionToDo.json --environment postman/env-local.json
Ejecutar tests E2E (requiere Chrome y dependencias):
mvn test -Dtest=e2e.TareasE2ETest
