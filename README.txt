# Kafka Project README

## Overview

This Kafka Data Pipeline is designed to consume data from a Kafka topic ("user-login"), process the incoming messages, and send the processed data to another Kafka topic ("processed-data") using a Kafka consumer and producer.

## Prerequisites

- Java JDK (version 19 or higher)
- Apache Kafka (version 2.4.0 or higher)
- Docker (version 24.0.6)
- IntelliJ IDEA Community Edition (version 2022.2.1) 

## Installation and Setup

#Github
- Navigate to the directory where you want to download the repository in command line
- Clone the github repository: "git clone https://github.com/Saaketh-Y/Kafka_Installation.git" (without quotes)

# Docker Setup
- Visit https://docs.docker.com/desktop/ and install Docker
- Navigate to the directory in which the files were downloaded on command line/terminal and then execute the command "docker-compose up -d" (without the quotes).
- Run Docker ps in the directory to identify the kafka broker
- Navigate to our desired container by executing the command "docker exec -it kafka_installation-kafka-1 /bin/sh" (without quotes again and choose the correct container from the above command)
- Create a new topic by using the command "kafka-topics --create --topic processed-data --partitions 3 --replication-factor 1 --bootstrap-server localhost:9092" (without quotes)

# IntelliJ setup
- Install IntelliJ community Edition (version 2022.2.1)
- Download any SDK if prompted, I am using jdk-19

# Run
- Run the KafkaFetch.java file
- The program might display a couple of errors regarding the Logger implementation. however, rest assured that the consumer and producer work as intended 

## Design Choices

# Kafka Consumer
- The Kafka consumer subscribes to the "user-login" topic.
- It is configured with the `"earliest"` offset reset policy to ensure processing of data from the beginning of the topic for a new consumer.
- The consumer is designed to run indefinitely, continuously polling for new records.

# Kafka Producer
- The Kafka producer is configured to send processed data to the "processed-data" topic.
- It uses a `try-catch-finally` block to ensure that the producer is properly flushed and closed even in case of exceptions.

# Logging
- SLF4J and Logback are used for logging to provide structured and configurable logging output.
- Log messages include key, value, partition, and offset information for easy troubleshooting.

# Error Handling
- Error handling is implemented within the consumer and producer loops to handle exceptions.

## Data Flow

1. The Kafka consumer continuously polls for new records from the "user-login" topic.
2. For each received record, it logs key, value, partition, and offset information.
3. The record's value (data) is processed, and the processed data is sent to the "processed-data" topic using the Kafka producer.
4. The producer ensures that data is reliably sent to the destination topic.

## Efficiency, Scalability, and Fault Tolerance

# Efficiency
- The Kafka consumer and producer are designed to be efficient by using asynchronous operations for message processing and sending.

# Scalability
- Scalability is achieved by running multiple instances of the Kafka consumer in parallel.
- Load balancing can be implemented to distribute Kafka partitions among multiple consumer instances.
- Kafka topic partitioning can also be optimized for parallel processing.

# Fault Tolerance
- Fault tolerance is enhanced through proper error handling and logging of error details.
- In case of an unexpected error, the pipeline can continue processing other messages.


## Contributors

- Saaketh Yerramsetti(https://github.com/yourusername) - Developer
