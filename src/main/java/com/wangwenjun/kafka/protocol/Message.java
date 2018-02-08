package com.wangwenjun.kafka.protocol;

/***************************************
 * @author:Alex Wang
 * @Date:2018/2/8
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class Message
{
    private long seq;

    private byte[] content;

    public Message()
    {
    }

    public Message(long seq, byte[] content)
    {
        this.seq = seq;
        this.content = content;
    }

    public long getSeq()
    {
        return seq;
    }

    public void setSeq(long seq)
    {
        this.seq = seq;
    }

    public byte[] getContent()
    {
        return content;
    }

    public void setContent(byte[] content)
    {
        this.content = content;
    }
}