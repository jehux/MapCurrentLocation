# MapCurrentLocation

## Descripción

Este proyecto es una aplicación de Android que muestra la ubicación actual del usuario en un mapa utilizando la API de Google Maps. La aplicación está desarrollada siguiendo la arquitectura MVP (Model-View-Presenter) para mantener una separación clara de responsabilidades y mejorar la mantenibilidad del código.

## Capturas de Pantalla

![Captura de Pantalla 1](https://github.com/jehux/MapCurrentLocation/app/src/main/res/drawable/captura_uno.jpg)
![Captura de Pantalla 2](https://github.com/jehux/MapCurrentLocation/app/src/main/res/drawable/captura_dos.jpg)

## Características

- Mostrar la ubicación actual del usuario en un mapa de Google Maps.
- Botón para refrescar la ubicación actual.
- Manejo de permisos de ubicación.
- Arquitectura MVP para un código limpio y modular.

## Requisitos

- Android Studio
- API Key de Google Maps

## Configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/tu_usuario/tu_proyecto.git
   ```
2. **Agregar API Key de Google Maps**:
    Crea el archivo secrets.properties en tu directorio de nivel superior y agrega el siguiente código. Reemplaza YOUR_API_KEY por tu clave de API. Almacena tu clave en este archivo, ya que secrets.properties no se registra en un sistema de control de versión
    ```bash
    MAPS_API_KEY=YOUR_API_KEY
    ```
