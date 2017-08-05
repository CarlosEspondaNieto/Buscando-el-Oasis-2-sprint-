import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)
val avroDF = sqlContext.read.avro("hdfs://carlos:9000/raw/file.avro")
avroDF.write.parquet("hdfs://carlos:9000/master/archivo.parquet")
val parqfile = sqlContext.read.parquet("hdfs://carlos:9000/master/archivo.parquet")
parqfile.registerTempTable("records")
val allrecords = sqlContext.sql("SELECT * FROM records")
println("Comprobando Esquema: \n")
allrecords.printSchema()
val valcount = allrecords.count()
println("Conteo de registros: \n" + valcount)
val valfirst = allrecords.first()
println("Primer registro: \n" + valfirst)
