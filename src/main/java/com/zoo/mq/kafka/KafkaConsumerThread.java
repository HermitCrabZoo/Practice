package com.zoo.mq.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class KafkaConsumerThread implements Runnable {

	private String topic;
	private int partition;
	
	
	public KafkaConsumerThread(String topic, int partition) {
		this.topic = topic;
		this.partition = partition;
	}

	@Override
	public void run() {
		
		/*
		 * bootstrap.servers:用于初始化时建立链接到kafka集群，以host:port形式，多个以逗号分隔host1:port1,host2:port2； 
		 * group.id：消费者的组id 
		 * kafka使用消费者分组的概念来允许多个消费者共同消费和处理同一个topic中的消息。分组中消费者成员是动态维护的，如果一个消费者处理失败了，那么之前分配给它的partition将被重新分配给分组中其他消费者；同样，如果分组中加入了新的消费者，也将触发整个partition的重新分配，每个消费者将尽可能的分配到相同数目的partition，以达到新的均衡状态； 
		 * enable.auto.commit:用于配置是否自动的提交消费进度； 
		 * auto.commit.interval.ms:用于配置自动提交消费进度的时间； 
		 * session.timeout.ms:会话超时时长，客户端需要周期性的发送“心跳”到broker，这样broker端就可以判断消费者的状态，如果消费在会话周期时长内未发送心跳，那么该消费者将被判定为dead，那么它之前所消费的partition将会被重新的分配给其他存活的消费者； 
		 * key.serializer,value.serializer说明了使用何种序列化方式将用户提供的key和vaule值序列化成字节。
		 */
		Properties props = new Properties();
		props.put("bootstrap.servers", Constant.KAFKA_HOST_PORTS);//kafka集群的ip:port多个用逗号分隔
        props.put("group.id", "test");//消费者的组id，同一个个组负责同一个topic
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //订阅主题列表topic
//        consumer.subscribe(Arrays.asList(topic));//kafka自东分配partition给consumer
        consumer.assign(Arrays.asList(new TopicPartition(topic, partition)));
        
        List<Integer> emptyTimes=new ArrayList<Integer>(100);
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf(Thread.currentThread().getName()+" offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value()+"\n");
            if (records.isEmpty()) {
            	emptyTimes.add(1);
            	if (emptyTimes.size()>=100) {//连续100次获取不到消息则退出
            		System.out.println("连续20秒未获取消息，"+Thread.currentThread().getName()+"退出！");
					break;
				}
			}else {
				emptyTimes.clear();
			}
        }
        consumer.close();
	}

}
