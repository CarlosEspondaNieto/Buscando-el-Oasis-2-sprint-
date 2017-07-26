## Mongo

- Tener instalado MongoDb Community Edition, seguir pasos de instalación de la página oficial de acuerdo a la distribución linux que se tenga en la máquina, en este caso fue Centos. 

[instalacion-mongo](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat/)

- Antes de iniciar los servicios de Mongo modificar el archivo `sudo vim /etc/mongod.conf`

```
# network interfaces
net:
  port: 27017
  bindIp:192.168.1.0  # Interfaz local en la cual va a escuchar. Si se comenta va escuchar en todas ls interfaces.  
```

- Iniciar los servicios:

`sudo service mongod start`

-Verificar:

`sudo cat /var/log/mongodb/mongod.log`

- 
- Desde la versión 3.4 el storage engine default de mongo es wiredTiger

* Para más info: [wiredTiger](https://docs.mongodb.com/manual/tutorial/change-standalone-wiredtiger/)

- Se debe crear un directorio para la instancia de mongod que va correr con el 
storage engine wiredTiger. 

- Crear con sudo la carpeta /data/db

`sudo mkdir -p /data/db`

- Cambiar permisos a la carpeta:

`sudo chmod 0777 /data/db`

`sudo chmod 0777 /data/`


Cambiar los propietarios:

`sudo chown -R mongod:mongod /data/db` 

`sudo chown -R mongod:mongod /data`

Abrir el archivo de configuración `sudo vim /etc/mongod.conf`

Cambiar la línea `#  engine` por `engine:wiredTiger`


Iniciar los servicios de mongo
`mongod` 

El mongo shell:
`mongo`



If the current version is supported the wiredTiger;

    Get the backup of the current database using mongodump command
    Stop the mongod service using sudo service mongod stop command
    Add storageEngine=wiredTiger text as the first line of mongod.conf file
    Delete the all file on /var/lib/mongodb (or /data/db folder if used)
    [This is important. Because MongoDB cannot convert the current MMAP db files to wiredTiger format]
    Start the mongod service using sudo service mongod start command
    Restore the database from the backup using [mongorestore][2] command
    wiredTiger is being used...


- Si no es la primera vez que se va usar esa versión de mongo, se debe eliminar el contenido de la carpeta `/data/db/`
(Ojo: cuidado, se recomienda hacer un respaldo de las bases de datos guardadas en Mongo, si es que
el usario creó unas anteriormente )
 


