import com.databricks.spark.avro._
import org.apache.spark.sql.SQLContext

// Se carga el SQLContext en una variable
val sqlContext = new SQLContext(sc)

// Se carga el archivo csv desde el HDFS:
val df = (sqlContext.read 
	.format("com.databricks.spark.csv")
	.option("delimiter", "|") 
	.option("header", "false")
	.option("inferSchema", "true")
	.load("hdfs://carlos:9000/landing_zone/teradata/beeva_moock_data1.csv")
	.write.avro("hdfs://carlos:9000/raw/file.avro")) //Se guarda en formato avro en local

val df2 = sqlContext.read.avro("hdfs://carlos:9000/raw/file.avro")


//También se puede guardar en HDFS
//df.write.avro("hdfs://localhost:9000/carlos/beeva_data")
println("Imprimiendo esquema: \n")
df2.printSchema() // Para ver el esquema de la información
println("Imprimiendo el primer registro: \n")
df2.first() // Para ver los primeros 20 registros
println("Imprimiendo conteo de registros: \n")
df2.count() // Para contar los registros

//System.exit(0)
