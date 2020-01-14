package com.zoo.mq.kafka;

public interface Constant {
	/**
	 * kafka集群
	 */
	String KAFKA_HOST_PORTS="192.168.2.25:9092,192.168.2.25:9093,192.168.2.25:9094";
	
	/**
	 * Zookeeper集群
	 */
	String ZK_HOST_PORTS="192.168.1.86:2181,192.168.1.96:2181,192.168.1.106:2181";
}
