package AntiUI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperator {
    private static final int PAGE_SIZE = 4 * 1024;
    private String fileName;
    private File file;
    private RandomAccessFile accessFile;
    private List<Page> pages = new ArrayList<>();
    private byte[] tempContent;

    private static FileOperator instance = new FileOperator();

    private FileOperator() {
    }

    public static FileOperator getInstance() {
        return instance;
    }

    public void initialFile(String fileName) {
        pages.clear();
        tempContent = new byte[PAGE_SIZE];
        this.fileName = fileName;
        file = new File(fileName);
        try {
            accessFile = new RandomAccessFile(file, "rw");
            int sum = 0;
            int length = 0;
            for (int count = 0; count * PAGE_SIZE < file.length(); count++) {
                if (file.length() - sum < PAGE_SIZE) {
                    length = (int) (file.length() - sum);
                } else {
                    length = PAGE_SIZE;
                }
                sum += length;
                tempContent = new byte[PAGE_SIZE];
                accessFile.readFully(tempContent, 0, length);
                pages.add(new Page(count, tempContent));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reWriteFile(int pageNumber) {

    }

    public String getFileName() {
        return fileName;
    }

    public List getPages() {
        return pages;
    }

}
