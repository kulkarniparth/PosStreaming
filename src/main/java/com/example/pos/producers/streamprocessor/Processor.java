package com.example.pos.producers.streamprocessor;

import com.example.pos.producers.configs.AppConfig;
import com.example.pos.producers.serde.AppSerdes;
import com.example.pos.producers.types.PosInvoice;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class Processor {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.STREAM_PROCESSOR_ID_0);
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfig.BOOTSTRAP_SERVER);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, PosInvoice> KS0 = builder.stream(AppConfig.TOPIC_POS_0,
                Consumed.with(AppSerdes.String(), AppSerdes.PosInvoice()));

        KS0.filter((k,v) ->
                k.equalsIgnoreCase("pos-1"))
                .to(AppConfig.TOPIC_POS_1, Produced.with(AppSerdes.String(),AppSerdes.PosInvoice()));

        KafkaStreams streams = new KafkaStreams(builder.build(), properties);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            streams.close();
        }));
    }
}
