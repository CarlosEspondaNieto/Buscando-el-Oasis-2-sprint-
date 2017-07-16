### Tomar un  archivo CSV y guardarlo en formato Avro usando Spark y Scala

- Se usó la biblioteca databricks que incluye Avro. 

- Se descargó la versison 1.6.2 de spark:
[spark1.6.2](http://spark.apache.org/downloads.html)

####  Primera prueba usando Scala, se leerá un archivo avro. 

- Descargar el archivo [episodes.avro](https://docs.databricks.com/_static/misc/episodes.avro)

-En el directorio de spark correr como sudo el shell de Scala junto con databrticks con la versión 2.0.1 de avro (por alguna razón, la version 2.0.0 no funciona bien):

`sudo bin/spark-shell --packages com.databricks:spark-avro_2.10:2.0.1`
 
Es probable que salga un error al ejecutarlo: "spark-shell error : Service 'sparkDriver' failed after 16 retries!" 

- Ubicarse en el directorio de spark, dentro de la carpeta bin abrir el archivo : 

`load-spark-env.sh`

y agregar la línea: `export SPARK_LOCAL_IP='127.0.0.1' `

-Ejecutar de nuevo el comando para entrar al shell.

-Importar las bibliotecas

```Scala
import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)

// The Avro records get converted to Spark types, filtered, and
// then written back out as Avro records

val df = sqlContext.read.avro("file_path/episodes.avro")
df.filter("doctor > 5").write.avro("/tmp/output")
```


