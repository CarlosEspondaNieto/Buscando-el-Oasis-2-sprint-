# Convertir AVRO a PARQUET con spark

Veremos como hacer la conversion de un archivo avro a formato parquet y enviarlo a HDFS. para este tutorial se asume que tienes instalado spark en su version 1.6.2 y scala en su version 2.11.

### ¿Que es parquet?
Es un formato de almacenamiento columnar para Hadoop, su gran caracteristica es que soporta codificacion y compresion eficiente

## Importar la libreria de databricks y pasamos el script de scala
```
sudo bin/spark-shell --packages com.databricks:spark-avro_2.10:2.0.1 -i your_path/ConverttoParquet.scala
```

* Una vez dentro del shell de spark, importaremos las siguientes librerias.
```scala
//Importamos las librerias necesarias
import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

// Instanciamos un sqlCOntext
val sqlContext = new SQLContext(sc)
// Leemos el archivo .avro
val avroDF = sqlContext.read.avro("your_path/file.avro")
// Convertimos a parquet y leemos el archivo
avroDF.write.parquet("your_path/file.parquet")
val parqfile = sqlContext.read.parquet("your_path/file.parquet")
// Registramos la tabla
parqfile.registerTempTable("records")
// Hacemos una consulta, imprimimos el esquema, hacemos un count y mostramos el primer registro
val allrecords = sqlContext.sql("SELECT * FROM records")
println("Comprobando Esquema: \n")
allrecords.printSchema()
val valcount = allrecords.count()
println("Conteo de registros: \n" + valcount)
val valfirst = allrecords.first()
println("Primer registro: \n" + valfirst)
```
