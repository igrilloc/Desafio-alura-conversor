# Conversor de Divisas en Java

Este programa es un **conversor de divisas** desarrollado en Java que utiliza una API externa para obtener las tasas de conversión entre diferentes monedas. Permite convertir entre dólares estadounidenses (USD), pesos argentinos (ARS), reales brasileños (BRL) y pesos colombianos (COP).

## Descripción General

El programa está compuesto por dos clases principales:

- **`Main`**: Maneja la interacción con el usuario y el flujo principal del programa.
- **`ConsultaAPI`**: Realiza las solicitudes HTTP a la API de tasas de cambio y procesa la respuesta para obtener la tasa de conversión entre dos monedas.

## Funcionalidad

### Interfaz de Usuario

1. Al iniciar, el programa muestra un menú con opciones para seleccionar la conversión entre diferentes monedas.
2. El usuario selecciona una opción y se le solicita el valor a convertir.

### Obtención de la Tasa de Conversión

- La clase `ConsultaAPI` se encarga de hacer una solicitud HTTP a la API de Exchange Rate para obtener la tasa de conversión entre las dos monedas seleccionadas.
- Se utiliza `HttpClient` para realizar la solicitud y `Gson` para procesar la respuesta en formato JSON.

### Cálculo del Valor Convertido

- El valor ingresado por el usuario se multiplica por la tasa de conversión obtenida de la API.
- El programa muestra el resultado indicando cuántas unidades de la moneda de origen equivalen a la moneda de destino.

### Manejo de Errores

- El programa gestiona excepciones cuando el usuario ingresa valores inválidos o cuando ocurre un error en la comunicación con la API.
- En caso de no obtenerse una tasa de conversión válida, se informa al usuario y el programa continúa su ejecución.

## Consideraciones Técnicas

### Requisitos

- **Bibliotecas**: El programa requiere `java.net.http` para realizar las solicitudes HTTP y `Gson` para procesar los datos en formato JSON.
- **Clave de API**: Se necesita una clave válida para acceder a los servicios de Exchange Rate.

### Extensibilidad

- Se pueden agregar nuevas monedas fácilmente, modificando el menú y el método `getCurrencyPair()` en la clase `Main`.
- El manejo de errores puede mejorarse añadiendo casos específicos según las necesidades del proyecto.

## Ejemplo de Uso

```bash
****************************************
Bienvenido/a al Conversor de Moneda

1) Dólar → Peso argentino
2) Peso argentino → Dólar
3) Dólar → Real brasileño
4) Real brasileño → Dólar
5) Dólar → Peso colombiano
6) Peso colombiano → Dólar
7) Salir
Elija una opción válida: 1
Ingresa el valor que deseas convertir: 100
100.00 [USD] equivale a 9,800.00 [ARS]
```

### Instalación y Ejecución
1) Clona este repositorio:
```bash
git clone https://github.com/tu-usuario/conversor-divisas-java.git
```
2) Asegúrate de tener las dependencias necesarias (Gson, java.net.http) en tu proyecto.
3) Ejecuta la aplicación en tu entorno de desarrollo preferido.
