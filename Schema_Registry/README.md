# SCHEMA REGISTRY
## Qué es schema registry
* Es una herramienta que nos permite almacenar y recuperar esquemas Avro, almacenando un historial de las versiones  de todos los esquemas, además proporciona multiples configuraciones de compatibilidad por si fuera poco permite la evolución de los esquemas de acuerdo a la configuración de compatibilidad.
Proporciona serializadores que se conectan a los clientes de Kafka para la recuperación y almacenamiento de mensajes de Kafka que se envian en formato Avro.

* Estamos dando por hecho que ya tenemos todos nuestros servicios levantados, si no es el caso podemos consultar esto [Levantar servicios](https://github.com/CarlosEspondaNieto/Buscando-el-Oasis-2-sprint-/tree/Daniel/mongo-sink-connector)
* En este proceso tenemos que  hacer dos sencillas configuraciones
## 1. Cambiar la configuración  de schema-registry.properties que se encuentra en
 **/etc/schema-registry**
```
listeners=http://0.0.0.0:8081
**kafkastore.connection.url=IP:2181,IP:2181,IP:2181**
kafkastore.topic=_schemas
debug=false
```
* Lo único qu etenemos qu eagregar son las IP's de nuestros nodos en **kafkastore.connection.url**

## 2. Definir el esquema en avro
* Éste es un ejemplo  de los registros 
```JSON
{"ID":3,
"name":"Raphael",
"lastname":"Sanzio",
"age":21,
"hobbies":["cinema","dancing"],
"address":{"street":"San Antonio",
	 "number":"77", 
	 "districts":"Del valle", 
	 "country": "CDMX"}
}
```
* Definimos nuestro esquema avro 
```AVRO
{"type":"record","name":"myrecord","fields":[ 
{"name":"ID","type":"int"}, 
{"name":"name", "type": "string"},
{"name":"last_name", "type": "string",
{"name":"age", "type": "int"},
{"name":"hobbies","type": {"type": "array", "items": "string"}}
,
{"name":"address","type":{"type":"record","name":"myaddress",
	"fields":[
	{"name":"street","type":"string"},
	{"name":"number","type":"string"},
	{"name":"districts","type":"string"},
	{"name":"country","type":"string"}
	]

}
}
]}
```
* Ahora sí ya podemos iniciar un  productor
```
./bin/kafka-avro-console-producer \
 --broker-list IP:9092,IP:9092,IP:9092 --topic  Ninja_Turtles\
 --property value.schema='{"type":"record","name":"myrecord","fields":[ 
{"name":"ID","type":"int"}, 
{"name":"name", "type": "string"},
{"name":"last_name", "type": "string"},
{"name":"age", "type": "int"},
{"name":"hobbies","type": {"type": "array", "items": "string"}}
,
{"name":"address","type":{"type":"record","name":"myaddress",
	"fields":[
	{"name":"street","type":"string"},
	{"name":"number","type":"string"},
	{"name":"districts","type":"string"},
	{"name":"country","type":"string"}
	]

}
}
]}'
```
* Tabién podemos hacer uso de una herramienta Online de JSONtoAVRO
[Herramienta Online](http://avro4s-ui.landoop.com/)