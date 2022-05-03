package filetree;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

public class main {
    public static void main(String[] args) throws IOException {
        File root = FileUtil.toFileRepresentation(Paths.get("C:\\Users\\dati1\\Documents\\testtest\\temp"));


        Iterator<File> rootIterator = root.iterator();

        System.out.println(root.getHeight());

        while (rootIterator.hasNext())
            System.out.println(rootIterator.next());;

//        System.out.println(rootIterator.next());
//        System.out.println(rootIterator.next());
//        System.out.println(rootIterator.next());
//
//        System.out.println(rootIterator.next());
//        System.out.println(rootIterator.next());

    }
}