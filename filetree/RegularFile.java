package filetree;

//import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RegularFile extends File {

	public RegularFile(Path path) {
		super(path);
	}

	@Override
	public Iterator<File> iterator() {

		RegularFile file = this;


		return new Iterator<File>(){
			boolean first = false;
			boolean second = false;
			@Override
			public boolean hasNext() {
				if(!first){
					first = true;
					return true;
				}
				return false;
			}

			@Override
			public File next() {
				if(!second) {
					second = true;
					return file;
				}
				throw new NoSuchElementException();
			}

		};
	}



	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public boolean isRegularFile() {
		return true;
	}

}
