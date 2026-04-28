package senagh;

import java.io.IOException;
import conversion.Convert;

public class Main {
  public static void main(String[] args) throws IOException {
    String filePath = "./test.txt";
    Convert convert = new Convert(filePath, "output.txt");
  }
}
