### Tomar un  archivo CSV y guardarlo en formato Avro usando Spark y Scala

- Se usó la biblioteca databricks que incluye Avro. 

- Se descargó la versison 1.6.2 de spark:
[spark1.6.2](http://spark.apache.org/downloads.html)

####  Primera prueba usando Scala, se leerá un archivo avro. 

- Descargar el archivo [episodes.avro](https://docs.databricks.com/_static/misc/episodes.avro)

-En el directorio de spark correr como sudo el shell de Scala junto con databricks con la versión 2.0.1 de avro (por alguna razón, la version 2.0.0 no funciona bien):

`sudo bin/spark-shell --packages com.databricks:spark-avro_2.10:2.0.1`
 
Es probable que salga un error al ejecutarlo: "spark-shell error : Service 'sparkDriver' failed after 16 retries!" 

- Ubicarse en el directorio de spark, dentro de la carpeta bin abrir el archivo : 

`load-spark-env.sh`

y agregar la línea: `export SPARK_LOCAL_IP='127.0.0.1' `

-Ejecutar de nuevo el comando para entrar al shell.

-Importar las bibliotecas y las siguientes líneas:

```Scala
// import needed for the .avro method to be added
import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)

// The Avro records get converted to Spark types, filtered, and
// then written back out as Avro records

val df = sqlContext.read.avro("file_path/episodes.avro") // ruta donde se guardó el archivo episodes.avro
df.filter("doctor > 5").write.avro("/tmp/output") //ruta donde se van a guardar
```

#### Convertir un archivo csv en avro desde HDFS
-Se hizo un script que toma un csv de HDFS y lo guarda en local. 

- Se ejecuta el spark shell con los paquetes necesarios (com.databricks.avro y com.databricks.csv) y se pasa como  parametro el archivo a ejecutar.

`sudo bin/spark-shell --packages com.databricks:spark-avro_2.10:2.0.1,com.databricks:spark-csv_2.10:1.5.0 script.scala`
 
 -Contenido del script:

```Scala
//Se importan las bibliotecas
import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

// Se carga el SQLContext en una variable
val sqlContext = new SQLContext(sc)

// Se carga el archivo csv desde el HDFS:
val df = (sqlContext.read // Scala no tiene caracter para continuación de líneas, por eso se encierran en paréntesis.
	.format("com.databricks.spark.csv")
	.option("delimiter", "|") 
	.option("header", "false")
	.option("inferSchema", "true")
	.load("hdfs://localhost:9000/landing_zone/teradata/beeva_moock_data1.csv") // se carga desde HDFS
	.write.avro("/raw/beeva")) //Se guarda en formato avro en local

System.exit(0) //Para salir del shell


//También se puede guardar en HDFS
//df.write.avro("hdfs://localhost:9000/carlos/beeva_data")

```
- Para comprobar, vía spark shell: 

```Scala
df.printSchema() // Para ver el esquema de la información
df.show() // Para ver los primeros 20 registros

df.count() // Para contar los registros
```
