package com.example.pos.producers.producers;

import com.example.pos.producers.configs.AppConfig;
import com.example.pos.producers.datagenerator.InvoiceGenerator;
import com.example.pos.producers.serde.JsonSerializer;
import com.example.pos.producers.types.PosInvoice;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
public class PosProducer0 {
    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOTSTRAP_SERVER);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfig.POS_COUNTER_ID_0);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        KafkaProducer<String, PosInvoice> kafkaProducer = new KafkaProducer<String, PosInvoice>(properties);

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        int no_of_messages = Integer.parseInt(args[0]);
        for (int i=0;i<no_of_messages;i++) {
            PosInvoice posInvoice = invoiceGenerator.getNextInvoice();
            String key = AppConfig.POS_COUNTER_ID_0;
            kafkaProducer.send(new ProducerRecord<>(AppConfig.TOPIC_POS_0,key, posInvoice));
            Thread.sleep(10000);
        }
        kafkaProducer.close();
    }
}
