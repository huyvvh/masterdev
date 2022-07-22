package kafka;

import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import serializzer.Deserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.26:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "protobuf-consumer-group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer .class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "serializzer.Deserializer");

        properties.put(KafkaProtobufDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://172.17.80.26:8081");
        properties.put(KafkaProtobufDeserializerConfig.SPECIFIC_PROTOBUF_VALUE_TYPE, DataTrackingOut.DataTracking.class.getName());

        KafkaConsumer<String, DataTrackingOut.DataTracking> consumer = new KafkaConsumer<>(properties,new StringDeserializer(), new Deserializer<>(DataTrackingOut.DataTracking.parser()));

        consumer.subscribe(Collections.singleton("data_tracking_huyvv20"));

        try {
            while (true) {
                ConsumerRecords<String, DataTrackingOut.DataTracking> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, DataTrackingOut.DataTracking> record : records) {
                    System.out.printf("offset = %d, key = %s,\n value = %s \n", record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
