## Definición de instancias con Confluent

- Lo que viene a continuación se hizo en la máquina de cada uno. 

#### Se instaló Confluent:

- Se descargó el paquete zip open source:

 	[confluent](http://docs.confluent.io/3.2.0/installation.html)

- Se probó el Quickstart del sitio web en standalone:
	
	[Quickstart](http://docs.confluent.io/3.2.0/quickstart.html#quickstart)


#### Se definió una arquitectura de tres brokers y tres zookeepers, uno en cada máquina distinta.

##### Configuraciónde zookeeper:
 
- Editar el archivo etc/kafka/zookeeper.properties:
 
```
# the directory where the snapshot is stored.
dataDir=/tmp/zookeeper

# the port at which the clients will connect
clientPort=2181

# disable the per-ip limit on the number of connections since this is a non-production config
maxClientCnxns=0
server.1=0.0.0.0:2888:3888 //es la dirección del nodo 1
server.2=ip_broker2:2888:3888 //la IP del nodo 2
server.3=ip_broker2:2888:3888 // la IP del nodo 3
initLimit=5
syncLimit=2
```

- Crear un archivo myid en el directorio /tmp/zookeeper/ y poner el id del nodo en las tres máquinas.

- Ejemplo: el 1 se pone en la máquina del broker del 1, el 2 en la máquina 2, el 3 en la máquina 3.

```
touch /tmp/zookeeper/myid
  
echo "1" > /tmp/zookeeper/myid

```

- Editar el archivo `etc/kafka/server.properties`:

```bash
The id of the broker. This must be set to a unique integer for each broker.

broker.id=[BROKER_ID]

# A comma seperated list of directories under which to store log files

log.dirs=/tmp/kafka-logs-0

Zookeeper connection string (see zookeeper docs for details).

# This is a comma separated host:port pairs, each corresponding to a zk

# server. e.g. "127.0.0.1:3000,127.0.0.1:3001,127.0.0.1:3002".

# You can also append an optional chroot string to the urls to specify the

# root directory for all kafka znodes.

zookeeper.connect=0.0.0.0:2181,ip_nodo_2:2181,ip_nodo_3:2181

```

- Iniciar los servicios de Zookeeper en cada máquina:

`bin/zookeeper-server-start.sh config/zookeeper.properties`

- Iniciar los servicios del broker en cada máquina:

`bin/kafka-server-start.sh config/server.properties`

- En una de las máquinas crear un  tópic:

```
      bin/kafka-topics.sh --create --zookeeper 0.0.0.0:2181, \ 

      ip_nodo_2:2181,ip_nodo_3:2181 --replication-factor 3 \ 

      --partitions 1 --topic prueba
```

- Levantar un productor en una máquina y un consumidor en las dos restantes:

```
bin/kafka-console-producer.sh --broker-list  0.0.0.0:9092,\

ip_nodo_2:9092,ip_nodo_3:9092 --topic prueba \

bin/kafka-console-consumer.sh --zookeeper0.0.0.0:9092,\ 

ip_nodo_2:9092,ip_nodo_3:9092  --topic prueba --from-beginning

```

-Confirmar que los tres nodos con sus brokers están arriba:

```
bin/kafka-topics --describe --zookeeper  0.0.0.0:9092, ip_nodo_2:9092,ip_nodo_3:9092  --topic prueba
```
Se debe ver algo así: 

```
Topic:prueba  PartitionCount:1    ReplicationFactor:3 Configs:
    Topic: prueba Partition: 1    Leader: 0   Replicas: 0,1,2 Isr: 0,1,2
```

- Enviar un mensaje desde el productor para verlo en el consumidor. 

`Mensaje desde productor`

- Tumbar broker y Zookeeper en una máquina, dejando el consumer arriba.

-Obtener los id's de los procesos: 
 
```ps -aux | grep kafka

   ps -aux | grep Zookeeper
```

- Tirarlos:

```
sudo kill -9 pid --> id del proceso de kafka

sudo kill -9 pid  --> id del proceso zookeeper
```

- Tumbar zokeeper en otra máquina, dejando sólo el broker, dejando el consumer arriba.

```ps -aux | grep Zookeeper

sudo kill -9 pid  --> id del proceso zookeeper
```
- Tumbar broker en otra máquina, dejando solo el zookeeper, dejando el producer arriba.

```ps -aux | grep kafka

sudo kill -9 pid --> id del proceso de kafka```

- Enviar mensaje para confirmar que los consumers reciben aún.

`Enviando mensaje ...`

- Confirmar que solo un nódo está arriba:

```
bin/kafka-topics --describe --zookeeper  ip_broker_vivo:9092  --topic prueba
```






