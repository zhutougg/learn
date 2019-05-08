# Spring Boot Kafka Example - The Practical Developer

## Basic configuration

This sample application shows how to use basic Spring Boot configuration to set up a producer to a topic with multiple partitions and a consumer group with three different consumers.

The full explanation is on The Practical Developer website: [Spring Boot and Kafka - Practical Configuration Examples](https://thepracticaldeveloper.com/2018/11/24/spring-boot-kafka-config/).

[![Kafka Configuration Example](img/kafka-configuration-example.jpg)](https://thepracticaldeveloper.com/2018/11/24/spring-boot-kafka-config/)

## Multiple serialization / deserialization formats

To illustrate the different configuration options, this application deserializes Kafka messages in three different ways:

* As a JSON to Java object.
* As a simple String (plain JSON).
* As a byte array.

## Docker compose

This code includes a `docker-compose.yml` file so you can use Docker Compose to start up Kafka, no installation needed.

## Did I help you?

Give a  star to this project and/or [buy me a coffee](https://buymeacoff.ee/ZyLJNUR) 😄


https://thepracticaldeveloper.com/2018/11/24/spring-boot-kafka-config/


https://www.tutorialspoint.com/apache_kafka/apache_kafka_installation_steps.htm
kafka版本
kafka_2.11-2.2.0
window版本
https://blog.csdn.net/u010283894/article/details/77106159
官方命令
https://kafka.apache.org/21/documentation.html#basic_ops
group命令
https://www.jianshu.com/p/58276dd6e0e8
rest命令
https://www.cnblogs.com/huxi2b/p/7284767.html

安裝zookeeper
修改config/zoo_example.cfg 改成zoo.cfg
添加dataDir=D:/software/zookeeper-3.4.14/data
啓動zkServer.cmd

安装kafka(kafka_2.11-2.2.0)
修改 server.properties   log.dirs=D:/software/kafka_2.11-0.9.0.0/kafka-logs
启动 kafka-server-start.bat D:/software/kafka_2.11-2.2.0/config/server.properties

创建
kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1   --partitions 1 --topic Hello-Kafka 
查看主题
kafka-topics.bat --list --zookeeper localhost:2181
模拟发消息
kafka-console-producer.bat --broker-list localhost:9092 --topic Hello-Kafka
消费消息
kafka-console-consumer.bat --bootstrap-server=localhost:9092 --topic Hello-Kafka --from-beginning --consumer-property group.id=test-group1


