package com.zoo.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

/**
 * 该类型只作为Value使用，所以只实现Writable类
 * @author ZOO
 *
 */
public class TrafficWritableUsingWrapper implements Writable {
	
	private IntWritable upPackNum=new IntWritable();
	private IntWritable upPayLoad=new IntWritable();
	private IntWritable downPackNum=new IntWritable();
	private IntWritable downPayLoad=new IntWritable();
	
	
	public void set(int upPackNum, int upPayLoad, int downPackNum, int downPayLoad) {
		this.upPackNum.set(upPackNum);
		this.upPayLoad.set(upPayLoad);
		this.downPackNum.set(downPackNum);
		this.downPayLoad.set(downPayLoad);
	}

	public int getUpPackNum() {
		return upPackNum.get();
	}

	public int getUpPayLoad() {
		return upPayLoad.get();
	}

	public int getDownPackNum() {
		return downPackNum.get();
	}

	public int getDownPayLoad() {
		return downPayLoad.get();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(upPackNum.get());
		out.writeInt(upPayLoad.get());
		out.writeInt(downPackNum.get());
		out.writeInt(downPayLoad.get());
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		upPackNum.set(in.readInt());
		upPayLoad.set(in.readInt());
		downPackNum.set(in.readInt());
		downPayLoad.set(in.readInt());
	}
	
	public static TrafficWritableUsingWrapper read(DataInput in) throws IOException {
		TrafficWritableUsingWrapper tw=new TrafficWritableUsingWrapper();
		tw.readFields(in);
		return tw;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((downPackNum == null) ? 0 : downPackNum.hashCode());
		result = prime * result + ((downPayLoad == null) ? 0 : downPayLoad.hashCode());
		result = prime * result + ((upPackNum == null) ? 0 : upPackNum.hashCode());
		result = prime * result + ((upPayLoad == null) ? 0 : upPayLoad.hashCode());
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
		TrafficWritableUsingWrapper other = (TrafficWritableUsingWrapper) obj;
		if (downPackNum == null) {
			if (other.downPackNum != null)
				return false;
		} else if (!downPackNum.equals(other.downPackNum))
			return false;
		if (downPayLoad == null) {
			if (other.downPayLoad != null)
				return false;
		} else if (!downPayLoad.equals(other.downPayLoad))
			return false;
		if (upPackNum == null) {
			if (other.upPackNum != null)
				return false;
		} else if (!upPackNum.equals(other.upPackNum))
			return false;
		if (upPayLoad == null) {
			if (other.upPayLoad != null)
				return false;
		} else if (!upPayLoad.equals(other.upPayLoad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return upPackNum.get()+"\t"+upPayLoad.get()+"\t"+downPackNum.get()+"\t"+downPayLoad.get();
	}
	
}
