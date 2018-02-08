package com.wangwenjun.kafka.consumer;

import com.wangwenjun.kafka.producer.ReliableProducer01;
import com.wangwenjun.kafka.protocol.Message;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class ReliableConsumer01
{


    public static void start() throws IOException
    {

        final Properties properties = loadProperties();
        KafkaConsumer<String, Message> consumer = new KafkaConsumer<>(properties);

        AtomicLong offSet = new AtomicLong();
        AtomicLong seq = new AtomicLong(-1);
        AtomicBoolean firstConsumer = new AtomicBoolean(true);
        consumer.subscribe(Collections.singleton("reliable"));
        for (; ; )
        {
            ConsumerRecords<String, Message> records = consumer.poll(100);
            records.forEach(record ->
            {
                final Message message = record.value();
                System.out.println("consume the message :" + message.getSeq());
                if (firstConsumer.get())
                {
                    firstConsumer.set(false);
                } else
                {
                    if (message.getSeq() - 1 != seq.get())
                    {
                        ReliableProducer01.run.set(false);
                        throw new RuntimeException("Oops, i think i amd loss data, previous offSet:" + offSet.get() + ", seq:" + seq.get() + " but" +
                                " now offSet:" + record.offset() + ",seq:" + message.getSeq());
                    }
                }
                offSet.set(record.offset());
                seq.set(message.getSeq());
            });
        }
    }

    private static Properties loadProperties() throws IOException
    {
        try (InputStream inStream = ReliableConsumer01.class.getClassLoader()
                .getResourceAsStream("conf/consumer-01.properties"))
        {
            Properties props = new Properties();
            props.load(inStream);
            return props;
        }
    }
}