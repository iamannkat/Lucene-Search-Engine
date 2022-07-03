package Engine;

import java.nio.file.Paths;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneSearch {
	 private static final String INDEX_DIR = "C:\\Users\\user\\Desktop\\index";
	 private static String[] results = new String[100];
	 private static String[] paths = new String[100];
	 
	 	public String[] getResults() {
	 		return results;
	 	}
	 	
	 	public String[] getPaths() {
	 		return paths;
	 	}

	   
	    public void doSearch(String question, String field) throws Exception 
	    
	    {
	    	
	    	int index = 0;
	        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
	        IndexReader reader = DirectoryReader.open(dir);
	        IndexSearcher searcher = new IndexSearcher(reader);
	        QueryParser qp = new QueryParser(field, new StandardAnalyzer());
	        Query query = qp.parse(question);
	        TopDocs foundDocs = searcher.search(query, 100);
	        System.out.println("Total :: " + foundDocs.totalHits);

	        for (ScoreDoc sd : foundDocs.scoreDocs) 
	        {

	            Document d = searcher.doc(sd.doc);
	            if (field.equals("content")) {
		            paths[index] = String.format(d.get("movie_title"));
		            results[index] = " ";

		            if(paths[index]==null) {
		    	        System.out.println("is null content");
		            	break;
		            }
	            }else {
		            results[index] = String.format(d.get(field)); // replace with field type
		            paths[index] = String.format(d.get("movie_title"));// always get the path here
		            if(results[index]==null) {
		    	        System.out.println("is null");
		            	break;
		            }
	            }

	            index++; 
	        }
	        
	        dir.close();
	        
	    }

}
