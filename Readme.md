**Evaluación final - Seniority Boost JAVA 2022**

**Consigna**

Desarrollar un microservicio para la venta de entradas a eventos:

1. Como usuario quiero ver un listado de eventos
  - El endpoint principal es GET /events por defecto el listado solo   
  muestra los eventos de hoy y futuros.
  - Se puede customizar la peticion
  usando un parametro.
  - GET /events?date=today solo muestra eventos de
  hoy.
  - GET /events?date=tomorrow solo muestra eventos de mañana
  - GET   /events?date=past solo muestra eventos pasados
  - En caso que sea GET /events?date=20220101 solo muestra los eventos de la fecha indicada  
  - En todos los casos lo eventos con entradas agotadas deben aparecer   
  tambien.
  - En todos los casos incluir la cantidad de entradas totales, disponibles y vendidas.
  - Hay eventos gratuitos que no requieren pago pero sí entrada.
  - Para reservar una entrada solo con los datos personales (nombre, apellido, dni, email) se obtiene.

2. Evento
  - Tiene una capacidad de asistentes o límite de entradas dependiendo de la sala donde se realice.
  - No se puede comprar entradas fuera de la fecha del evento.
3. Salas

- Representa al lugar donde se realizan los eventos.
- No puede haber dos eventos en la misma sala a la misma hora.

4. Eventos pasados
  - Los eventos pasados ya son solo de lectura.
  - Se requiere un endpoint que pueda ser llamado solo con autorizacion.
  - POST /batch/events debe ejecutar una operacion de copiar todos los eventos pasados a una base de datos no relacional.
  - Para autorizar debe aceptar un Bearer Token (para el ejemplo el token puede ser cualquier string)

5. Uso de las bases de datos
  - Los eventos pasados (solo lectura) se guardan en una coleccion de Mongo DB    
  - Los eventos futuros se guardan en MySQL.
  - Los eventos del dia en Redis solo para las consultas (junto con MySQL).

**Tecnologias**
- JAVA
- Sprint Boot
- Base de datos SQL (Mysql o Postgres)
- Mongo DB
- Redis
- JUnit
- Docker

**Tener en cuenta**
- Testing.
- Arquitectura limpia.
- Codigo limpio.
- Patrones de diseño.
- Usar características nuevas del lenguaje.
- Implementar técnicas aprendidas en clases.
- Archivo readme para aclaraciones o indicaciones de uso.
- Commits atómicos y descriptivos.

**Puntuación / Backlog**

| **Issue**                                                                        | **Valor** | **Estado** |
|----------------------------------------------------------------------------------|----------|------------|
| GET /events solo devuelve eventos de hoy y futuros                               | 5     | Done       |
| GET /events?date=tomorrow solo devuelve eventos de mañana                        | 4     | Done       |
| GET /events?date=past solo devuelve eventos pasados                              | 3     | Done       |
| GET /events?date=today solo devuelve eventos de hoy                              | 3     | Done       |
| GET /events?date=20220101 solo devuelve eventos de la fecha especificada         | 3     | Done       |
| En caso que la fecha sea invalida devuelve un error HTTP                         | 1     | Done       |
| En todos los casos anteriores incluye eventos con entradas agotadas              | 3     | Done       |
| No es posible comprar entradas si el evento ya es  pasado                        | 2     | Done       |
| No es posible comprar entradas si ya se alcanzó el límte de capacidad            | 2     | Done       |
| DELETE /events/{id} borra el evento solicitado                                   | 3     | Done       |
| No es posible borrar un evento si tiene entradas vendidas                        | 2     | Done       |
| POST /batch/events migra los datos correctamente                                 | 5     | To-Do      |
| Autoriza peticiones mediante un Bearer Token                                     | 3     | To-Do      |
| Al crear evento no es posible en la misma sala en la misma fecha y hora          | 5     | Done       |
| Se usaron para cada caso las bases de datos solicitadas                          | 6     | To-Do      |
| Por cada caso anterior (15) testeado correctamente con JUnit se agregan 4 puntos | 60    | WIP        |
| Dockerizado de toda la app                                                       | 10    | To-Do      |
| Funcionalidad Adicional: Versionado de DB (Flyway)                               |       | Done       |