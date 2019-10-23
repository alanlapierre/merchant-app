# MERCHANT API REST

El ejemplo es un **API REST** desarrollado en **Java 8** con **Spring Boot 2.1**. El objetivo fue simular en forma simple el comportamiento de un API para una **APP de e-commerce**, brindando las siguientes funcionalidades:

* **Gestion de Merchants (comerciantes)** : Podemos agregar, modificar, editar y eliminar comerciantes.
* **Agregar venta**: Podemos agregar una venta a un comerciante de determinado producto, indicando la cantidad de artículos vendidos.
* **Calcular comisiones**: Para obtener el total de comisiones a pagar por un comerciante, teniendo en cuenta ciertas reglas y la cantidad de productos vendidos. Esto puede ser utilizado para cerrar el período de facturación por parte de la empresa de e-commerce y cobrar a sus clientes (los merchants) las comisiones por ventas según corresponda.


### Dentro de las consideraciones que tuve para el desarrollo se encuentran las siguientes:

* **Separación en capas**. Para que la lógica quede organizada de forma correcta.
* **Mantener la lógica de negocio 100% en el Service**. Para que la llamada desde el Controller sea completamente transparente.
* **Buen tratamiento de errores**. Para que desde la capa de servicios se emita siempre un **BusinessException** y el Controller solamente vea este tipo de errores.
* **Usar un nombrado correcto en las URLs de los endpoints**. Para lograr que los Servicios Rest sean un API REST, y no solamente una capa de servicios. Teniendo en cuenta el **versionado del API REST**.
* **Documentación del API REST con Swagger**.
* **Utilización de H2 en memoria como Database**. Para que el ejemplo sea mas portable y no requiera de un Postgres, MySql, etc.
* **Validación de parámetros pasados a los endpoints usando Bean Validation**. Para evitar validaciones manuales dentro del Controller de los valores enviados.
* **Habilitación de auditoria de en Spring Boot**. Para que todas las clases del modelo incluyan Timestamp de creación y de actualización.

### Notas:

* Para acceder a la consola de administración de H2: http://localhost:8080/h2 (una vez la app se encuentra ejecutando).
* Para acceder a la vista de documentación del API: http://localhost:8080/swagger-ui.html (una vez la app se encuentra ejecutando).


## API REST ENDPOINTS

### CREAR MERCHANT

```sh
POST /api/v1/merchants
```

```sh
{
	"email":"t2223@test.com",
	"phone":"3222",
	"name": "test",
	"address":"test 1234, CABA, Argentina",
	"plan": {
		"id":"1"
	}
}  
```
`NOTA 1: Todos los valores del ejemplo son necesarios para poder dar el alta del Merchant.`

`NOTA 2: Dentro del código hay un archivo data.sql que incluye los tipos de planes disponibles. Cualquier ID de Plan pasado en el alta del Merchant que no coincida con los existentes va a generar un BAD REQUEST como respuesta.`


### OBTENER TODOS LOS MERCHANTS

```sh
GET /api/v1/merchants  
```

### OBTENER MERCHANT POR ID

```sh
GET /api/v1/merchants/1
```

`NOTA 1: En caso de no existir el Merchant obtendremos un BAD REQUEST (400).`


### ELIMINAR UN MERCHANT POR ID

```sh
DELETE /api/v1/merchants/1  
```

`NOTA 1: En caso de no existir el Merchant obtendremos un BAD REQUEST (400).`


### MODIFICAR UN MERCHANT

```sh
PUT /api/v1/merchants/1
```
  
```sh
{
	"email":"t2223@test.com",
	"phone":"3222",
	"name": "test",
	"address":"test 1234, CABA, Argentina",
	"plan": {
		"id":"1"
	}
}
```

`NOTA 1: Todos los valores del ejemplo son necesarios para poder hacer update del Merchant. En caso de no existir el merchant obtendremos un BAD REQUEST.`

`NOTA 2: Dentro del código hay un archivo data.sql que incluye los tipos de planes disponibles. Cualquier ID de Plan pasado en el update del Merchant que no coincida con los existentes va a generar un BAD REQUEST como respuesta.`


### AGREGAR UNA VENTA A UN MERCHANT

```sh
POST /api/v1/merchants/1/sales
```

```sh
{
	"quantity":"20",
	"product": {
		"id":"5"	
	}
}
```

`NOTA 1: Es necesario enviar la cantidad de productos (quantity) y el id del producto seleccionado. El Id de Merchant va en la URL.`

`NOTA 2: Dentro del código hay un archivo data.sql que incluye los productos disponibles. Cualquier ID de producto pasado que no coincida con los existentes va a generar un BAD REQUEST como respuesta.`

### OBTENER LA SUMA DE LAS COMISIONES POR VENTAS PARA UN MERCHANT

```sh
GET /api/v1/merchants/1/sales/transactionfees  
```


Licencia MIT
----
**Para uso totalmente libre**.
