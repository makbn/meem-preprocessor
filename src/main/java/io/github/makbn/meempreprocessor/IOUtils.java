package io.github.makbn.meempreprocessor;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class IOUtils {

    public static File saveToFile(File file, String s) {
        File f=new File(s);
        try {
            FileUtils.copyFile(file,f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
}
