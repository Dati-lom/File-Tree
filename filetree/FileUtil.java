package filetree;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {

	public static File toFileRepresentation(Path path) throws IOException {
		if(Files.isRegularFile(path)){

			return new RegularFile(path);
		}

		else if(Files.isDirectory(path)){

			List<File> flist = new LinkedList<>();

			Files.list(path).forEach(path1 ->{

				try {
					flist.add(toFileRepresentation(path1));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

				return new Directory(path, flist);
			}
		return null;
	}

}
