package com.zoo.zookeeper;

public interface Constant {
//	String ZK_CONNECTION_STRING = "192.168.2.17:2181,192.168.2.18:2181,192.168.2.19:2181";
	String ZK_CONNECTION_STRING = "192.168.2.25:2181,192.168.2.25:2182,192.168.2.25:2183";
    int ZK_SESSION_TIMEOUT = 5000;
    String ZK_REGISTRY_PATH = "/registry";
    String ZK_PROVIDER_PATH = ZK_REGISTRY_PATH + "/provider";
}
