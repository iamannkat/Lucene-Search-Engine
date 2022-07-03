package Engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Client.Window;

public class Engine {
	private static String[] FIELDS = {"movie_title", "cast","country","director",
			"date_added","release_year","rating","duration","listed_in","description"};
	private static ArrayList<String> history = new ArrayList<String>();
	private static String questionWord;
	
	private static void readHistoryFromFile() throws FileNotFoundException {
		Scanner s = new Scanner(new File("C:\\Users\\user\\Desktop\\EclipseProjects\\SearchEngine_lucene\\SeachHistory"));
		while (s.hasNext()){
		    history.add(s.next());
		}
		s.close();

	}
	
	private static void writeHistoryToFile() throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\user\\Desktop\\EclipseProjects\\SearchEngine_lucene\\SeachHistory");
        for(String element : history) {
            writer.write(element + "\n");
        }
        writer.close();
	}

	public static void main(String[] args) throws Exception {
		System.out.print("Welcome to the search engine!\nIndexing in progress...");

		readHistoryFromFile();
		LuceneIndex index = new LuceneIndex();
		index.createIndex();

		
		while(true) {	
			String[] results = {};
			String[] paths = {};
			Scanner input = new Scanner(System.in);
			Window win = new Window();
			System.out.print("Would you like to search the database with a keyword or with a field?(k/f): ");
			String query_answer = input.nextLine();
			
			if(query_answer.equals("k")) {
				
				System.out.print("Previous Searches -> " + history.toString() + "\n");
				System.out.print("Enter your keyword: ");
				questionWord = input.nextLine();
				history.add(questionWord);
				LuceneSearch search = new LuceneSearch();
				search.doSearch(questionWord, "content");
				results = search.getResults();
				paths = search.getPaths();

			}else if(query_answer.equals("f")) {
				System.out.println("Please select one of the following fields: ");
				System.out.println("1. movie_title");
				System.out.println("2. cast");
				System.out.println("3. country of origin");
				System.out.println("4. director");
				System.out.println("5. date added to database");
				System.out.println("6. release_year");
				System.out.println("7. rating");
				System.out.println("8. duration");
				System.out.println("9. genre");
				System.out.println("10. description");
				System.out.println("Select number: ");

				String fieldInt = input.nextLine();
				Integer i = Integer.valueOf(fieldInt);
				String fieldType = FIELDS[i-1];
				System.out.print("Previous Searches -> " + history.toString() + "\n");

				System.out.print(fieldType+": ");
				questionWord = input.nextLine();
				history.add(questionWord);
				LuceneSearch search = new LuceneSearch();
				search.doSearch(questionWord, fieldType);
				results = search.getResults();
				paths = search.getPaths();
			}else {
				System.out.print("Please select one of the avaliable options.\n\n");
				continue;
			}

			win.set(results);
			win.setMovieTitle(paths);
			win.setWord(questionWord);
			win.createWindow();
			win.addFirstTen();

			System.out.println("Query is done");
			writeHistoryToFile();
			
			System.out.print("Would you like to ask another question? (y/n): ");
			String answer = input.nextLine();

			if(answer.equals("y")) {
				win.closeWindow();
				continue;
			}else {
				input.close();
				System.out.println("Exiting program...");
				win.closeWindow();

				break;
			}

		}
		
	}

}
