package pairmatching.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    public List<String> readLines(String fileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            throw new IllegalArgumentException("[ERROR] " + fileName + " 파일을 찾을 수 없습니다.");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.readLine(); // 헤더 스킵
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "[ERROR] " + fileName + " 파일을 읽을 수 없습니다."
            );
        }
    }

}

