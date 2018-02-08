package com.wangwenjun.kafka.protocol;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/***************************************
 * @author:Alex Wang
 * @Date:2018/2/8
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class MessageSerializer implements Serializer<Message>
{
    @Override
    public void configure(Map<String, ?> map, boolean b)
    {
    }

    @Override
    public byte[] serialize(String s, Message message)
    {
        if (message == null)
        {
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(8 + message.getContent().length);
        buffer.putLong(message.getSeq());
        buffer.put(message.getContent());
        return buffer.array();
    }

    @Override
    public void close()
    {

    }
}