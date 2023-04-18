# Prueba técnica Spring boot

Aplicación utilizando Spring Boot 2 y Java 11. API que permite hacer un mantenimiento de súper héroes.


# ¿Qué permite este mantenimiento?

* Consultar todos los súper héroes. 
* Consultar un único súper héroe por id. 
* Consultar todos los súper héroes que contienen, en su nombre, el valor de un parámetro enviado en la petición. Por ejemplo, si enviamos “man” devolverá “Spiderman”, “Superman”, “Manolito el fuerte”, etc. 
* Modificar un súper héroe. 
* Eliminar un súper héroe. 
* Test unitarios.
* Los súper héroes se guardan en una base de datos H2 en memoria.
* Jpa 
* Anotación personalizada que sirve para medir cuánto tarda en ejecutarse una petición.
* Gestión centralizada de excepciones. 
* Test de integración. 
* Aplicación dockerizada. 
* Poder cachear peticiones. 
* Documentación de la API. 
* Seguridad del API.


## API

* Usuario/contraseña disponibles para probar:
	*  user/user
	* admin/admin
* Swagger: http://localhost:8080/swagger-ui.html

#### /superhero
* `GET` : Recupera todos los súper héroes.
* `PUT` : Modifica un súper héroe.

#### /superhero?text=XXXXXX
* `GET` : Recupera los súper héroes que contengan el texto indicado por parámetro.

#### /superhero/:id
* `GET` : Recupera un súper héroe por id.
* `DELETE` : Elimina un súper héroe por id .
#### /cache
* `GET` : Borra toda la caché.
#### /cache?cacheName=XXXXXX
* `GET` : Borra la caché especificada por parámetro.

## Docker
```bash
# Construir contenedor
docker build -t practice-super-hero-api-rest .
```
```bash
# Ejecutar contenedor
docker run -p 8080:8080 practice-super-hero-api-rest
```
