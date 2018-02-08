package com.wangwenjun.kafka.reliable;

import com.wangwenjun.kafka.consumer.ReliableConsumer01;
import com.wangwenjun.kafka.producer.ReliableProducer01;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/***************************************
 * @author:Alex Wang
 * @Date:2018/2/8
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class ReliableTest1
{

    public static void main(String[] args) throws InterruptedException
    {
        new Thread(() ->
        {
            try
            {
                ReliableConsumer01.start();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }, "Consumer").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() ->
        {
            try
            {
                ReliableProducer01.start();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }, "Producer").start();
    }
}
