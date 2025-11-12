#!/bin/bash
mvn clean

TARGET_DIR="target"
# Crear carpetas ANTES de la ejecución (para evitar errores en la ejecución)
mkdir -p ${TARGET_DIR}/allure-chrome
mkdir -p ${TARGET_DIR}/allure-firefox
mkdir -p ${TARGET_DIR}/allure-edge

echo "--- Iniciando Ejecución Paralela Cross-Browser ---"

# Ejecución paralela
mvn test -Dtest.browser=CHROME -Djson.report.file=cucumber-chrome.json -Dallure.results.directory=${TARGET_DIR}/allure-chrome &
mvn test -Dtest.browser=FIREFOX -Djson.report.file=cucumber-firefox.json -Dallure.results.directory=${TARGET_DIR}/allure-firefox &
mvn test -Dtest.browser=EDGE -Djson.report.file=cucumber-edge.json -Dallure.results.directory=${TARGET_DIR}/allure-edge &

wait # Espera a que los 3 procesos terminen

echo "--- Generando Reporte Consolidado de Allure ---"

# 1. Ejecutar el comando de Allure CLI para generar el reporte
# El plugin de Maven ya está configurado para buscar en 'target/allure-results' (por defecto).

# 2. Vamos a usar la herramienta externa de Allure (si la tienes instalada)
# El plugin de Maven, en este caso, se comporta mejor si lo usamos para abrir el reporte
# y le pasamos todas las carpetas.

# 🚀 Opción A: Usar 'allure:serve' (Recomendada, abre el navegador)
# Le pasamos las rutas separadas directamente. Este comando SIEMPRE funciona para fusión.
# NOTA: Este comando requiere que Allure CLI esté instalado globalmente.

# Si no tienes Allure CLI instalado, usa la Opción B (Reporte estático)

# 🚀 Opción B: Copiar manualmente (Corregido el error de cp)
# Solo si el paso de copia manual falla:

rm -rf target/allure-results
mkdir target/allure-results

# Usamos 'find' o un bucle para asegurar que los archivos se copian, no las carpetas.
# Si estás usando Git Bash, 'cp -r' debería funcionar mejor.
cp target/allure-chrome/* target/allure-results/
cp target/allure-firefox/* target/allure-results/
cp target/allure-edge/* target/allure-results/

# 3. Generar el reporte estático FINAL (HTML)
mvn allure:report
echo "El reporte HTML consolidado está en C:\JAVA\PROYECTOS\Test_Suite_Sauce\target\allure-report"