--zookeeper localhost:2181  
--bootstrap localhost:9092 
--break

--list
--create
--describle

ReplicationFactor:2  ReplicationFactor tells how many replicas there are.
Replicas: 3,1        Replicas lists the broker id of all the replicas.



topic有不同的partion 每个partion 同一个group只能消费同一个partion 一次。 
查看group
kafka-consumer-groups.bat  --bootstrap-server localhost:9092 --list 
查看group offset
kafka-consumer-groups.bat  --bootstrap-server localhost:9092 --describe  -group p1
创建group
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --consumer-property group.id=p1 
普通生产数据
kafka-console-producer.bat --broker-list localhost:9092 --topic test
重置group offset
kafka-consumer-groups.bat  --bootstrap-server localhost:9092 --group p1 --topic test --reset-offsets --to-offset 5 --execute