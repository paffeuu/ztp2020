package ztp.lista4.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClassFileGenerator {
    public void generateClassFile(String className, String classSource) throws IOException {
        try (FileWriter fw = new FileWriter(new File("generated//" + className + ".java"))) {
            fw.append(classSource);
        }
    }
}
