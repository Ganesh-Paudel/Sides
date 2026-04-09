package file;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileExtracter {

  private BufferedReader reader;

  public FileExtracter(String filePath) throws IOException {
    File file = new File(filePath);
    if (!file.exists()) {
      throw new IOException("File doesn't exists");
    }

    reader = new BufferedReader(new FileReader(file));
  }

  public String getLine() throws IOException {
    return this.reader.readLine();
  }
}
