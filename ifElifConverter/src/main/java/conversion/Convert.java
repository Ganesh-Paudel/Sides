package conversion;

import java.util.ArrayList;
import java.io.IOException;
import file.FileExtracter;
import java.io.File;
import java.io.FileWriter;

public class Convert {

  FileExtracter extracter;
  String indent = "    ";
  int indentMultiplicity = 0;

  public Convert(String filePath, String outFile) throws IOException {
    extracter = new FileExtracter(filePath);
    String outputFilePath = "./" + outFile;
    File file = new File(outputFilePath);
    FileWriter fw = new FileWriter(file);
    StringBuilder ss = new StringBuilder();
    StringBuilder output = convert(extracter.getLine(), ss);
    if (ss.length() > 0 && ss.charAt(0) == '\n') {
      ss.deleteCharAt(0);
    }
    String outputString = ss.toString();
    System.out.println(output);
    fw.write(outputString);
    fw.close();
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

  private StringBuilder convert(String line, StringBuilder ss) throws IOException {
    if (line == null) {
      return ss;
    }
    if (line.startsWith("elif")) {
      String indentation = getIndent();
      ss.append("\n").append(indentation).append("else:");
      indentMultiplicity++;
      String newLine = elifToIf(line);
      indentation = getIndent();
      ss.append("\n").append(indentation).append(newLine);
    } else {
      if (line.startsWith("if")) {
        indentMultiplicity = 0;
      }
      String indentation = getIndent();
      ss.append("\n").append(indentation + line);
    }
    convert(extracter.getLine(), ss);
    return ss;
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
