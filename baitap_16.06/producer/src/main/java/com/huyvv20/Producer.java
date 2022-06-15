package com.huyvv20;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Producer {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.17.80.30:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        try {
            File csv = new File("E:/masterdev/baitap_16.06/producer/data/customer.csv");
            Scanner scan = new Scanner(csv);
            String header = "id,num_order,age,tel";
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                if(!data.equals(header)) {
                    producer.send(new ProducerRecord<String, String>("customer", null, data));
                    System.out.println(data);
                }
            }
            scan.close();
            producer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Not Found.");
            e.printStackTrace();
        }
    }
}

