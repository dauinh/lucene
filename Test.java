import java.io.*;
import java.nio.file.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;

public class Test {
    public static void main(String[] args) throws IOException{
        Analyzer analyzer = new StandardAnalyzer();

        // Path indexPath = Files.createTempDirectory("tempIndex");
        Path indexPath = Paths.get("/Users/dauinh/Desktop/lucene/tempIndex");
        Directory directory = FSDirectory.open(indexPath);      

        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();

        String text = "This is the text to be indexed.";
        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();
        
        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        // Query query = parser.parse("text");
        // try {
        //     Query query = parser.parse("text");
        // } catch (java.text.ParseException e) {
        //     e.printStackTrace();
        // }
        // ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
        Query query = null;
        try {
            query = parser.parse("text");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (query != null) {
            ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
            // assertEquals(1, hits.length);
            // Iterate through the results:
            StoredFields storedFields = isearcher.storedFields();
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = storedFields.document(hits[i].doc);
                System.out.println("This is the text to be indexed." + hitDoc.get("fieldname"));
                // assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
            }
        }

        
        ireader.close();
        directory.close();
        IOUtils.rm(indexPath);
    }
}