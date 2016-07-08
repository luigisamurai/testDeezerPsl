# testDeezerPsl

1.1 Software requerido: 
• SDK o JDK 1.8 JAVA 
• Apache-tomcat-8.0.32 
• apache-maven-3.3.9
• Navegador Mozilla Firefox (version reciente)

2. Configurar Apache Tomcat
• Configure tomcat el usuario de tomcat que accedera al manage, en el archivo lib/tomcat-user.xml adicione las siguientes lineas:
  <role rolename="manager"/>
  <user username="tomcat" password="s3cret" roles="manager"/>
  <role rolename="manager-gui"/>
  <user username="tomcat" password="s3cret" roles="manager-gui"/>
• En la carpeta .m2 (ej: C:\Users\luigi\.m2) debe existir el archivo settings.xml donde username y password deben coincider con el      usuario de tomcat
  <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
   xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
   http://maven.apache.org/xsd/settings-1.0.0.xsd">
   <localRepository/>
   <interactiveMode/>
   <usePluginRegistry/>
   <offline/>
   <pluginGroups/>
   <servers>
     <server>
       <id>TomcatServer</id>
       <username>tomcat</username>
       <password>s3cret</password>
     </server>
   </servers>
   <mirrors/>
   <proxies/>
   <profiles/>
   <activeProfiles/>
  </settings>


Ejecutar el proyecto (Maven)
• Descargue y descomprima el proyecto desde el repositorio github: https://github.com/luigisamurai/testDeezerPsl.git.
• Mediante consola acceda a la carpeta raíz del proyecto (donde se encuentra el archivo pom.xml)
• Ejecute el comando mvn compile
• Ejecute el comando mvn spring-boot:run
• En una nueva consola acceda a la carpeta raiz del proyecto (donde se encuentra el archivo pom.xml)
• Ejecute el comando mvn test

