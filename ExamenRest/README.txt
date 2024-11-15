Proyecto Spring Boot con MySQL y Docker
Este proyecto utiliza Spring Boot y MySQL para almacenar datos, y Docker para gestionar la base de datos. A continuación, se describen los pasos necesarios para levantar una instancia de MySQL en Docker, configurarla con una contraseña, crear una base de datos llamada prueba y conectar la aplicación Spring Boot a esta base de datos.

Requisitos
Antes de empezar, asegúrate de tener lo siguiente:

Docker instalado en tu máquina. Si no lo tienes, puedes descargarlo aquí.
MySQL instalado si no deseas usar Docker. (Docker se usará para evitar tener que instalar todo MySQL localmente).
Un proyecto Spring Boot configurado con JPA y MySQL.
Pasos para levantar MySQL con Docker
Levantar la instancia de MySQL con Docker:

Para crear un contenedor de MySQL con la contraseña del usuario root y la base de datos prueba pre-creada, ejecuta el siguiente comando:

bash
Copiar código
docker run --name some-mysql1 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=prueba -d -p 3306:3306 mysql:latest
Explicación de los parámetros:

--name some-mysql1: Da un nombre al contenedor de MySQL (en este caso some-mysql1).
-e MYSQL_ROOT_PASSWORD=root: Establece la contraseña del usuario root de MySQL.
-e MYSQL_DATABASE=prueba: Crea la base de datos prueba automáticamente al iniciar el contenedor.
-d: Ejecuta el contenedor en segundo plano (modo "detached").
-p 3306:3306: Mapea el puerto 3306 del contenedor al puerto 3306 de tu máquina local para poder conectarte a la base de datos.
mysql:latest: Usa la última versión de la imagen oficial de MySQL.
Verificar que el contenedor esté en ejecución:

Puedes comprobar que el contenedor de MySQL está corriendo correctamente con el siguiente comando:

bash
Copiar código
docker ps
Esto debería mostrarte el contenedor some-mysql1 en la lista de contenedores en ejecución.

Pasos para levantar el proyecto Spring Boot en Tomcat
Abre una consola (CMD o terminal).

Navega a la carpeta de tu proyecto:

Usa el comando cd para situar la consola en el directorio raíz de tu proyecto:

bash
Copiar código
cd /ruta/a/tu/proyecto
Ejecuta la aplicación Spring Boot:

Usa el siguiente comando para levantar el proyecto:

bash
Copiar código
mvn spring-boot:run
Este comando compilará y levantará la aplicación, que estará disponible en http://localhost:8080 (por defecto).

Probando la API con Postman
Petición GET
Para obtener los componentes almacenados en la base de datos, realiza una solicitud GET a la siguiente URL:

http
Copiar código
GET http://localhost:8080/api/componentes
Petición POST
Para agregar un nuevo componente, realiza una solicitud POST a la misma URL con el siguiente cuerpo (body) en formato JSON:

http
Copiar código
POST http://localhost:8080/api/componentes
Body:

json
Copiar código
{
    "nombre": "Procesador Intel i9",
    "marca": "Intel",
    "precio": 499.99,
    "descripcion": "Procesador de alta gama"
}
Esto agregará un nuevo componente a la base de datos prueba y podrás verlo al realizar la petición GET.