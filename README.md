# testDeezerPsl
1.1 Software requerido: 
• SDK o JDK 1.8 JAVA 
• Apache-tomcat-8.0.32 
• Navegador Mozilla Firefox (reciente)

1.2 Ambiente de desarrollo 
• Eclipse IDE for Java EE (usado en desarrollo versión Mart) 
• Maven repository • Repositorio de código GitHub (master): https://github.com/luigisamurai/testDeezerPsl.git

1.3 Importar el Proyecto 
• Acceda al link del repositorio de GitHub y descargue el proyecto testDeezerPsl 
• Descomprima la carpeta descargada en el workspace de Eclipse 
• Desde Eclipse seleccione la opción Archivo, luego importar, busque la carpeta Generar y dentro la opción Proyecto existente en el workspace. siga las instrucciones. 
• Verifique que el SDk o JDK este correctamente asociado al proyecto. De clic derecho, luego propiedades y seleccione la opción Java Build Path y acceda a la pestaña librerías. Tambien verifique que el proyecto tiene los facets de Java y Dinamic Web Module 3.0 o superior 
• Adicione un servidor Apache Tomcat-8 en las pestañas Server. • Despliegue a aplicación y accede a la página principal.

1.4 Estructura del proyecto

Paquete: testdeezerpsl.test Descripción: Almacena las pruebas automatizadas Clases: DeezerApiLogicTest.java : Prueba de unidad del Back End que interactúa con el Api de Deezer. DeezerInterfaceWebTest.java : Prueba End to End que actúan sobre la interfaz index.html

Paquete: testdeezerpsl.controller Descripción: Almacena las clases que comunican las vista y la lógica Clases:
DeezerApiController.java : controlador, servicio Rest que comunica a la vista y la lógica (Back End).

Paquete: testdeezerpsl.logic Descripción: Almacena las clases del modelo y lógica Clases: DeezerApiLogic.java : lógica (Back End) que interactúa con el api de Deezer.

Paquete: testdeezerpsl.resources Descripción: Almacena la clase utilitarias y declaraciones globales Clases: Enums.java: Declaraciones de enumeración globales para la aplicación HttpURLConnections.java: Envía peticione Http.
