# App S9 - SharedPreferences Demo (Versión Mejorada)

Aplicación Android de ejemplo que demuestra el uso avanzado de `SharedPreferences` para almacenamiento persistente de datos del usuario, incluyendo control de visitas, tema oscuro y gestión extendida de usuarios.

## 📱 Descripción

Esta aplicación permite guardar y recuperar información personalizada del usuario, incluyendo nombre, apellido y correo, además de contar con funcionalidades adicionales como:
- Contador de visitas
- Modo oscuro con `Switch`
- Reinicio del contador
- Detección de primer uso

## 🚀 Características Nuevas y Mejoradas

- ✅ **Gestión de usuarios**: Guarda nombre, apellido y correo electrónico
- ✅ **Contador de visitas**: Se incrementa cada vez que se abre la app
- ✅ **Botón para reiniciar visitas**
- ✅ **Modo oscuro**: Toggle persistente con `SharedPreferences`
- ✅ **Persistencia de datos**: Todos los datos se mantienen entre ejecuciones

## ⚙️ Funcionalidades Originales

- ✔️ Guardar y recuperar nombre de usuario
- ✔️ Verificar si es la primera vez que se abre la app
- ✔️ Limpiar todas las preferencias
- ✔️ Interfaz intuitiva con campos y botones

## 🆕 Modificaciones Realizadas

| Funcionalidad              | Descripción                                                                 |
|---------------------------|-----------------------------------------------------------------------------|
| 👤 Gestión de Usuario      | Se añadieron campos para **apellido** y **correo electrónico**              |
| 🔢 Contador de Visitas     | Se implementó un contador que se incrementa cada vez que se inicia la app  |
| ♻️ Reinicio de Contador    | Botón para reiniciar el contador de visitas a 0                             |
| 🌗 Modo Oscuro             | Se añadió un `Switch` para alternar entre modo claro y oscuro, con persistencia |
| 🧹 Limpieza Total          | Al presionar "Limpiar Todo", se eliminan todas las preferencias y se actualiza el contador |

## 📋 Requisitos

- Android Studio Arctic Fox o superior
- SDK mínimo: API 21 (Android 5.0)
- SDK objetivo: API 34 (Android 14)
- Kotlin 1.9.0

## 🛠️ Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/GxJohan/app_s9.git
