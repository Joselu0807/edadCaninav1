# Edad Canina (v3 · MVC y DataBinding)

Aplicación Android hecha en Java que calcula la edad humana de un perro (multiplicando sus años por 7). En esta versión he reestructurado el proyecto aplicando el patrón **MVC** y **DataBinding** para dejar `MainActivity` limpia y separar la lógica de la interfaz.

## ¿Qué hace?

* **Valida la entrada:** Comprueba que no esté vacío, que sea un número válido y que esté entre **1 y 30 años**.
* **Se limpia al editar:** Si modificas el número, desaparecen solos el resultado anterior y cualquier aviso de error.
* **Botón Limpiar y tecla Listo:** Permite reiniciar la pantalla con un toque o calcular directamente desde la tecla **Listo / ✓** del teclado (ocultándolo al instante).
* **Aguanta el giro de pantalla:** Usa `onSaveInstanceState` para no perder el número, el resultado ni los errores al rotar el móvil.

## Estructura del proyecto

```text
es.davante.edadcanina/
├── modelo/       # Cálculos, validaciones y datos observables (EdadCaninaModelo, Validacion)
├── controlador/  # Acciones de Calcular y Limpiar (EdadCaninaControlador)
├── vista/        # @BindingAdapter para pintar errores y capturar la tecla Listo
└── MainActivity  # Enlaza el binding y guarda el estado al girar
```

Al no mezclar vistas de Android dentro del modelo ni del controlador, he podido comprobar toda la lógica con pruebas unitarias en **JUnit 4** (`EdadCaninaModeloTest` y `EdadCaninaControladorTest`) sin tener que abrir el emulador.

## Historial en Git

El desarrollo está guardado por fases mediante etiquetas (*tags*):

* **`paso-1` a `paso-7`:** Estructura inicial, modelo con tests JUnit, layout XML, DataBinding, `@BindingAdapter` para validaciones, controlador con botón *Limpiar* y guardado de estado al rotar.
* **`reto-teclado`:** Cálculo y cierre automático del teclado al pulsar *Listo* (`actionDone`).

---

**Jose Luis Martin** · 2.º DAM