
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException, CsvValidationException {
    // Literally just calls our parser right now (....and is called for tests)
    CsvParser csvP = new CsvParser("src/Data/SEOExample.csv");
    csvP.printCsv();

    // Load the json
        /*
        1. Create instance of GSON
        2. Create a JsonReader object using FileReader
        3. Array of class instances of AuthorParser, assign data from our JsonReader
        4. foreach loop to check data
         */

  }
}
