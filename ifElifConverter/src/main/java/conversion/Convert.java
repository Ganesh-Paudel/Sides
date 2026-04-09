package conversion;

import java.util.ArrayList;
import java.io.IOException;

import file.FileExtracter;

public class Convert {

  FileExtracter extracter;
  String indent = "    ";
  int indentMultiplicity = 0;

  public Convert(String filePath) throws IOException {
    extracter = new FileExtracter(filePath);

    convert(extracter.getLine());
  }

  private void convert(String line) throws IOException {
    if (line == null) {
      return;
    }
    if (line.startsWith("elif")) {
      String indentation = getIndent();
      System.out.println(indentation + "else:");
      indentMultiplicity++;
      String newLine = elifToIf(line);
      indentation = getIndent();
      System.out.println(indentation + newLine);
    } else {
      String indentation = getIndent();
      System.out.println(indentation + line);
    }
    convert(extracter.getLine());
  }

  private String elifToIf(String line) {
    String newLine = line.replace("elif ", "if ");
    return newLine;
  }

  private String getIndent() {
    StringBuilder ss = new StringBuilder();
    for (int i = 0; i < indentMultiplicity; i++) {
      ss.append(this.indent);
    }
    return ss.toString();
  }
}
