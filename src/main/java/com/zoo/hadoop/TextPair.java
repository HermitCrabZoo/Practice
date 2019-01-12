package com.zoo.hadoop;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.text.Collator;
import java.util.Comparator;
import java.util.Objects;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextPair implements WritableComparable<TextPair> {
	
	private static final Comparator<Object> CHINESE = Collator.getInstance(java.util.Locale.CHINA);
	
	private Text first;
	private Text second;

	public TextPair() {
	}

	public TextPair(Text first, Text second) {
		this.first = first;
		this.second = second;
	}
	

	public Text getFirst() {
		return first;
	}

	public void setFirst(Text first) {
		this.first = first;
	}

	public Text getSecond() {
		return second;
	}

	public void setSecond(Text second) {
		this.second = second;
	}

	@Override
	public void write(DataOutput out) throws IOException {
		first.write(out);
		second.write(out);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		first.readFields(in);
		second.readFields(in);
		
	}
	
	public static TextPair read(DataInput in) throws IOException {
		TextPair instance=new TextPair();
		instance.readFields(in);
		return instance;
	}

	@Override
	public int compareTo(TextPair o) {
		int cmp = CHINESE.compare(first, o.first);
		if(cmp != 0) {
			return cmp;
		}
		return CHINESE.compare(second, o.second);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TextPair) {
			TextPair other=(TextPair) obj;
			return Objects.equals(first, other.first) && Objects.equals(second, other.second);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(first,second);
	}

	@Override
	public String toString() {
		return first +" "+ second;
	}
	
}
