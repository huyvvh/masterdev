package kafka;

import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.26:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "serializzer.Serializer");
        properties.put(KafkaProtobufSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://172.17.80.26:8081");

        String topic = "data_tracking_huyvv20";

        KafkaProducer<String, DataTrackingOut.DataTracking> producer = new KafkaProducer<>(properties);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long timestp = Long.parseLong(sdf1.format(timestamp));

        //prepare the message
        DataTrackingOut.DataTracking dataTrackingOut = DataTrackingOut.DataTracking.newBuilder()
                .setVersion("002")
                .setName("HUYVV21")
                .setTimestamp(timestp)
                .setPhoneId("113")
                .setLon(987654321)
                .setLat(269200000)
                .build();

        System.out.println(dataTrackingOut);
        while (true){
            TimeUnit.SECONDS.sleep(5);
            ProducerRecord<String, DataTrackingOut.DataTracking> record = new
                    ProducerRecord<String, DataTrackingOut.DataTracking>(topic, "DataTrackingOut", dataTrackingOut);

            producer.send(record);

        }

    }
}
