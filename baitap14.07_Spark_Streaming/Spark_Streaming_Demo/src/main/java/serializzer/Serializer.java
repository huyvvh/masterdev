package serializzer;

import com.google.protobuf.Message;
import java.util.Map;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;

public class Serializer<T extends Message> extends KafkaProtobufSerializer<T> {
    @Override
    public byte[] serialize(String topic, T record) {
        return record.toByteArray();
    }
}
