# Convertir AVRO a PARQUET con spark

Veremos como hacer la conversion de un archivo avro a formato parquet y enviarlo a HDFS. para este tutorial se asume que tienes instalado spark en su version 1.6.2 y scala en su version 2.11.

## Importar la libreria de databricks
```
bin/spark-shell --packages com.databricks:spark-avro_2.11:2.0.1
```

* Una vez dentro del shell de spark, importaremos las siguientes librerias.
```scala
import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext
val sqlContext = new SQLContext(sc)
val df = sqlContext.read.avro("src/test/resources/episodes.avro")
df.filter("doctor > 5").write.avro("/tmp/output")
```
