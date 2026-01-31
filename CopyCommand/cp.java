import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class cp{

	public static void main(String[] args){


// The following if conditions checks for the arguments and configures what the user is trying to acheive with the command, if there are missing arguments it will prompt the user for it and if not it will do the work
		if(args.length == 0) {
			System.out.println("Missing file operand (files basically)");
		}
			
		if(args.length == 1){
			System.out.println("missing destination file operand after  ' " + args[0] + "'");
		}

		if(args.length == 2) {
			Path inputPath = Paths.get(args[0]);
			Path outputPath = Paths.get(args[1]);


			try(FileChannel inChannel = FileChannel.open(inputPath, StandardOpenOption.READ); FileChannel outChannel = FileChannel.open(outputPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)){

				ByteBuffer buffer = ByteBuffer.allocate(1024);

				while(inChannel.read(buffer) != -1){
					buffer.flip();
					outChannel.write(buffer);
					buffer.clear();
				}

				System.out.println("File is to be copied");
			} catch (NoSuchFileException e){
				System.out.println("File is missing: " + e.getFile());
			} catch (IOException e) {
				System.err.println("General I/) error: " + e.getMessage());
			}

		}

		if(args.length > 2){
			int totalFiles = args.length - 1;
			String[] inputFiles = new String[totalFiles];

			for(int i = 0; i < totalFiles; i ++){
				inputFiles[i] = args[i];
			}

			int fileCount = 0;

			while(fileCount < totalFiles) {
				Path inputPath = Paths.get(inputFiles[fileCount]);
				String outPath = args[args.length - 1] + inputFiles[fileCount];
				Path outputPath = Paths.get(outPath);

				try(FileChannel inChannel = FileChannel.open(inputPath, StandardOpenOption.READ); FileChannel outChannel = FileChannel.open(outputPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)){

					ByteBuffer buffer = ByteBuffer.allocate(1024);

					while(inChannel.read(buffer) != -1){
						buffer.flip();
						outChannel.write(buffer);
						buffer.clear();
					}

					fileCount++;

				} catch (NoSuchFileException e){
					System.out.println("File is missing");
				} catch (IOException e){
					System.err.println("General IO error");
				}
			}
		}


	}
}
