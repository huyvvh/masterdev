package serializzer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;

public class Deserializer<T extends Message> implements org.apache.kafka.common.serialization.Deserializer<T> {
    private final Parser<T> parser;

    public Deserializer(Parser<T> parser) {
        this.parser = parser;
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        try {
            return parser.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
