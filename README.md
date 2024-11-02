## Nivel 2: API REST para Detección de Mutantes

1. **API HOSTEADA EN RENDER**:
   - La API se encuentra deployeada en Render. LINK: https://parcial1-ih1t.onrender.com


2. **PROBAR POST /mutant**:
   - Para comprobar el post se puede abrir Swagger con este link: https://parcial1-ih1t.onrender.com/swagger-ui/index.html

3. **Formato del POST**:
   - La solicitud debe enviarse en formato JSON con el siguiente formato:
     ```json
     {
       "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
     }
     ```

4. **Respuestas**:
   - Si el ADN es de un mutante, se devuelve un código HTTP **200 OK**.
   - Si el ADN pertenece a un humano, se devuelve un código HTTP **403 Forbidden**.

---

## Nivel 3: Integración de Base de Datos y Estadísticas

1. **Integración de H2**:
   - Se ha anexado una base de datos H2 para almacenar los ADN verificados.
   - Solo se permite un registro por ADN.
   - Para visualizar la base de datos se utiliza este link: https://parcial1-ih1t.onrender.com/h2-console/
   - Se debe usar en JDBC URL: jdbc:h2:mem:testdb

2. **PROBAR GET /stats**:
   - Para comprobar el GET se puede abrir Swagger con este link: https://parcial1-ih1t.onrender.com/swagger-ui/index.html
   - Se ejecuta dentro del Swagger

3. **Formato de Respuesta de Estadísticas**:
   - La respuesta devuelve un JSON con el siguiente formato:
     ```json
     {
       "count_mutant_dna": 1,
       "count_human_dna": 1,
       "ratio": 1
     }
     ```

4. **Requisitos de Escalabilidad**:
   - La API está diseñada para manejar fluctuaciones agresivas de tráfico, con un rango estimado de entre 100 y 1 millón de peticiones por segundo.
   - Esto se midio con JMeter, con los resultados en el PDF

5. **Pruebas Automáticas**:
   - Se implementaron pruebas automáticas con una cobertura de código superior al **80%**.
   - Esto se midio con Jacoco, con los resultados en el PDF

---
