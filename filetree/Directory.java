package filetree;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public class Directory extends File {

	private final List<File> files;

	public Directory(Path path, List<File> files) {
		super(path);
		this.files = files;
	}

	@Override
	public Iterator<File> iterator() {

		File file = this;
		//same as regular
		if(files.isEmpty()){
			return new Iterator<File>() {
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

					if(!second){
						second = true;
						return file;
					}
					throw new NoSuchElementException();
				}
			};
		}
		Directory yes = new Directory(getPath(),new LinkedList<>());
		//big list where we save
		List<File> list = new LinkedList<>(files);
		// we add first directory
		list.add(yes);
		return new Iterator<File>() {

			@Override
			public boolean hasNext() {

				return !list.isEmpty();
			}

			@Override
			public File next() {
				File temp = list.remove(0);

				if(temp.isRegularFile()){
					return temp;
				}else{
					list.addAll(((Directory) temp).files); // if it is not a regularfile we cast it to directory type and afterwards add all files to big list we created
				}
				return temp;
			}
		};
	}




	@Override
	public int getHeight() {

		if(files.isEmpty() || isRegularFile()){
			return 0;
		}
		return  files.stream().mapToInt(file -> file.getHeight()).max().getAsInt() + 1;



	}

	@Override
	public boolean isRegularFile() {
		return false;
	}

	public List<File> getFiles() {
		return files;
	}

}
