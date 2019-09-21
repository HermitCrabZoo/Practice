package com.zoo.mapfile;


import com.zoo.io.FileItr;
import com.zoo.io.FileLineItr;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.MapFile.Writer;
import org.apache.hadoop.io.MapFile.Reader;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class HetHgHelper {


    public static void main(String[] args) throws Exception {
        String sourceFile = "E:\\result\\BS2_hom_het\\BS2_hom_het.hg19.txt";
        String indexDir = "E:\\result\\BS2_hom_het\\data";
//        write(sourceFile, indexDir);
         read(sourceFile, indexDir);
        //  key=12 52841404 T C   value= 0 1
//        Text val = readByKey("1", 161276434, 161276434, "A", "G");
//        System.out.println(val.toString());


    }

    public static void write(String sourceFile, String indexDir) throws IOException {
        Configuration conf = new Configuration();
//        Writer.Option o1 = Writer.keyClass(HetHgKey.class);
        try (FileSystem fs = FileSystem.getLocal(conf);
             FileItr<String> fileItr = new FileLineItr(sourceFile);
             Writer writer = new Writer(conf, fs, indexDir, HetHgKey.class, Text.class)/*;
             Writer writer1 = new Writer(conf, new Path(path.toString()), o1)*/) {
            HetHgKey key = new HetHgKey();
            Text value = new Text();
            String[] tab;
            int count = 0;
            List<String[]> lines = new ArrayList<>(6000000);
            for (String line : fileItr) {
                count++;
                tab = line.split(" ");
                lines.add(tab);
            }
            lines.sort(Comparator.comparing(t -> HetHgKey.getIndex(((String[]) t)[0])).thenComparingInt(t -> Integer.valueOf(((String[]) t)[1])).thenComparing(t -> ((String[]) t)[2]).thenComparing(t -> ((String[]) t)[3]));
            for(String[] line:lines){
                String chr = line[0];
                int pos = Integer.valueOf(line[1]);
                String ref = line[2];
                String alt = line[3];
                key.setChr(new Text(chr));
                key.setStart(new IntWritable(pos));
                key.setEnd(new IntWritable(pos));
                key.setRef(new Text(ref));
                key.setAlt(new Text(alt));
                value.set(line[4] + " " + line[5]);
                writer.append(key, value);
            }
            System.out.println(count);
        }
    }

    public static void read(String sourceFile, String indexDir) throws IOException {
        //创建配置信息
        Configuration conf = new Configuration();
        //创建文件系统
        //创建Path对象
        //4.new一个MapFile.Reader进行读取
        try (FileSystem fs = FileSystem.getLocal(conf);
             Reader reader = new Reader(fs, indexDir, conf);
             FileItr<String> fileItr = new FileLineItr(sourceFile)) {

            List<String[]> lines = new ArrayList<>(6000000);
            for (String line : fileItr) {
                lines.add(line.split(" "));
            }
            lines.sort(Comparator.comparing(t -> HetHgKey.getIndex(((String[]) t)[0])).thenComparingInt(t -> Integer.valueOf(((String[]) t)[1])).thenComparing(t -> ((String[]) t)[2]).thenComparing(t -> ((String[]) t)[3]));

            //创建Key和Value
            HetHgKey key = new HetHgKey();
            Text value = new Text();
            //遍历获取结果
            int count = 0, fcount = 0;
            Iterator<String[]> itr = lines.iterator();
            while (reader.next(key, value)) {
//                String line = fileItr.next();
                String line = String.join(" ", itr.next());
                String k = key.getChr() + " " + key.getStart() + " " + key.getRef() + " " + key.getAlt();
                String v = value.toString();
                String kv = k + " " + v;
                if (kv.equals(line)) {
                    count++;
                } else {
                    fcount++;
                }
            }
            System.out.println(count + " " + fcount);
        }
    }

    public static Text readByKey(String chr, int start, int end, String ref, String alt) throws Exception {
        //创建配置信息
        Configuration conf = new Configuration();
        //创建文件系统
        //创建Path对象
        Path path = new Path("E:\\result\\BS2_hom_het\\data.map");
        //创建Key和Value
        HetHgKey key = new HetHgKey();
        key.setChr(new Text(chr));
        key.setStart(new IntWritable(start));
        key.setEnd(new IntWritable(end));
        key.setRef(new Text(ref));
        key.setAlt(new Text(alt));
        Text value = new Text();
        //4.new一个MapFile.Reader进行读取
        try (FileSystem fs = FileSystem.getLocal(conf);
             MapFile.Reader reader = new MapFile.Reader(fs, path.toString(), conf)) {
            reader.get(key, value);
        }
        return value;
    }


}

