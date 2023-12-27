# Codigo ejemplo de Hadoop MapReduce

Esta carpeta contine el codigo utilizado en el taller de bases de Hadoop, donde se ejemplifico como crear algunos jobs y como procesar información y alojarla en Hadoop.

Podras encontrar un ejemplo en Java y otro en Scala.

**Comandos Hadoop**

```shell
hdfs dfs -mkdir /user/normal
hdfs dfs -mkdir /user/normal/output
hdfs dfs -chmod -R 777 /user/normal
hdfs dfs -copyFromLocal DATASETS/temperatures.csv /user/normal
hdfs dfs -copyFromLocal DATASETS/cities.csv /user/normal
```