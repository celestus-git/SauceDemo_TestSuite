# SauceDemo Automation Suite: Hybrid BDD & DDT Framework 🚀

Este repositorio contiene una suite de automatización de pruebas de extremo a extremo (E2E) para la plataforma **Sauce Demo**. El framework ha sido diseñado bajo una arquitectura híbrida que combina **Behavior-Driven Development (BDD)** y **Data-Driven Testing (DDT)**, priorizando la escalabilidad, el mantenimiento y la ejecución de alto rendimiento.

---

## 🛠️ Stack Tecnológico

| Herramienta | Propósito |
| :--- | :--- |
| **Java** | Lenguaje de programación base. |
| **Selenium WebDriver** | Automatización de la interfaz de usuario. |
| **Cucumber** | Implementación de BDD mediante Gherkin. |
| **JUnit / TestNG** | Gestión y ejecución de pruebas. |
| **Maven** | Gestión de dependencias y ciclo de vida. |
| **Allure Report** | Reportería visual y análisis de resultados. |
| **PicoContainer** | Inyección de Dependencias (DI) para gestión de estados. |

---

## 🏗️ Arquitectura y Patrones de Diseño

El framework implementa principios avanzados de ingeniería de software para asegurar un código desacoplado y robusto:

* **Page Object Model (POM):** Separación total de la lógica de negocio y los localizadores de la UI.
* **Factory Pattern:** Uso de un `WebDriverFactory` para la instanciación dinámica de múltiples navegadores (Chrome, Firefox, Edge).
* **Dependency Injection (DI):** Implementación de **PicoContainer** para compartir el estado del WebDriver entre Step Definitions sin dependencias estáticas, garantizando el aislamiento de hilos.
* **Singleton Pattern:** Gestión centralizada de archivos de configuración y propiedades.

---

## ⚡ Ejecución en Paralelo (High Performance)

Una de las mayores fortalezas de este framework es su capacidad de ejecución concurrente configurada a tres niveles:
1.  **A nivel de Escenarios:** Ejecución simultánea de múltiples casos de prueba de Cucumber.
2.  **A nivel de Navegadores (Cross-Browser):** Pruebas en paralelo en distintos motores de navegación.
3.  **A nivel de Datos (Users):** Validación concurrente de diferentes perfiles de usuario, optimizando el tiempo de feedback en un **60%**.

---

## 📑 Escenarios Automatizados (Gherkin Coverage)

La cobertura se centra en los flujos más críticos para el negocio:

### 1. Gestión de Autenticación
* **Login Exitoso:** Validación con `standard_user`.
* **Data-Driven Testing:** Validación masiva de login con usuarios bloqueados (`locked_out_user`), problemas de rendimiento y credenciales inválidas.

### 2. Flujo de Compra E2E (Checkout)
* **Gestión de Carrito:** Selección dinámica de productos y persistencia del contador.
* **Checkout completo:** Validación del formulario de información, cálculo de impuestos y finalización exitosa de la orden ("THANK YOU FOR YOUR ORDER").

---

## 📊 Reportería Avanzada con Allure

Se integró **Allure Report** para ofrecer visibilidad técnica y de negocio:
* **Dashboards dinámicos:** Resumen ejecutivo de la salud de la suite.
* **Evidencia visual:** Capturas de pantalla adjuntas automáticamente en cada paso fallido.
* **Trazabilidad:** Desglose paso a paso de la ejecución en lenguaje Gherkin.

---

## 🚀 Instalación y Ejecución

### Requisitos
* Java JDK 11 o superior.
* Maven instalado.

### Pasos
1.  Clonar el proyecto:
    ```bash
    git clone [https://github.com/celestus-git/SauceDemo_TestSuite.git](https://github.com/celestus-git/SauceDemo_TestSuite.git)
    cd SauceDemo_TestSuite
    git checkout Junit+BDD
    ```
2.  Ejecutar las pruebas:
    ```bash
    mvn clean test
    ```
3.  Generar el reporte de Allure:
    ```bash
    allure serve allure-report
    
    allure serve allure-results
    ```

---

## 📁 Estructura del Proyecto

```text
src/
 ├── main/java/pages          # Page Objects (Encapsulamiento de UI)
 ├── test/java/
 │    ├── stepDefinitions     # Implementación de pasos Gherkin
 │    ├── runners            # Clases de ejecución (TestNG/JUnit)
 │    └── utils               # WebDriver Factory e Inyección de Dependencias
 └── test/resources/features  # Archivos de texto Gherkin (.feature)
