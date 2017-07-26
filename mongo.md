## Mongo

- Tener instalado MongoDb Community Edition, seguir pasos de instalación de la página oficial de acuerdo a la distribución linux que se tenga en la máquina, en este caso fue Centos. 

[instalacion-mongo](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-red-hat/)

- Antes de iniciar los servicios de Mongo modificar el archivo `sudo vim /etc/mongod.conf`

```
# network interfaces
net:
  port: 27017
  bindIp:192.168.1.0  # Interfaz local en la cual va a escuchar el proceso mongo. Si se comenta va escuchar en todas ls interfaces.  
```

- Iniciar los servicios:

`sudo service mongod start`

-Verificar:

`sudo cat /var/log/mongodb/mongod.log`

