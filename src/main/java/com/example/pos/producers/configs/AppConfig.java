package com.example.pos.producers.configs;

public class AppConfig {
    public static final String BOOTSTRAP_SERVER = "localhost:9092,localhost:9093,localhost:9094";
    public static final String TOPIC_POS_0 = "pos";

    public static final String TOPIC_POS_1 = "pos1";
    public static final String POS_COUNTER_ID_0 = "pos-0";
    public static final String POS_COUNTER_ID_1 = "pos-1";
    public static final String STREAM_PROCESSOR_ID_0 = "stream-processor-0";
}
