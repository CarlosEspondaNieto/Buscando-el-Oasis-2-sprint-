# QUÉ  ES TPT
Es una aplicación cliente que  provee escalabilidad, alta velocidad y transmisión en paralelo.

TPT nos permite cargar y exportar datos desde cualquier base de datos  accesible en Teradata u otro almacen de datos que use operadores TPT.

* Operadores TPT
![operadoresTPT](/TPT/operadoresTPT.png)


# CÓMO FUNCIONA
Usa un flujo de datos que actua como un pipeline entre operadores. El flujo de datos basicamente fluye de un operador a otro.

Teradata soporta los siguientes tipos de ambiente:
* Pipeline Parallelism
* Data Parallelism

## Pipeline Parallelism
Pipeline Parallelism, se logra conectando intancias de operadores atraves de flujos de datos durante un sólo job.
![piplineP](/TPT/piplineP.png)

1. El  operador de exportación de la izquierda extrae datos de un data source y escribe en el data stream.
2. El operador de filtación extrae dayos de data stream, los proces para después escribirlos en otro data stream.
3. El operador de carga  inicia a escribir los datos en el instante que estos están disponibles del data stream.
Estos tres operadores corren su propio proceso, pueden operar independientemente y l mismo tiempo.


## Data Parallelism
Muestra como se puede procesar una gran cantidada de datos, mediante la particion de los datos origen en un numero sets independientes, donde cada particion  es manejada por una instancia seperada de un operador.
![dataP](/TPT/dataP.png)

# Validando TPT después de la instalación 
Recordando que ya tenemos TERADATA instalado el siguente link nos dice como: [INSTALACION DE TERADATA EN VWARE!](https://github.com/Dauzy/S1---Viaje-a-Jupyter/blob/carlos/teradata_instalacionVM.md) .
* Creamos una base de datos y un usuario:
```
CREATE DATABASE pruebas_tpt    AS PERMANENT =  500000;  
CREATE USER tpt_u FROM pruebas_tpt AS PASSWORD = tpt , PERM = 500000;                     
```
* Nos cambiamos al directorio /opt/teradata/client/16.00/tbuild/sample/validate y ejecutamos
```
./tptvalidate.ksh dbccop1 tpt_u tpt
```
* Nos tiene que salir el siguiente mensaje
```
Teradata Parallel Transporter Version 16.00.00.01 64-Bit
Job log: /opt/teradata/client/16.00/tbuild/logs/qsetup1-40.out
Job id is qsetup1-40, running on TDExpress16004_Sles11
Teradata Parallel Transporter SQL DDL Operator Version 16.00.00.01
$DDL: private log specified: ddlprivate.log
$DDL: connecting sessions
$DDL: sending SQL requests
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE_LOG' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE_E1' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE_E2' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: disconnecting sessions
$DDL: Total processor time used = '0.092006 Second(s)'
$DDL: Start : Mon Jul 17 22:26:27 2017
$DDL: End   : Mon Jul 17 22:26:28 2017
Job step MAIN_STEP completed successfully
Job qsetup1 completed successfully
Job start: Mon Jul 17 22:26:24 2017
Job end:   Mon Jul 17 22:26:28 2017
Teradata Parallel Transporter Version 16.00.00.01 64-Bit
Job log: /opt/teradata/client/16.00/tbuild/logs/qstart1-41.out
Job id is qstart1-41, running on TDExpress16004_Sles11
Teradata Parallel Transporter DataConnector Operator Version 16.00.00.01
Teradata Parallel Transporter Load Operator Version 16.00.00.01
$LOAD: private log specified: loadprivate.log
$FILE_READER[1]: Instance 1 directing private log report to 'dtacop-root-3411-1'.
$FILE_READER[1]: DataConnector Producer operator Instances: 1
$FILE_READER[1]: ECI operator ID: '$FILE_READER-3411'
$FILE_READER[1]: Operator instance 1 processing file 'flatfile1.dat'.
$LOAD: connecting sessions
$LOAD: preparing target table
$LOAD: entering Acquisition Phase
$LOAD: entering Application Phase
$LOAD: Statistics for Target Table:  'TARGET_EMP_TABLE'
$LOAD: Total Rows Sent To RDBMS:      10
$LOAD: Total Rows Applied:            10
$LOAD: Total Rows in Error Table 1:   0
$LOAD: Total Rows in Error Table 2:   0
$LOAD: Total Duplicate Rows:          0
$FILE_READER[1]: Total files processed: 1.
$LOAD: disconnecting sessions
$LOAD: Total processor time used = '0.136008 Second(s)'
$LOAD: Start : Mon Jul 17 22:26:44 2017
$LOAD: End   : Mon Jul 17 22:26:50 2017
Job step MAIN_STEP completed successfully
Job qstart1 completed successfully
Job start: Mon Jul 17 22:26:40 2017
Job end:   Mon Jul 17 22:26:50 2017
Teradata Parallel Transporter Version 16.00.00.01 64-Bit
Job log: /opt/teradata/client/16.00/tbuild/logs/qsetup2-42.out
Job id is qsetup2-42, running on TDExpress16004_Sles11
Teradata Parallel Transporter SQL DDL Operator Version 16.00.00.01
$DDL: private log specified: ddlprivate.log
$DDL: connecting sessions
$DDL: sending SQL requests
$DDL: TPT10508: RDBMS error 3807: Object 'SOURCE_EMP_TABLE' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: Rows Inserted: 1
$DDL: disconnecting sessions
$DDL: Total processor time used = '0.040002 Second(s)'
$DDL: Start : Mon Jul 17 22:27:02 2017
$DDL: End   : Mon Jul 17 22:27:03 2017
Job step MAIN_STEP completed successfully
Job qsetup2 completed successfully
Job start: Mon Jul 17 22:26:59 2017
Job end:   Mon Jul 17 22:27:03 2017
Teradata Parallel Transporter Version 16.00.00.01 64-Bit
Job log: /opt/teradata/client/16.00/tbuild/logs/qstart2-43.out
Job id is qstart2-43, running on TDExpress16004_Sles11
Teradata Parallel Transporter DataConnector Operator Version 16.00.00.01
Teradata Parallel Transporter Export Operator Version 16.00.00.01
$EXPORT: private log specified: exportprivate.log
$FILE_WRITER[1]: Instance 1 directing private log report to 'dtacop-root-3431-1'.
$FILE_WRITER[1]: DataConnector Consumer operator Instances: 1
$FILE_WRITER[1]: ECI operator ID: '$FILE_WRITER-3431'
$FILE_WRITER[1]: Operator instance 1 processing file 'flatfile2.dat'.
$EXPORT: connecting sessions
$EXPORT: sending SELECT request
$EXPORT: entering End Export Phase
$EXPORT: Total Rows Exported:  10
$EXPORT: Total Rows Discarded: 0
$EXPORT: disconnecting sessions
$FILE_WRITER[1]: Total files processed: 1.
$EXPORT: Total processor time used = '0.080004 Second(s)'
$EXPORT: Start : Mon Jul 17 22:27:19 2017
$EXPORT: End   : Mon Jul 17 22:27:24 2017
Job step MAIN_STEP completed successfully
Job qstart2 completed successfully
Job start: Mon Jul 17 22:27:15 2017
Job end:   Mon Jul 17 22:27:24 2017
Teradata Parallel Transporter Version 16.00.00.01 64-Bit
Job log: /opt/teradata/client/16.00/tbuild/logs/qcleanup-44.out
Job id is qcleanup-44, running on TDExpress16004_Sles11
Teradata Parallel Transporter SQL DDL Operator Version 16.00.00.01
$DDL: private log specified: ddlprivate.log
$DDL: connecting sessions
$DDL: sending SQL requests
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE_LOG' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE_E1' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE_E2' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: TPT10508: RDBMS error 3807: Object 'TARGET_EMP_TABLE_WT' does not exist.
$DDL: TPT18046: Error is ignored as requested in ErrorList
$DDL: disconnecting sessions
$DDL: Total processor time used = '0.044002 Second(s)'
$DDL: Start : Mon Jul 17 22:27:39 2017
$DDL: End   : Mon Jul 17 22:27:39 2017
Job step MAIN_STEP completed successfully
Job qcleanup completed successfully
Job start: Mon Jul 17 22:27:36 2017
Job end:   Mon Jul 17 22:27:39 2017
TPT Validation completed successfully.
```
# Extracción de una tabla de ejemplo usando TPT
```
tbuild -f export_d.txt  -v jobsvar2.txt -j exportt
```
-f : job a ejecutar
-v : variables
-j : nombre del job
La salida debe ser la siguiente
```
Teradata Parallel Transporter Version 16.00.00.01 64-Bit
Job log: /opt/teradata/client/16.00/tbuild/logs/exportt-51.out
Job id is exportt-51, running on TDExpress16004_Sles11
Teradata Parallel Transporter DataConnector Operator Version 16.00.00.01
Teradata Parallel Transporter Export Operator Version 16.00.00.01
$EXPORT: private log not specified
$FILE_WRITER[1]: Instance 1 directing private log report to 'dtacop-root-6431-1'.
$FILE_WRITER[1]: DataConnector Consumer operator Instances: 1
$FILE_WRITER[1]: ECI operator ID: '$FILE_WRITER-6431'
$FILE_WRITER[1]: Operator instance 1 processing file 'teradata_to_local.csv'.
$EXPORT: connecting sessions
$EXPORT: sending SELECT request
$EXPORT: entering End Export Phase
$EXPORT: Total Rows Exported:  100
$EXPORT: Total Rows Discarded: 0
$EXPORT: disconnecting sessions
$FILE_WRITER[1]: Total files processed: 1.
$EXPORT: Total processor time used = '0.092006 Second(s)'
$EXPORT: Start : Mon Jul 17 23:02:09 2017
$EXPORT: End   : Mon Jul 17 23:02:14 2017
Job step MAIN_STEP completed successfully
Job exportt completed successfully
Job start: Mon Jul 17 23:02:05 2017
Job end:   Mon Jul 17 23:02:14 2017
```

