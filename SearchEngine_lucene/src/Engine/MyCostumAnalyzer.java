package Engine;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.CapitalizationFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyCostumAnalyzer extends Analyzer{
	
	private static final String[] stopWords= {"a", "an", "and", "are", "as", "at", "be", "but", "by","for", "if", "in", "into", "is", "it","no", "not", "of", "on", "or", "such","that", "the", "their", "then", "there", "these","they", "this", "to", "was", "will", "with"};
	
	@Override
    protected TokenStreamComponents createComponents(String fieldName) {
        
		StandardTokenizer src = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(src);
		result = new StopFilter(result, StopFilter.makeStopSet(stopWords));
        result = new PorterStemFilter(result);
        return new TokenStreamComponents(src, result);
    }

}
