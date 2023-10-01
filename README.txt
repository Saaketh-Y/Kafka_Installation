# Kafka Project README

## Overview

This Kafka Data Pipeline is designed to consume data from a Kafka topic ("user-login"), process the incoming messages, and send the processed data to another Kafka topic ("processed-data") using a Kafka consumer and producer.

## Prerequisites

- Java JDK (version 19 or higher)
- Apache Kafka (version 2.4.0 or higher)

## Design Choices

### Kafka Consumer
- The Kafka consumer subscribes to the "user-login" topic.
- It is configured with the `"earliest"` offset reset policy to ensure processing of data from the beginning of the topic for a new consumer.
- The consumer is designed to run indefinitely, continuously polling for new records.

### Kafka Producer
- The Kafka producer is configured to send processed data to the "processed-data" topic.
- It uses a `try-catch-finally` block to ensure that the producer is properly flushed and closed even in case of exceptions.

### Logging
- SLF4J and Logback are used for logging to provide structured and configurable logging output.
- Log messages include key, value, partition, and offset information for easy troubleshooting.

### Error Handling
- Error handling is implemented within the consumer and producer loops to handle exceptions.

## Data Flow

1. The Kafka consumer continuously polls for new records from the "user-login" topic.
2. For each received record, it logs key, value, partition, and offset information.
3. The record's value (data) is processed, and the processed data is sent to the "processed-data" topic using the Kafka producer.
4. The producer ensures that data is reliably sent to the destination topic.

## Efficiency, Scalability, and Fault Tolerance

### Efficiency
- The Kafka consumer and producer are designed to be efficient by using asynchronous operations for message processing and sending.

### Scalability
- Scalability is achieved by running multiple instances of the Kafka consumer in parallel.
- Load balancing can be implemented to distribute Kafka partitions among multiple consumer instances.
- Kafka topic partitioning can also be optimized for parallel processing.

### Fault Tolerance
- Fault tolerance is enhanced through proper error handling and logging of error details.
- In case of an unexpected error, the pipeline can continue processing other messages.

## Prerequisites

- Java JDK (version X.X or higher)
- Apache Kafka (version X.X or higher)

## Usage

1. Build the application: `mvn clean install`
2. Run the Kafka consumer application: `java -jar your-application.jar`

## Configuration

- Configure Kafka broker and topic settings in the `consumerProperties` and `producerProperties`.
- Adjust consumer and producer settings based on your use case and requirements.

## Monitoring and Logging

- Use monitoring tools like Prometheus and Grafana to monitor application health and performance.
- Configure log management systems to centralize and analyze logs.

## Contributors

- Saaketh Yerramsetti(https://github.com/yourusername) - Developer
