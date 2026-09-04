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

### Necesidad 3 - Notificaciones ante cambio de estado

- **Patrón aplicado:** *Observer (Observador)*.
- **Justificación:** Permite desacoplar el objeto sujeto (`GestorNotificaciones` / `Solicitud`) de los receptores externos (correo, dashboard de contabilidad y auditoría). Cuando la solicitud cambia de estado, se notifica automáticamente a todos los observadores suscritos sin que la solicitud conozca la implementación interna ni el número de suscriptores, facilitando agregar nuevos módulos de reacción sin modificar el núcleo.
- **Alternativa descartada:** *State (Estado)*.
- **Razón de rechazo:** El patrón State se enfoca en alterar la lógica interna y las operaciones permitidas sobre un objeto según su estado actual, mientras que Observer gestiona las reacciones de componentes y servicios externos desacoplados cuando dicho cambio de estado ocurre.

### Necesidad 4 - Reglas de transición según el estado

- **Patrón aplicado:** *State (Estado)*.
- **Justificación:** Modela cada estado posible (`EstadoPendiente`, `EstadoAprobada`, `EstadoEjecutada`, etc.) como un objeto independiente que encapsula las reglas de qué transiciones u operaciones son válidas. Elimina los bloques `if/else` dispersos, garantizando que añadir un nuevo estado no requiera modificar la lógica existente en múltiples partes del sistema.
- **Alternativa descartada:** *Strategy (Estrategia)*.
- **Razón de rechazo:** Aunque estructuralmente similar, Strategy requiere que un cliente externo elija e inyecte una estrategia fija según el contexto. En el patrón State, la propia entidad transiciona automáticamente entre distintos estados autónomos como consecuencia de ejecutar sus operaciones permitidas.

## Reflexión sobre otros tres patrones

1. **Iterator (Iterador):** Encajaría en el reporte de centros de costo para recorrer la colección de solicitudes secuencialmente sin exponer la estructura interna de almacenamiento (List, Map, Set).
2. **Template Method (Método Plantilla):** Aplicaría para la impresión de comprobantes, definiendo un esqueleto de pasos comunes (encabezado, pie) en una clase base abstracta y delegando a las subclases el llenado particular del cuerpo.
3. **Memento (Recuerdo):** Se alinearía con el guardado de instantáneas completas del estado de una solicitud, almacenando y restaurando su estado interno en cualquier momento sin violar la encapsulación, a diferencia de Command que guarda una secuencia de operaciones/acciones ejecutadas.

## Herramientas utilizadas

- Java 17, Spring Boot 3.2, Apache Maven, JUnit 5
- Visual Studio Code / IntelliJ IDEA, Git, GitHub

## Conclusiones

La implementación de patrones de comportamiento permitió desacoplar la lógica de negocio de las solicitudes de compra en ComprasUDES. La principal enseñanza radicó en diferenciar patrones con estructuras de clases similares pero intenciones conceptuales completamente distintas, como la transición orientada al ciclo de vida en State versus la inyección táctica de algoritmos en Strategy, o el ruteo condicional en Chain of Responsibility frente a la encapsulación ejecutable de Command.
