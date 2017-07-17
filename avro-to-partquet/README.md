# Convertir AVRO a PARQUET con spark

Veremos como hacer la conversion de un archivo avro a formato parquet y enviarlo a HDFS. para este tutorial se asume que tienes instalado spark en su version 1.6.2 y scala en su version 2.11.

## Importar la libreria de databricks
```
bin/spark-shell --packages com.databricks:spark-avro_2.10:2.0.1
```

* Una vez dentro del shell de spark, importaremos las siguientes librerias.
```scala
//Importamos las librerias necesarias
import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

// Instanciamos un sqlCOntext
val sqlContext = new SQLContext(sc)
// Leemos el archivo .avro
val avroDF = sqlContext.read.avro("src/test/be-datio/part-r-00000-d7bcd31a-c121-4809-bfa2-617b1e0253ee.avro")
// Convertimos a parquet y leemos el archivo
avroDF.write.parquet("src/test/parquet/out/archivo.parquet")
val parqfile = sqlContext.read.parquet("src/test/parquet/out/pruebas.parquet")
// Registramos la tabla
parqfile.registerTempTable("pruebas")
// Hacemos una consulta, imprimimos el esquema, hacemos un count y mostramos el primer registro
records = sqlContext.sql("SELECT * FROM pruebas")
records.printSchema()
records.count()
records.show()
```
