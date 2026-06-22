# Proyecto Logística - Mini Core (Arquitectura Desacoplada)

## Descripción del Proyecto
Aplicación funcional desarrollada para resolver un problema de logística empresarial: calcular el costo total de los envíos realizados por cada repartidor dentro de un rango de fechas determinado. El sistema aplica tarifas variables según la zona de entrega correspondiente a cada envío.

El cálculo se realiza iterando sobre los envíos individuales de cada repartidor dentro del rango seleccionado. En caso de que un repartidor no cuente con envíos registrados en el período definido, el sistema gestiona la ausencia de datos asignando de forma predeterminada un costo de $0.00, asegurando la integridad lógica de la aplicación.

## Patrón Arquitectónico y Framework MVC Utilizado
Para este proyecto, el patrón clásico **MVC (Model-View-Controller)** fue adaptado a una arquitectura web moderna y desacoplada, separando físicamente el backend del frontend en dos directorios principales (`minicore` y `frontend`).

* **Modelo (Backend - Spring Boot & Spring Data JPA):** Responsable del acceso a la base de datos (PostgreSQL), la representación de las entidades de negocio (Repartidor, Envio, Zona) y el procesamiento lógico. Las operaciones matemáticas (suma de tarifas por peso) se manejan en la capa de servicios (`Service`), transfiriendo únicamente la información necesaria mediante objetos DTO.
* **Controlador (Backend - Spring Web):** Implementado mediante controladores REST (`@RestController`). Su función es recibir las peticiones HTTP GET provenientes de la vista, delegar el procesamiento al modelo y retornar la información estructurada en formato JSON, gestionando adicionalmente los permisos de origen (CORS).
* **Vista (Frontend - React & Vite):** Interfaz de usuario construida de manera independiente. Emplea HTML semántico estricto y captura los rangos de fechas definidos por el usuario, comunicándose con el controlador mediante el uso de la API Fetch para renderizar dinámicamente los resultados en una tabla.

## Enlaces de Referencia y Entregables
* **Video explicativo (Demostración y Código):** https://youtu.be/jW4olQVwo-Y 
* **Proyecto Deployado (Render):** https://minicore-logistica-spring-boot-backend.onrender.com - https://minicore-logistica-spring-boot-frontend.onrender.com
* **Documentación Oficial de Spring Boot:** https://docs.spring.io/spring-boot/index.html
* **Documentación Oficial de React:** https://react.dev/

---

## Instrucciones para Ejecución Local

Este repositorio consta de dos carpetas independientes. Para ejecutar el proyecto en un entorno local, es necesario levantar ambos servicios por separado.

### Requisitos Previos
* Java Development Kit (JDK) 17 o superior.
* Node.js y el gestor de paquetes `pnpm` instalados.
* Entorno de Desarrollo Integrado (IDE) compatible con Java, preferiblemente IntelliJ IDEA.
* Base de datos PostgreSQL o cadena de conexión de un servicio en la nube (ej. Neon.tech).

### 1. Configuración y Ejecución del Backend (Carpeta `minicore`)

El backend está construido con Spring Boot y maneja la lógica principal y conexión a la base de datos.

1. Clonar este repositorio en su máquina local.
2. Abrir su IDE (IntelliJ IDEA) y seleccionar específicamente la carpeta llamada `minicore` para abrirla como un proyecto Maven.
3. Esperar a que el IDE indexe el proyecto y descargue las dependencias definidas en el archivo `pom.xml`.
4. Configurar las credenciales de la base de datos. Para evitar la exposición de credenciales, el proyecto utiliza variables de entorno. Diríjase a la configuración de ejecución (Run/Debug Configurations) en su IDE y añada la siguiente cadena en el apartado **Environment Variables**, reemplazando los valores con sus credenciales locales o en la nube:
   ```text
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/tu_base_de_datos;SPRING_DATASOURCE_USERNAME=tu_usuario;SPRING_DATASOURCE_PASSWORD=tu_contraseña
Localizar el archivo principal MinicoreApplication.java (ubicado típicamente en src/main/java/com/example/minicore/) y ejecutarlo.

Verificar en la consola de salida que la aplicación se haya iniciado correctamente en el puerto 8080 y que Hibernate haya creado la estructura de las tablas (update).

Carga de Datos Iniciales (Seed Data): Una vez que el sistema ha creado las tablas, es necesario poblar la base de datos. Utilice su cliente SQL preferido (DataGrip, DBeaver, pgAdmin) conectado a la base de datos configurada y ejecute el siguiente script:

### SQL
```text
INSERT INTO zonas (id_zona, nombre_zona, tarifa_por_kg) VALUES (1, 'Norte', 1.50) ON CONFLICT DO NOTHING;
INSERT INTO zonas (id_zona, nombre_zona, tarifa_por_kg) VALUES (2, 'Sur', 2.00) ON CONFLICT DO NOTHING;
INSERT INTO zonas (id_zona, nombre_zona, tarifa_por_kg) VALUES (3, 'Centro', 1.00) ON CONFLICT DO NOTHING;
```
```text
INSERT INTO repartidor (id_repartidor, nombre, email) VALUES (1, 'Andrés', 'andres@mail.com') ON CONFLICT DO NOTHING;
INSERT INTO repartidor (id_repartidor, nombre, email) VALUES (2, 'Camila', 'camila@mail.com') ON CONFLICT DO NOTHING;
INSERT INTO repartidor (id_repartidor, nombre, email) VALUES (3, 'Luis', 'luis@mail.com') ON CONFLICT DO NOTHING;
```
```text
INSERT INTO envios (id_envio, id_repartidor, id_zona, peso_kg, fecha_envio) VALUES (1, 1, 1, 10.0, '2025-05-10') ON CONFLICT DO NOTHING;
INSERT INTO envios (id_envio, id_repartidor, id_zona, peso_kg, fecha_envio) VALUES (2, 1, 1, 22.0, '2025-05-15') ON CONFLICT DO NOTHING;
INSERT INTO envios (id_envio, id_repartidor, id_zona, peso_kg, fecha_envio) VALUES (3, 2, 2, 18.0, '2025-05-20') ON CONFLICT DO NOTHING;
```
### 2. Configuración y Ejecución del Frontend (Carpeta frontend)
La interfaz de usuario está construida con React y utiliza Vite como entorno de desarrollo.

Abrir una nueva ventana de terminal o consola de comandos.

Navegar explícitamente hacia el directorio del frontend utilizando el comando de cambio de directorio:

Bash
cd ruta/hacia/el/repositorio/frontend
Instalar las dependencias necesarias del proyecto ejecutando:

Bash
```text
pnpm install
```
Iniciar el servidor de desarrollo local mediante el comando:

Bash
```text
pnpm run dev
```
La terminal indicará que el servicio está en ejecución. Abra su navegador web e ingrese a http://localhost:5173.

Nota técnica: El código fuente de React está programado para utilizar la URL del backend de producción si detecta la variable de entorno correspondiente. Al ejecutarlo de manera local sin dicha variable, la aplicación dirigirá automáticamente las peticiones hacia http://localhost:8080, permitiendo la comunicación transparente con su backend local sin necesidad de modificar el código.

### 3. Prueba de la Aplicación (Uso del Frontend)
Para comprobar el correcto funcionamiento del sistema con los datos precargados (seed data), ingrese en el formulario web la **Fecha Inicio: 01/05/2025** y la **Fecha Fin: 31/05/2025**. Al hacer clic en el botón "Calcular Costos", la tabla consumirá la API y mostrará los resultados exactos del caso de uso, validando los envíos de Andrés y Camila, y asignando correctamente el valor de $0.00 al repartidor sin actividad en ese período.

Información de Contacto
Nombre: Kevin Johan Maquis Calle

Correo Institucional: kevin.maquis@udla.edu.ec
