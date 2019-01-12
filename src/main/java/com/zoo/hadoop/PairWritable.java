package com.zoo.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.Collator;
import java.util.Comparator;
import java.util.Objects;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.io.WritableUtils;


public class PairWritable implements WritableComparable<PairWritable> {
	
	private static final Comparator<Object> CHINESE = Collator.getInstance(java.util.Locale.CHINA);
	
	private String name;
	private Integer age;
	
	

	public PairWritable() {
	}

	public PairWritable(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeBytes(name);
		out.writeInt(age);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		name=in.readUTF();
		age=in.readInt();
	}
	
	public static PairWritable read(DataInput in) throws IOException {
		PairWritable instance=new PairWritable();
		instance.readFields(in);
		return instance;
	}

	@Override
	public int compareTo(PairWritable o) {
		int cmp = CHINESE.compare(name, o.name);
		if(cmp != 0) {
			return cmp;
		}
		return Integer.compare(age, o.age);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PairWritable) {
			PairWritable pw=(PairWritable) obj;
			return Objects.equals(name, pw.name) && Objects.equals(age,pw.age);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name,age);
	}
	
	@Override
	public String toString() {
		return name +" "+ age;
	}
	
	/**
	 * 自定义比较器
	 * @author ZOO
	 *
	 */
	public static class Comparer extends WritableComparator {
		@Override
		public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {

			
			//比较name
			int n1 = WritableUtils.decodeVIntSize(b1[s1]);
			int n2 = WritableUtils.decodeVIntSize(b2[s2]);
			int cmp = compareBytes(b1, s1 + n1, l1 - n1, b2, s2 + n2, l2 - n2);
			if (cmp != 0) {
				return cmp;
			}
			
			//比较age
			int thisValue = readInt(b1, l1 - s1 -n1);
			int thatValue = readInt(b2, l2 - s2 -n2);
			return (thisValue < thatValue ? -1 : (thisValue == thatValue ? 0 : 1));
		}
	}
	
	/**
	 * 注册
	 */
	static {
		WritableComparator.define(PairWritable.class, new Comparer());
	}

}
