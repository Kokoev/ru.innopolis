package dz;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Класс MyThread является экземпляом класса Thread. MyThread имеет два приватных поля.
 * Первое поле filePath получает ссылку типа String через конструктор.
 * Второе поле set статическое типа Set.
 *
 */
public class MyThread extends Thread {


    private String filePath = null;
    private static Set<String> set = new HashSet<String>();
    private Lock lock = new ReentrantLock();
    private static Boolean uniqueIndicator = true;


    MyThread(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Метод readFile используя классы URL и Scanner записыват в set все считанные слова написанные кириллицей
     *
     */
    private void readFile() {
        URL url = null;
        Scanner scanner = null;
        try {
            url = new URL(filePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter("[^А-яЁё]+");
        while (scanner.hasNext() && uniqueIndicator!=false) {
            try {
                lock.lock();
                String s = scanner.next().toLowerCase();
                if (addToSet(s, set)) {
                    System.out.println(getName() + ": " + s);
                } else {
                    uniqueIndicator = false;
                    System.out.println("не уникальное слово: " + s);
                }
            } finally {
                lock.unlock();
            }
        }
        scanner.close();
    }



    /**
     *  
     */
    public static boolean addToSet(String string, Set<String> set) {
        return set.add(string);
    }

    /**
     * Переопределенный метод run вызывает метод readFile
     */
    @Override
    public void run() {
        readFile();
//        System.out.println(set.size());
    }
}


