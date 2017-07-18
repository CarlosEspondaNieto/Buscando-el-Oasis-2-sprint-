# Como Instalar Confluent Platform

### ¿ Que es Confluent Platform ?
Es una plataforma de streaming que nos permite administrar, organizar y manipular datos de distintas fuentes. Confluent Platform nos brinda distintas herramientas y facilidad para conectar a fuentes distintas de datos, aplicaciones.

Confluent Platform esta disponible como Confluent Open Source y Confluent Enterprise, para este tutorual se usa Confluent Open Source.

## Confluent Open Source
Tiene 4 componentes importantes que nos facilitan iniciar con nuestra plataforma de streaming basada en kafka.

#### Confluent Kafka Connectors
Este componente aprovecha la API de kafka Connect para conectar Kafka con otros sistemas. Entre los distintos conectores se encuentran Confluent JDBC connector, HDFS Connector,  Elasticsearch Connector, S3 Connector.

#### Confluent Kafka Clients
Este componente nos brinda clientes para integrarlos con distintos lenguajes de programacion como c/c++, python, go, .Net.

#### Confluent Schema Registry
Este componente nos brinda dar un formato comun para que los distintos servicios interaccionen entre si. Lo cual permite una evolucion segura de los esquemas centralizando la gestion de esquemas escritos para el sistema de serializacion Avro. Hace un seguimiento de todas las versiones en cada topico y solo permite la evolucion del esquemas de acuerdo con la configuración de compatibilidad definida por el usuario. Lo cual permite a los desarrolladores cambiar los esquemas sin romper algun servicio

#### Confluent Kafka REST Proxy

Este componente nos facilita trabajar con Kafka con cualquier lenguaje de programacion, ya que nos proporciona un servicio HTTP RESTFULL para interactuar con los clusters de kafka. Proporciona toda la funcionalidad de enviar, leer mensajes individualmente o a un consumer group, inspeccionar metadata de los clusters como la lista de topicos y configuraciones. Por ultimo tambien se puede integrar con el Schema Registry.
