package com.zoo.mq.kafka;

import java.util.concurrent.atomic.LongAdder;

public class TestKafka {
	private static LongAdder padder=new LongAdder();
	private static LongAdder cadder=new LongAdder();

	public static void main(String[] args) {
		
		padder.increment();
		cadder.increment();
		
		Thread producer=new Thread(new KafkaProducerThread("test1"),"Producer-"+padder.longValue());
		producer.start();
		
		Thread consumer=new Thread(new KafkaConsumerThread("test1",0),"Consumer-"+cadder.longValue());
		consumer.start();
	}

}
