package com.zoo.mq.kafka;

import com.zoo.base.Strs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class KafkaProducerThread implements Runnable {

    private String topic;

    public KafkaProducerThread(String topic) {
        this.topic = topic;
    }


    @Override
    public void run() {
        //生产者发送消息 
        Producer<String, String> producer = createProducer();
        for (int i = 1; i <= 15; i++) {
            String value = Thread.currentThread().getName() + "_value_" + Strs.randString(6);
            ProducerRecord<String, String> msg = new ProducerRecord<>(topic, value);
            producer.send(msg);
            System.out.println(Thread.currentThread().getName() + " topic = " + msg.topic() + ", value = " + msg.value());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //列出topic的相关信息
        List<PartitionInfo> partitions = producer.partitionsFor(topic);
        for (PartitionInfo p : partitions) {
            System.out.println(p);
        }

        System.out.println("send message over.");
//        producer.close(100,TimeUnit.MILLISECONDS);
        producer.close(Duration.ofMillis(100));
    }

    private Producer<String, String> createProducer() {

        /*
         * bootstrap.servers:用于初始化时建立链接到kafka集群，以host:port形式，多个以逗号分隔host1:port1,host2:port2；
         * acks:生产者需要server端在接收到消息后，进行反馈确认的尺度，主要用于消息的可靠性传输；acks=0表示生产者不需要来自server的确认；
         * 		acks=1表示server端将消息保存后即可发送ack，而不必等到其他follower角色的都收到了该消息；acks=all(or acks=-1)意味着server端将等待所有的副本都被接收后才发送确认。
         * retries:生产者发送失败后，重试的次数
         * batch.size:当多条消息发送到同一个partition时，该值控制生产者批量发送消息的大小，批量发送可以减少生产者到服务端的请求数，有助于提高客户端和服务端的性能。
         * linger.ms:默认情况下缓冲区的消息会被立即发送到服务端，即使缓冲区的空间并没有被用完。可以将该值设置为大于0的值，这样发送者将等待一段时间后，再向服务端发送请求，以实现每次请求可以尽可能多的发送批量消息。
         * batch.size和linger.ms是两种实现让客户端每次请求尽可能多的发送消息的机制，它们可以并存使用，并不冲突。
         * buffer.memory:生产者缓冲区的大小，保存的是还未来得及发送到server端的消息，如果生产者的发送速度大于消息被提交到server端的速度，该缓冲区将被耗尽。
         * key.serializer,value.serializer说明了使用何种序列化方式将用户提供的key和vaule值序列化成字节。
         */

        Properties props = new Properties();
        props.put("bootstrap.servers", Constant.KAFKA_HOST_PORTS);//kafka集群的ip:port多个用逗号分隔
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return new KafkaProducer<>(props);
    }
}
