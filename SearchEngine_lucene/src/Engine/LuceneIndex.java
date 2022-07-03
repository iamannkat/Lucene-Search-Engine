package Engine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import java.nio.file.Files;
import java.nio.file.Path;


public class LuceneIndex {
	private final String INDEX_DIR = "C:\\Users\\user\\Desktop\\index";
	private final String CORPUS_DIR = "C:\\Users\\user\\Desktop\\corpus";
	private String currentPath = "";
	
	private IndexWriter createWriter() throws IOException{
		
		FSDirectory directory = FSDirectory.open(Paths.get(INDEX_DIR));
		IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
		IndexWriter writer = new IndexWriter(directory, config);
		
		return writer;
	}
	
	private Document createDocument(File file) throws IOException{
		Document document = new Document(); 
		
		TextField fileNameField = new TextField("movie_title", file.getName(), Field.Store.YES);
		TextField textfield = new TextField("content", new FileReader(file));
		
		Path pathTofile = Paths.get(currentPath);
		java.util.List<String> lines = Files.readAllLines(pathTofile);
		
		TextField castfield = new TextField("cast",lines.get(2), Field.Store.YES);
		StringField typefield = new StringField("type",lines.get(1), Field.Store.YES);
		TextField countryfield = new TextField("country",lines.get(3), Field.Store.YES);
		TextField directorfield = new TextField("director",lines.get(4), Field.Store.YES);
		StringField date_addedfield = new StringField("date_added",lines.get(5), Field.Store.YES);
		StringField release_yearfield = new StringField("release_year",lines.get(6), Field.Store.YES);
		StringField ratingfield = new StringField("rating",lines.get(7), Field.Store.YES);
		StringField durationfield = new StringField("duration",lines.get(8), Field.Store.YES);
		// Use StringField for a field with an atomic value that should not be tokenized. Use TextField for a field that needs to be tokenized into a set of words.
		TextField listed_infield = new TextField("listed_in",lines.get(9), Field.Store.YES);
		TextField descriptionfield = new TextField("description",lines.get(10), Field.Store.YES);

		document.add(fileNameField);
		document.add(textfield);
		document.add(castfield);document.add(typefield);
		document.add(countryfield);document.add(directorfield);document.add(date_addedfield);document.add(release_yearfield);
		document.add(ratingfield);document.add(durationfield);document.add(listed_infield);document.add(descriptionfield);

		return document;
	}
	
	

	public void createIndex() throws Exception{
		IndexWriter writer = createWriter();
		
		writer.deleteAll();
		
		//for each document in data folder
	    File[] files = new File(CORPUS_DIR).listFiles();
	    for (File file : files) {
	    	if(!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead()) {
	    		currentPath = file.getCanonicalPath();
	    		// System.out.println("Indexing " + currentPath);
	    	    Document document = createDocument(file);
	    	    writer.addDocument(document);
	    	}
	    }
		
		writer.commit();
		writer.close();
		System.out.println("Indexing Done");
		
	}

}
