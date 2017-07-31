import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

val sqlContext = new SQLContext(sc)
val avroDF = sqlContext.read.avro("src/test/be-datio/part-r-00000-d7bcd31a-c121-4809-bfa2-617b1e0253ee.avro")
avroDF.write.parquet("src/test/parquet/out/archivo.parquet")
val parqfile = sqlContext.read.parquet("src/test/parquet/out/archivo.parquet")
parqfile.registerTempTable("records")
val allrecords = sqlContext.sql("SELECT * FROM records")
println("Comprobando Esquema: \n")
allrecords.printSchema()
val valcount = allrecords.count()
println("Conteo de registros: \n" + valcount)
val valfirst = allrecords.first()
println("Primer registro: \n" + valfirst)
