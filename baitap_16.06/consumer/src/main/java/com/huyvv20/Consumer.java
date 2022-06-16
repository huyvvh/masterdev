package com.huyvv20;

import com.huyvv20.schema.customer;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        ArrayList<Integer> listId = new ArrayList<>();

        // Delimiter used in CSV file
        final String COMMA_DELIMITER = ",";
        final String NEW_LINE_SEPARATOR = "\n";

        // CSV file header
        final String FILE_HEADER = "id,num_order,age,tel";

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.80.30:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-consumer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList("customer"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    //Check id
                    try{
                        String file = "E:/masterdev/baitap_16.06/consumer/data/result.csv";
                        List<customer> checkIdCustomer = new CsvToBeanBuilder(new FileReader(file)).withType(customer.class).build().parse();
                        for(int i=0; i<checkIdCustomer.size(); i++){
                            listId.add(checkIdCustomer.get(i).getId());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    //Return Age < 20 && Num_order > 100
                    List<customer> customersResult = new CsvToBeanBuilder(new StringReader(record.value())).withType(customer.class).build().parse();
                    for(int i=0; i<customersResult.size(); i++) {
                        if (!listId.contains(customersResult.get(i).getId()) && customersResult.get(i).getAge() < 20 && customersResult.get(i).getNum_order()>100 ){
                            File result = new File("E:/masterdev/baitap_16.06/consumer/data/result.csv");
                            try {
                                FileWriter fileWriter = new FileWriter(result, true);
                                fileWriter.append(String.valueOf(customersResult.get(i).getId()));
                                fileWriter.append(COMMA_DELIMITER);
                                fileWriter.append(String.valueOf(customersResult.get(i).getNum_order()));
                                fileWriter.append(COMMA_DELIMITER);
                                fileWriter.append(String.valueOf(customersResult.get(i).getAge()));
                                fileWriter.append(COMMA_DELIMITER);
                                fileWriter.append(customersResult.get(i).getTel());
                                fileWriter.append(NEW_LINE_SEPARATOR);

                                fileWriter.flush();
                                fileWriter.close();
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        } finally {
            consumer.close();
        }
    }


}
