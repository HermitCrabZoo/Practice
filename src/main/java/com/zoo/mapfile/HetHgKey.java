package com.zoo.mapfile;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class HetHgKey implements WritableComparable<HetHgKey> {

    private static Map<String, Integer> chrIndex = new HashMap<>();
    private static final Integer LOWER = 25;

    static {
        IntStream.rangeClosed(1, 22).forEach(i -> chrIndex.put(String.valueOf(i), i));
        chrIndex.put("X", 23);
        chrIndex.put("Y", 24);
    }
    public static Integer getIndex(String chr){
        return chrIndex.getOrDefault(chr, LOWER);
    }

    private static final Comparator<Object> comparator = Comparator.comparing(h -> chrIndex.getOrDefault(((HetHgKey) h).chr.toString(), LOWER)).thenComparing(h -> ((HetHgKey) h).start).thenComparing(h -> ((HetHgKey) h).ref).thenComparing(h -> ((HetHgKey) h).alt);

    private Text chr = new Text();
    private IntWritable start = new IntWritable();
    private IntWritable end = new IntWritable();
    private Text ref = new Text();
    private Text alt = new Text();


    public Text getChr() {
        return chr;
    }

    public void setChr(Text chr) {
        this.chr = chr;
    }

    public IntWritable getStart() {
        return start;
    }

    public void setStart(IntWritable start) {
        this.start = start;
    }

    public IntWritable getEnd() {
        return end;
    }

    public void setEnd(IntWritable end) {
        this.end = end;
    }

    public Text getRef() {
        return ref;
    }

    public void setRef(Text ref) {
        this.ref = ref;
    }

    public Text getAlt() {
        return alt;
    }

    public void setAlt(Text alt) {
        this.alt = alt;
    }

    @Override
    public int compareTo(HetHgKey o) {
        return comparator.compare(this, o);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        chr.write(dataOutput);
        start.write(dataOutput);
        end.write(dataOutput);
        alt.write(dataOutput);
        ref.write(dataOutput);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        chr.readFields(dataInput);
        start.readFields(dataInput);
        end.readFields(dataInput);
        alt.readFields(dataInput);
        ref.readFields(dataInput);
    }

}

