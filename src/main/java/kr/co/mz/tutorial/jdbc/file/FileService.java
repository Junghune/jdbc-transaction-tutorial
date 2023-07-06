package kr.co.mz.tutorial.jdbc.file;

import java.io.File;
import java.time.LocalDateTime;

public class FileService {

    public static final String BASIC_DIRECTORY = "/Users/mz01-junghunee/Documents/tutorial_directory/";

    public static void createDirectory() {
        File fileDirectory = new File(generateFileDirectoryName());
        if (!fileDirectory.exists()) {
            boolean flag = fileDirectory.mkdirs();
            if (flag) {
                System.out.println("파일이 생성되지 않았습니다.");
            }
        }
    }

    public static String generateFileDirectoryName() {
        return BASIC_DIRECTORY + LocalDateTime.now().toLocalDate().toString().substring(0, 10);
    }
}
