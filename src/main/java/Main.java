import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

  public static void main(String[] args) throws IOException, CsvValidationException {
    // Literally just calls our parser right now (....and is called for tests)
    CsvParser csvP = new CsvParser("src/Data/bookstore_report2.csv");
    csvP.printCsv();

    // Load the json
    /*
    1. Create instance of GSON
    2. Create a JsonReader object using FileReader
    3. Array of class instances of AuthorParser, assign data from our JsonReader
    4. foreach loop to check data
     */
    Gson gson = new Gson();
    JsonReader jread = new JsonReader(new FileReader("Data/authors.json"));
    AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);

    for (var element : authors) {
      System.out.println(element.getName());
    }

    Connection con = null;

    try {
      String url = "jdbc:sqlite:BookStore.db";
      con = DriverManager.getConnection(url);

      Statement stmt = con.createStatement();

      String insertQuery =
          "INSERT INTO author"
              + "(author_name, author_email, author_url)"
              + "VALUES (?, ?, ?)";
      for (var element : authors) {
        PreparedStatement pstmt = con.prepareStatement(insertQuery);

        pstmt.setString(1, element.getName());
        pstmt.setString(2, element.getEmail());
        pstmt.setString(3, element.getUrl());
        pstmt.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}