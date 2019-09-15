package com.zoo.lucene;

import com.zoo.base.Resource;
import com.zoo.io.Filer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

/**
 * 索引维护
 * 添加  入门程序
 * 删除
 * 修改
 * 查询  入门程序 精准查询 
 * @author lx
 *
 */
public class LuceneManager {

	public static void main(String[] args) throws Exception {
		
		LuceneManager lm=new LuceneManager();
		lm.testNumericRangeQuery();
	}
	
//	private static Path source = Paths.get(Resource.classPath()).getParent().getParent().resolve("source");
	private static Path store = Paths.get(Resource.classPath()).getParent().getParent().resolve("store");
	
	private static Path stopword=Resource.onClassPath("stopword.dic");
	
	private static Analyzer analyzer=getAnalyzer();
	
	public static Analyzer getAnalyzer() {
//		 Analyzer analyzer = new StandardAnalyzer();// 官方推荐
//		 Analyzer analyzer = new CJKAnalyzer();
//		Analyzer analyzer = new IKAnalyzer();// 较好用的,但是许久没更新便无法兼容高版本的Lucene了
		CharArraySet stopWords=CharArraySet.copy(new HashSet<String>(Filer.readLines(stopword)));
		Analyzer analyzer = new SmartChineseAnalyzer(stopWords);// 智能中文
		return analyzer;
	}
	
	
	//
	public IndexWriter getIndexWriter() throws Exception{
		// 第一步：创建一个java工程，并导入jar包。
		// 第二步：创建一个indexwriter对象。
		Directory directory = FSDirectory.open(store);
		// Directory directory = new RAMDirectory();//保存索引到内存中 （内存索引库）
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);
		return new IndexWriter(directory, config);
	}
	
	//全删除
	@Test
	public void testAllDelete() throws Exception {
		IndexWriter indexWriter = getIndexWriter();
		indexWriter.deleteAll();
		indexWriter.close();
	}
	//根据条件删除
	@Test
	public void testDelete() throws Exception {
		IndexWriter indexWriter = getIndexWriter();
		Query query = new TermQuery(new Term("fileName","apache"));
		indexWriter.deleteDocuments(query);
		indexWriter.close();
	}
	//修改
	@Test
	public void testUpdate() throws Exception {
		IndexWriter indexWriter = getIndexWriter();
		Document doc = new Document();
		doc.add(new TextField("fileN", "测试文件名",Store.YES));
		doc.add(new TextField("fileC", "测试文件内容",Store.YES));
		indexWriter.updateDocument(new Term("fileName","lucene"), doc);
		indexWriter.close();
	}
	//IndexReader  IndexSearcher
	public IndexSearcher getIndexSearcher() throws Exception{
		// 第一步：创建一个Directory对象，也就是索引库存放的位置。
		Directory directory = FSDirectory.open(store);// 磁盘
		// 第二步：创建一个indexReader对象，需要指定Directory对象。
		IndexReader indexReader = DirectoryReader.open(directory);
		// 第三步：创建一个indexsearcher对象，需要指定IndexReader对象
		return new IndexSearcher(indexReader);
	}
	//执行查询的结果
	public void printResult(IndexSearcher indexSearcher,Query query)throws Exception{
		// 第五步：执行查询。
		TopDocs topDocs = indexSearcher.search(query, 10);
		// 第六步：返回查询结果。遍历查询结果并输出。
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int doc = scoreDoc.doc;
			Document document = indexSearcher.doc(doc);
			// 文件名称
			String fileName = document.get("fileName");
			System.out.println(fileName);
			// 文件内容
			String fileContent = document.get("fileContent");
			System.out.println(fileContent);
			// 文件大小
			String fileSize = document.get("fileSize");
			System.out.println(fileSize);
			// 文件路径
			String filePath = document.get("filePath");
			System.out.println(filePath);
			System.out.println("------------");
		}
	}
	//查询所有
	@Test
	public void testMatchAllDocsQuery() throws Exception {
		IndexSearcher indexSearcher = getIndexSearcher();
		Query query = new MatchAllDocsQuery();
		System.out.println(query);
		printResult(indexSearcher, query);
		//关闭资源
		indexSearcher.getIndexReader().close();
	}
	//根据数值范围查询
	@Test
	public void testNumericRangeQuery() throws Exception {
		IndexSearcher indexSearcher = getIndexSearcher();
//		Query query = NumericRangeQuery.newLongRange("fileSize", 47L, 200L, false, true);
		Query query =LongPoint.newRangeQuery("fileSize", 52, 200);
		System.out.println(query);
		printResult(indexSearcher, query);
		//关闭资源
		indexSearcher.getIndexReader().close();
	}
	//可以组合查询条件
	@Test
	public void testBooleanQuery() throws Exception {
		IndexSearcher indexSearcher = getIndexSearcher();
		
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		
		Query query1 = new TermQuery(new Term("fileName","apache"));
		Query query2 = new TermQuery(new Term("fileName","lucene"));
		//  select * from user where id =1 or name = 'safdsa'
		booleanQuery.add(query1, Occur.MUST);
		booleanQuery.add(query2, Occur.SHOULD);
		System.out.println(booleanQuery);
//		printResult(indexSearcher, booleanQuery);
		//关闭资源
		indexSearcher.getIndexReader().close();
	}
	//条件解释的对象查询
	@Test
	public void testQueryParser() throws Exception {
		IndexSearcher indexSearcher = getIndexSearcher();
		//参数1： 默认查询的域  
		//参数2：采用的分析器
		QueryParser queryParser = new QueryParser("fileName",new IKAnalyzer());
		// *:*   域：值
		Query query = queryParser.parse("fileName:lucene is apache OR fileContent:lucene is apache");
		
		printResult(indexSearcher, query);
		//关闭资源
		indexSearcher.getIndexReader().close();
	}
	//条件解析的对象查询   多个默念域
	@Test
	public void testMultiFieldQueryParser() throws Exception {
		IndexSearcher indexSearcher = getIndexSearcher();
		
		String[] fields = {"fileName","fileContent"};
		//参数1： 默认查询的域  
		//参数2：采用的分析器
		MultiFieldQueryParser queryParser = new MultiFieldQueryParser(fields,new IKAnalyzer());
		// *:*   域：值
		Query query = queryParser.parse("lucene is apache");
		
		printResult(indexSearcher, query);
		//关闭资源
		indexSearcher.getIndexReader().close();
	}
	
	
}
