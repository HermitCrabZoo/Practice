package com.zoo.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * 该类型只作为Value使用，所以只实现Writable类
 * @author ZOO
 *
 */
public class TrafficWritable implements Writable {
	
	private int upPackNum;
	private int upPayLoad;
	private int downPackNum;
	private int downPayLoad;
	
	
	public void set(int upPackNum, int upPayLoad, int downPackNum, int downPayLoad) {
		this.upPackNum = upPackNum;
		this.upPayLoad = upPayLoad;
		this.downPackNum = downPackNum;
		this.downPayLoad = downPayLoad;
	}

	public int getUpPackNum() {
		return upPackNum;
	}

	public int getUpPayLoad() {
		return upPayLoad;
	}

	public int getDownPackNum() {
		return downPackNum;
	}

	public int getDownPayLoad() {
		return downPayLoad;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(upPackNum);
		out.writeInt(upPayLoad);
		out.writeInt(downPackNum);
		out.writeInt(downPayLoad);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		upPackNum = in.readInt();
		upPayLoad = in.readInt();
		downPackNum = in.readInt();
		downPayLoad = in.readInt();
	}
	
	public static TrafficWritable read(DataInput in) throws IOException {
		TrafficWritable tw=new TrafficWritable();
		tw.readFields(in);
		return tw;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + downPackNum;
		result = prime * result + downPayLoad;
		result = prime * result + upPackNum;
		result = prime * result + upPayLoad;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrafficWritable other = (TrafficWritable) obj;
		if (downPackNum != other.downPackNum)
			return false;
		if (downPayLoad != other.downPayLoad)
			return false;
		if (upPackNum != other.upPackNum)
			return false;
		if (upPayLoad != other.upPayLoad)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return upPackNum+"\t"+upPayLoad+"\t"+downPackNum+"\t"+downPayLoad;
	}
	
}
