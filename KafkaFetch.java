import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * This code demonstrates a Kafka consumer that subscribes to the "user-login" topic, processes
 * incoming messages, and sends processed data to the "processed-data" topic using a Kafka producer.
 * It includes comments explaining the purpose and usage of each section of the code.
 */
public class KafkaFetch {
    public static void main(String[] args) {
        // Initialize a logger using SLF4J
        Logger logger = LoggerFactory.getLogger(KafkaFetch.class.getName());

        // Configure Kafka consumer properties
        Properties consumerProperties = new Properties();
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-consumer-group");
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //Since this is a new consumer, we want to ensure it is processing data from the start

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
        consumer.subscribe(Collections.singletonList("user-login"));

        // Configure Kafka producer properties
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(producerProperties);

        try {

            while (true) {
                // Poll for new records, with a timeout
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    // Process the received Kafka message here
                    logger.info("Key: " + record.key() + ", Value:" + record.value());
                    logger.info("Partition:" + record.partition() + ",Offset:" + record.offset());
                    String key = record.key();
                    String value = record.value();
                    try {
                        //processed data goes here, for now we just directly pass the values
                        //as processed data
                        String processedData = value;
                        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                                "processed-data", key, processedData);
                        producer.send(producerRecord);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the Kafka consumer and producer when the application is done
            if (consumer != null) {
                consumer.close();
            }
            if (producer != null) {
                producer.flush();
                producer.close();
            }

        }

    }
}
