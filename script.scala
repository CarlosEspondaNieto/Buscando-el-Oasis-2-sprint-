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
	.load("hdfs://localhost:9000/landing_zone/teradata/beeva_moock_data1.csv")
	.write.avro("/raw/beeva_")) //Se guarda en formato avro en local



//También se puede guardar en HDFS
//df.write.avro("hdfs://localhost:9000/carlos/beeva_data")

//df.printSchema() // Para ver el esquema de la información
//df.show() // Para ver los primeros 20 registros

//df.count() // Para contar los registros

System.exit(0)


