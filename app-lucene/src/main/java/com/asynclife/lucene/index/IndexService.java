package com.asynclife.lucene.index;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class IndexService {
	
	private static final String indexFolder = "target/index";
	Directory directory;
	
	public IndexService() {
		try {
			this.directory = FSDirectory.open(new File(indexFolder));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private IndexWriter createIndexWriter() {
		IndexWriter indexWriter = null;
		try {
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			indexWriter = new IndexWriter(directory, config);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexWriter;
	}
	
	public void addIndex(File folder) {
		try {
			IndexWriter indexWriter = createIndexWriter();
			// 创建Document，添加Field（标题，大小，路径，内容）
			for(File file : folder.listFiles()) {
				Document doc = new Document();
				// 添加文件内容
				doc.add(new Field("content", new FileReader(file)));
				// 添加文件名到索引并且存储，对文件名不作分词
				doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED)); 
				// 添加文件路径到索引并且存储，对文件路径不作分词
				doc.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				// 使用IndexWriter将Document添加到索引中
				indexWriter.addDocument(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用Map封装需要添加索引的数据
	 * @param map
	 * @param clear	是否先清空已存在的索引
	 */
	public void addIndex(Map<String, String> map, boolean clear) {
		IndexWriter indexWriter = null;
		try {
			indexWriter = createIndexWriter();
			
			if(clear) {
				indexWriter.deleteAll();
				indexWriter.commit();
			}
			
			// 创建Document，添加Field（标题，大小，路径，内容）
			for(String key : map.keySet()) {
				Document doc = new Document();
				String id = key;
				String content = map.get(key);
				doc.add(new Field("id", id, 
						Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS));
				doc.add(new Field("content", content, 
						Field.Store.NO, Field.Index.ANALYZED));
				indexWriter.addDocument(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(indexWriter);
		}
	}
	
	private void close(IndexWriter indexWriter) {
		try {
			indexWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void reportIndex() {
		IndexReader reader = null;
		
		try {
			if(directory.listAll().length <= 0) {
				return;
			}
			reader = IndexReader.open(directory);
			System.out.println("numDocs:"+reader.numDocs());
			System.out.println("maxDoc:"+reader.maxDoc());
			System.out.println("numDeletedDocs:"+reader.numDeletedDocs());
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			// reader 一般不关闭，持续使用
			// reader.close();
		}
		
	}
	
	/**
	 * 删除所有的索引
	 */
	public void deleteAll() {
		try {
			IndexWriter writer = createIndexWriter();
			writer.deleteAll();
			writer.commit();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除包含指定Term的Document
	 * @param term
	 */
	public void delete(Term term) {
		IndexWriter writer = createIndexWriter();
		try {
			writer.deleteDocuments(term);
			writer.commit();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 恢复被删除的Document
	 * 
	 */
	public void recoverDeletedDocument() {
		try {
			IndexReader reader = IndexReader.open(directory, false);
			reader.undeleteAll();
			reader.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 合并索引，保留指定数量的segment段数
	 * */
	public void forceMerge(int maxNumSegments) {
		IndexWriter writer = createIndexWriter();
		try {
			writer.forceMerge(maxNumSegments);
			// writer.forceMergeDeletes(); // 删除所有已被删除的Document
			writer.commit();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新索引
	 * 	先删除，再添加
	 * @param term
	 * @param newDocument
	 */
	public void updateIndex(Term term, Document newDocument) {
		IndexWriter writer = createIndexWriter();
		try {
			writer.updateDocument(term, newDocument);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
