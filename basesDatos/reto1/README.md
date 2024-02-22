# Reto 1: Consultas básicas

Vicente Casares.


En este reto trabajamos con la base de datos `sanitat`, que nos viene dada en el fichero `sanitat.sql`. A continuación realizamos una serie de consultas para extraer la información pedida en cada uno de los enunciados.  

El código fuente correspondiente a este reto puede consultarse en: https://gitlab.com/vicentecasares/BBDD_1DAM

## Query 1
Queremos ver las columnas `HOSPITAL_COD`, `NOM` y `TELEFON` de todos los elementos de la tabla `HOSPITAL`, para ello hacemos una query simple en SQL utlizando `SELECT` como se ve acontinuación:

```sql
-- query1
SELECT H.HOSPITAL_COD, H.NOM, H.TELEFON
FROM HOSPITAL AS H;
```

## Query 2
Si se quiere mostrar los hospitales existentes (`HOSPITAL_COD`, `nombre` y `teléfono`) que tengan un letra 'A' en la segunda posición, se pueden hacer de varias maneras.  
Por mi cuenta conseguí hallar una solución usando carácteres comodines como puede ser `_` que sirve para cuando hay solo un carácter, si se ponen dos pueden haber dos y así consecutivamente y el `%` que sirve para un número indeterminado de carácteres. Tambíen podemos apreciar el `LIKE` que la propia palabra ya lo comenta, el campo `NOM` tiene que ser **como** '_A%'.

```sql
-- query2
SELECT H.HOSPITAL_COD, H.NOM, H.TELEFON
FROM HOSPITAL AS H
WHERE H.NOM LIKE ‘_A%’;
```

En clase tambíen encontramos otra posible solución utilizando `substring`. Esta función permite crear un cadena auxiliar de otra cadena en base a unos valores, por ejemplo, si tenemos un campo que sea nombre y apellidos y queremos sacar el nombre sabiendo la longitud del nombre podemos utilizar esta función para sacarla.

```sql
-- query2
SELECT H.HOSPITAL_COD, H.NOM, H.TELEFON
FROM HOSPITAL AS H
WHERE substring(NOM, 2, 1) = 'A';
```

## Query 3
En esta query se busca mostrar datos de los empleados, para ellos recurrimos a la tabla de `PLANTILLA` los campos de `HOSPITAL_COD`, `SALA_COD`, `EMPLEAT_NO` y `COGNOM`. Para ellos haces una query simple como hicimos en la *query1* posteriormente comentada.

```sql
-- query3
SELECT P.HOSPITAL_COD, P.SALA_COD, P.EMPLEAT_NO, P.COGNOM
FROM PLANTILLA AS P;
```

## Query 4
Si se quiere obtener los mismo campos de la query anterior (`HOSPITAL_COD`, `SALA_COD`, `EMPLEAT_NO` y `COGNOM`) de la tabla `PLANTILLA` que no trabajen en turno de noche. En este caso se utiliza un operador lógico, en concreto el de negación `!=`. La función de este operador es básicamente encontrar un resultado que no sea el que se especifica. Se aplica en el campo de `TORN` que es donde se especifíca en turno donde 'N' es turno de noche.

```sql
-- query4
SELECT P.HOSPITAL_COD, P.SALA_COD, P.EMPLEAT_NO, P.COGNOM
FROM PLANTILLA AS P
WHERE P.TORN != 'N';
```

## Query 5
Si se quieren buscar los enfermos nacidos en un año concreto debemos usar la tabla `MALALT`. Podemos destacar el `*`, esto hace referencia a todas las columnas de la tabla. También podemos observar una función no vista anteriormente que es `YEAR()`, la función de esta función (valga la redundancia) es básicamente extraer el año de una fecha, en este caso es perfecto ya que queremos encontrar todos los enfermos nacidos en 1960. 

```sql
-- query5
SELECT *
FROM MALALT AS M
WHERE YEAR(M.DATA_NAIX) = 1960;
```

## Query 6
En este caso se buscan los enfermos que hayan nacido a partir del año 1960 (incluido). Para ello solo deberíamos modificar un poco la *query5* que hemos hecho anteriormente utilizando un operador lógico diferente. En este caso se `>=` que él de por sí ya se describe bastante bien, quiere decir *mayor o igual que*.   

```sql
-- query6
SELECT *
FROM MALALT AS M
WHERE YEAR(M.DATA_NAIX) >= 1960;
```