package com.wangwenjun.kafka.producer;

import com.wangwenjun.kafka.protocol.Message;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ReliableProducer01
{
    public static AtomicBoolean run = new AtomicBoolean(true);

    public static void start() throws IOException
    {
        final Properties properties = loadProperties();
        KafkaProducer<String, Message> producer = new KafkaProducer<>(properties);

        AtomicLong seq = new AtomicLong();
        while (run.get())
        {
            long current = seq.get();
            //send the message size is more than 10KB.
            ProducerRecord<String, Message> record = new ProducerRecord<>("reliable", new Message(current, new byte[1024 * 10]));
            producer.send(record);
            System.out.println("send " + record.value().getSeq() + " successfully.");
            seq.incrementAndGet();
        }
    }

    private static Properties loadProperties() throws IOException
    {
        try (InputStream inStream = ReliableProducer01.class.getClassLoader()
                .getResourceAsStream("conf/producer-01.properties"))
        {
            Properties props = new Properties();
            props.load(inStream);
            return props;
        }
    }
}