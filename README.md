# Spring Boot Kafka Producer and Consumer Example

#How to setup

1. Download kafka version 2.12-2.8.1 (work on window 10)
   - https://dlcdn.apache.org/kafka/2.8.1/
2. Run zookeeper.
    - command: .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
3. Run kafka
    - command: .\bin\windows\kafka-server-start.bat .\config\server.properties
4. Create topic
   - .\bin\windows\kafka-topics.bat –create –zookeeper localhost:2181 –replication-factor 1 –partitions 10 –topic orders