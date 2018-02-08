package com.wangwenjun.kafka.protocol;

import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

/***************************************
 * @author:Alex Wang
 * @Date:2018/2/8
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class MessageDeserializer implements Deserializer<Message>
{
    @Override
    public void configure(Map<String, ?> map, boolean b)
    {

    }

    @Override
    public Message deserialize(String s, byte[] bytes)
    {
        if (bytes == null || bytes.length == 0)
            return null;
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return new Message(buffer.getLong(), buffer.array());
    }

    @Override
    public void close()
    {

    }
}
