# meza-post1-u4
Post-contenido Patrones de Comportamiento aplicados al backend de ComprasUDES

## Descripción
Repositorio del post-contenido de la Unidad 4 de Patrones de Diseño
de Software. Un único proyecto Spring Boot (compras-comportamiento)
que resuelve cuatro necesidades reales del backend de ComprasUDES,
el sistema interno de solicitudes de compra corporativas: aprobación
por niveles jerárquicos, ejecución reversible de solicitudes
aprobadas, notificaciones ante cambios de estado y reglas de
transición según el estado actual de la solicitud.

## Decisiones de diseño

### Necesidad 1 - Aprobación por niveles jerárquicos

- **Patrón aplicado:** *Chain of Responsibility (Cadena de Responsabilidad)*.
- **Justificación:** Se requiere procesar solicitudes mediante una secuencia de decisores independientes (Supervisor, Gerente, Director Financiero), donde cada uno determina si tiene la autoridad para aprobar la solicitud según el monto o si la remite al siguiente nivel. Además, la cadena permite insertar dinámicamente nuevos niveles (como el *Revisor de Cumplimiento Normativo* para solicitudes de categoría `INTERNACIONAL`) sin que el cliente (`ControladorSolicitudes`) conozca la cantidad, el orden o la existencia de dichos niveles.
- **Alternativa descartada:** *Command (Comando)*.
- **Razón de rechazo:** Command busca encapsular una acción u operación como un objeto autónomo para ejecutarla, diferirla o deshacerla en un historial. No resuelve el problema de encaminar recursivamente una petición entre evaluadores en cadena hasta que uno de ellos la resuelva o reaccione a ella.

### Necesidad 2 - Ejecución reversible de solicitudes

- **Patrón aplicado:** *Command (Comando)*.
- **Justificación:** Cada operación de ejecución (reservar presupuesto, generar orden de compra) se encapsula en una clase que implementa la interfaz `ComandoEjecucion` con métodos explícitos para ejecutar (`ejecutar()`) y revertir (`deshacer()`). El objeto invocador (`GestorEjecucion`) administra una pila para revertir únicamente la última operación realizada sin afectar a las previas, y almacena un historial completo consultable de todas las operaciones aplicadas sobre la solicitud.
- **Alternativa descartada:** *Chain of Responsibility (Cadena de Responsabilidad)*.
- **Razón de rechazo:** En la Necesidad 2 no existe una secuencia de evaluadores decidiendo si tienen la facultad de procesar o pasar la petición al siguiente actor. Se trata de acciones ejecutables y reversibles de forma discreta solicitadas por un mismo gestor, las cuales deben quedar registradas de manera secuencial para auditoría y reversión independiente.

## Herramientas utilizadas

- Java 17, Spring Boot 3.2, Apache Maven, JUnit 5
- Visual Studio Code / IntelliJ IDEA, Git, GitHub
