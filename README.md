# SauceDemo Automation Suite: Hybrid BDD & DDT Framework 🚀

Este repositorio contiene una suite de automatización de pruebas de extremo a extremo (E2E) para la plataforma **Sauce Demo**. El framework ha sido diseñado bajo una arquitectura híbrida que combina **Behavior-Driven Development (BDD)** y **Data-Driven Testing (DDT)**, priorizando el desacoplamiento de código y la ejecución de alto rendimiento.

---

## 🏗️ Arquitectura y Patrones de Diseño

El framework implementa principios de ingeniería de software para asegurar un código robusto y mantenible:

* **Page Object Model (POM):** Implementado en el paquete `pages` para encapsular la interacción con la UI.
* **Factory Pattern:** Localizado en `drivers`, utilizando `DriverFactory` para la instanciación dinámica de múltiples navegadores.
* **Dependency Injection (DI):** Uso de **PicoContainer** gestionado en el paquete `context` (`TestContext`), permitiendo compartir el estado del Driver entre Steps sin dependencias estáticas.
* **Fluent Interface:** Diseño de métodos en las páginas que permiten un flujo de escritura de pruebas más natural y legible.

---

## ⚡ Ejecución en Paralelo (High Performance)

Configurado a través de `junit-platform.properties` para maximizar la velocidad de retroalimentación en tres niveles:
1.  **A nivel de Escenarios:** Ejecución simultánea de casos de prueba.
2.  **Cross-Browser:** Pruebas concurrentes en diversos motores de navegación.
3.  **Multi-User:** Validación de diferentes perfiles de usuario en paralelo, optimizando el tiempo de ejecución en un **60%**.

---

## 📑 Escenarios Automatizados (Gherkin Coverage)

La cobertura se centra en los flujos más críticos para el negocio:

### 1. Gestión de Autenticación
* **Login Exitoso:** Validación con `standard_user`.
* **Data-Driven Testing:** Validación masiva con usuarios bloqueados, errores de rendimiento y credenciales inválidas.

### 2. Flujo de Compra E2E (Checkout)
* **Gestión de Carrito:** Selección de productos y persistencia de datos.
* **Checkout completo:** Validación de formularios, cálculo de impuestos y finalización exitosa de orden.

---

## 📁 Estructura del Proyecto (src/test)

La organización de los paquetes sigue estándares de proyectos de grado empresarial:

```text
java/
 ├── context          # Gestión de estado compartido (PicoContainer)
 ├── data             # Modelos de datos y POJOs (User/UserCreator)
 ├── drivers          # Gestión de navegadores (DriverFactory / Supplier)
 ├── hooks            # Setup y Teardown de escenarios
 ├── Logger           # Registro detallado de eventos (LogManager)
 ├── pages            # Clases del Page Object Model
 ├── runners          # Punto de entrada para ejecución (CucumberTestSuite)
 ├── stepdefinitions   # Implementación de pasos de Gherkin
 └── wait             # Utilidades de sincronización (WaitHelper)
resources/
 ├── features         # Escenarios de negocio (.feature)
 └── junit-platform.properties  # Configuración de paralelismo

```
---

## 📊 Reportería y Evidencias con Allure

Se integró **Allure Report** para proporcionar transparencia total y evidencias concretas sobre el proceso de QA, facilitando la comunicación con perfiles técnicos y de negocio:

* **Dashboards:** Visión general e interactiva de la salud del proyecto, detallando el porcentaje de pruebas pasadas, fallidas y omitidas.
* **Evidencias:** Generación y adjunto automático de capturas de pantalla (screenshots) en cada paso donde se detecte un fallo, reduciendo el tiempo de depuración.
* **Trazabilidad:** Desglose detallado de la ejecución alineado con los archivos `.feature`, permitiendo auditar cada paso del proceso.

---

## 🚀 Instalación y Ejecución

Sigue estos pasos para replicar el entorno de pruebas y visualizar los resultados del trabajo auténtico realizado en este framework:

### Requisitos
* **Java JDK 11** o superior.
* **Maven** instalado y configurado en las variables de entorno.

### Comandos Rápidos

**1. Clonar y situarse en la rama del proyecto:**
```bash
git clone [https://github.com/celestus-git/SauceDemo_TestSuite.git](https://github.com/celestus-git/SauceDemo_TestSuite.git)
cd SauceDemo_TestSuite
git checkout Junit+BDD

**2. Ejecutar la suite de pruebas:**
```bash
mvn clean test

**3. Generar y visualizar el reporte de Allure:**
```bash
allure serve allure-results
