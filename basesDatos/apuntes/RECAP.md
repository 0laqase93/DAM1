# Unidad C0: Recapitulación

Vicente Casares.

Este documento es el primero que hacemos para bases de datos, en él podemos encontrar lo más básico de una base de datos: desde la propia definición hasta lo que se usa para administrarlas (SQL), su forma de conectar los datos y la utilidad que traen a la administración de la información.  

Una base de datos es una recopilación de datos estructurados almacenados de una manera ordenada, que se usan para administrar grandes cantidades de información. Puede contener cualquier tipo de datos.

## Concepto y origen de las bases de datos
**¿Qué son las bases de datos? ¿Qué problemas tratan de resolver? Definición de base de datos.**  
Las bases de datos son una estrura donde se pueden almacenan los datos para acceder a ellos de manera ordenada y eficiente, permite relacionar los datos entre ellos para mejorar la eficiencia de la búsqueda y evitar datos erroneos.  

Principalmente lo que se busca es la búsqueda de data rápida y eficiente, sin contar que es muy útil para mantener los datos de forma segura ya ccesible para los usuarios y administradores.

## Sistemas de gestión de bases de datos
**¿Qué es un sistema de gestión de bases de datos (DBMS)? ¿Qué características de acceso a los datos debería proporcionar? Definición de DBMS.**  

Un **s**istema **g**estor de **b**ases de **d**atos (SGBD) es un software que permite al usuario crear y utilizar una bases de datos. Un SGBD garantiza la integridad de los datos y la persistencia de los mismo, es decir, permite hacer backups de los datos por si surgen posibles fallos o hackeos no deseados. También permite acceder a los datos de forma concurrente, esto quiere decir que permite acceder varios usuarios al mismo tiempo. Priporciona cierta abstracción del método de almacenamiento, es decir, nosotros no le decimos cómo debe guardar los datos, solo le decimos lo que tiene que guardar. La seguridad de los datos y la confidencialidad es algo muy importante que nos proporciona los SGBD al igual que un control de acceso para que no pueda acceder a todos los datos cualquier persona, esto de maneja gestionando los permisos que puede tener cada usuario o incluso cada 'rol'. Una de las características más destacables es que permite acceder a los datos de forma eficiente.  

### Ejemplos de sistemas de gestión de bases de datos
**¿Qué DBMS se usan a día de hoy? ¿Cuáles de ellos son software libre? ¿Cuáles de ellos siguen el modelo cliente-servidor?**  
Las ventajas del modelo cliente-servidor es la escalabilidad, esto se refiere al tamaño del proyecto, si se quiere un modelo muy grande se necesita escalabilidad ya que la sincronización de varias sedes sin este modelo pueda ser más compleja por que en los que no utilizan el modelo cliente-servidor se almacena de forma local y esto puede llevar a fallos de sincronización.  

El software libre implica que el usuario tiene ciertos privilegios: adaptarlos, compartir las adaptaciones, modificar el código, etc... En la lista posterior Oracle DB, SQLite, MariaDB, PostgreSQL y muSQL son de  software libre.

**Más conocidos**
* Oracle DB
* IBM Db2
* SQLite -> **no** sigue el modelo cliente-servidor (almacena los datos de forma local).
* MariaDB
* SQL Server
* PostgreSQL
* mySQL

## Modelo cliente-servidor
**¿Por qué es interesante que el DBMS se encuentre en un servidor? ¿Qué ventajas tiene desacoplar al DBMS del cliente? ¿En qué se basa el modelo cliente-servidor?**  

- Imagen del modelo cliente-servidor sacada de google: https://infimg.com/bimg/2019/02/diagrama-cliente-servidor.jpeg  
- Imagen de un esquema desarrolado en clase por Crístian (Profesor de bases de datos): https://hhtpreview.s3.us-east-2.amazonaws.com/whiteboard_oem/20240215081317643060B0C2A1928/1.pdf

El modelo cliente-servidor se basa en un dos partes, el cliente que busca la información y manda peticiones SQL al server y el server que es el sofware que almacena la bases de datos o el servicio(ejecuta, pone a disposicion una serie de recuersos...) y devuelve la respuesta que buscaba el cliente. El SGBD está en el server, que a su vez tiene la base de datos. El cliente accede al server mediante una red (capa física) a través de peticiones:  
`(Cliente --(Petición)-> Server) (Server --(Respuesta)-> Cliente)`  
No antes sin abrir puertos (conexión) de escucha (server) a través de una dirección IP (TCP/IP -> 127.0.0.1:33006). Peticiones (SQL)

* __Cliente__: Software que hace peticiones a otro programa.
* __Servidor__: Software donde se aloja la base de datos y da la respuesta al cliente.
* __Red__: Lugar por donde viajan las peticiones y las respuestas.
* __Puerto de escucha__: Es el puerto por donde entran y salen las peticiones. Cuaando envías una petición se habre un puerto de escucha esperando la respuesta.  
* __Petición__: Las peticiones a bases de datos se hacen con SQL, el usuario escribe qué es l o que busca de la bases de datos y lo envía.
* __Respuesta__: Son los registros que devuelve la base de datos en base a la petición enviada por el usuario.

## SQL
**¿Qué es SQL? ¿Qué tipo de lenguaje es?**  

**S**tructure **Q**uery **L**anguage o SQL es un lenguaje de consulta, declarativo, estandarizado por dos agencias de enstandarización (ANSI (en 1989) e ISO) y de cuarta generación (Se le dice al ordenador qué debe hacer no como -> muy alto nivel). Se usa para acceder a cualquier bases datos relacional -> SQL (también hay no relacionales --> NoSQL), la diferencia entre relacional y no relacional se basa en el diseño. En la relacional los datos van a estar íntimamente relacionados (por ejemplo una red social) , en la no relacional están menos relacionados (por ejemplo transacciones sencillas).

### Instrucciones de SQL

* __DDL:__ **D**ata **D**efinition **L**enguaje: "CREATE", "ALTER", "RENAME", etc...  
* __DML:__ **D**ata **M**anipulation **L**enguaje: ***"SELECT"***, "INSERT", "UPDATE", "DELETE", etc...  
* __DCL:__ **D**ata **C**ontrol **L**anguage (control de acceso): "GRANT", "REVOKE".  
* __TCL:__ **T**ransaction **C**ontrol **L**enguaje (Control de transacciones): "COMMIT", "ROLLBACK", etc...  

## Bases de datos relacionales
**¿Qué es una base de datos relacional? ¿Qué ventajas tiene? ¿Qué elementos la conforman?**  
Los datos están estructurado en forma de tablas (la forma más eficiente) o relación, esta tabla contiene atributos (columnas) o campos, las filas representan los registros o tuplas(conjunto de números separados por comas)

* __Relación (tabla)__: Usuario, Libro, Foto, etc...
* __Atributo/Campo (columna)__: Nombre, Autor, URL, etc...
* __Registro/Tupla (fila)__: Víctor, García Lorca, https://imagen.com