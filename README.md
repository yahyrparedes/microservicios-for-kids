# Arquitectura de Microservicios 

Sesion de Mentoria 1

<br/>

### Descripcion del caso practico:

Se diseñaron 2 microservicios, mscategory ( Escrito en NodeJS ) y msproduct ( Escrito en Java Spring Boot ), estos servicios se registran en un servidor Eureka lo que permite su descubrimiento mutuo y comunicacion, segun el diagrama de arquitectura basica de microservicios expuesta en la sesion teorica.

![](https://user-images.githubusercontent.com/864790/172711982-f36a22cf-f7f5-4b3f-9064-ecafa535277a.png)


## Asignación

Escribir un microservicio adicional en el lenguaje de su preferencia que tambien se registre en el servidor de registro eureka para que sea descubrible por los otros microservicios.

Bibliotecas recomendadas:

- https://www.npmjs.com/package/eureka-js-client
- https://github.com/harmoney-jianbo/ruby-eureka
- https://www.nuget.org/packages/Steeltoe.Discovery.ClientCore/
