import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class Task {

    /**
     * Записать массив целых чисел в двоичный поток.
     */
    public static void writeToBinStream(OutputStream out, int[] array) {
        try (DataOutputStream os = new DataOutputStream(out)) {
            for (int i : array) {
                os.writeInt(i);
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }

    }

    /**
     * Прочитать массив целых чисел из
     * двоичного потока. Предполагается, что до чтения массив уже создан, нужно прочитать n
     * чисел, где n — длина массива.
     */
    public static void readFromBinStream(InputStream in, int[] arr) {
        try (DataInputStream is = new DataInputStream(in)) {
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = is.readInt();
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }


    /**
     * Аналогично, используя символьные потоки. В потоке числа должны разделяться
     * пробелами.
     */
    public static void writeToCharStream(Writer out, int[] arr) {
        try (BufferedWriter bw = new BufferedWriter(out)) {
            if (arr.length > 0) {
                bw.write(Integer.toString(arr[0]));
                for (int i = 1; i < arr.length; ++i) {
                    bw.write(" ");
                    bw.write(Integer.toString(arr[i]));

                }
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public static void readFromCharStream(Reader in, int[] arr) {
        try (BufferedReader br = new BufferedReader(in)) {
            String[] s = br.readLine().split(" ");
            int i = 0;
            for (int j = 0; j < s.length && i < arr.length; ++j) {
                arr[i] = Integer.parseInt(s[j]);
                i++;
            }
            if (i != arr.length)
                throw new IllegalArgumentException("Cannot read all elements of array");
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    /**
     * Используя класс RandomAccessFile, прочитайте массив целых чисел, начиная с заданной
     * позиции.
     */
    public static void readRandom(String filename, long position, int[] arr) throws FileNotFoundException {
        try (RandomAccessFile ptr = new RandomAccessFile(filename, "r")) {
            ptr.seek(position * Integer.BYTES);
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = ptr.readInt();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getExtensionByStringHandling(String filename) {
        String fe = "";
        int i = filename.lastIndexOf('.');
        if (i > 0) {
            fe = filename.substring(i + 1);
        }
        return fe;
    }

    /**
     * Используя класс File, получите список всех файлов с заданным расширением в заданном
     * каталоге (поиск в подкаталогах выполнять не надо).
     */
    public static List<String> getAllWithGivenExtension(String directory, String ext) {
        File dir = new File(directory);
        if (!dir.isDirectory()) {
            return List.of();
        }

        var allFilesWithExt = dir.listFiles(pathname -> getExtensionByStringHandling(pathname.getName()).equals(ext)
                && pathname.isFile());

        if (allFilesWithExt == null) {
            return List.of();
        }

        return Arrays.stream(allFilesWithExt).map(File::getName).sorted().toList();
    }

    /**
     * Используя класс File, получите в заданном каталоге список всех файлов и подкаталогов,
     * имена которых удовлетворяют заданному регулярному выражению. Поиск должен
     * распространиться в подкаталоги. Имена найденных файлов должны быть вместе с
     * абсолютными путями.
     */
    public static List<String> getAllFromRegularRecursively(String directory, String regex) {
        File dir = new File(directory);
        if (!dir.isDirectory()) {
            return List.of();
        }

        List<String> files = new ArrayList<>();
        File[] filesInDirectory = dir.listFiles();
        if (filesInDirectory == null) {
            return List.of();
        }

        for (File f : filesInDirectory) {
            if (f.getName().matches(regex)) {
                files.add(f.getAbsolutePath());
            }
            if (f.isDirectory()) {
                List<String> inChildDirectory = getAllFromRegularRecursively(f.getAbsolutePath(), regex);
                files.addAll(inChildDirectory);
            }
        }
        return files;
    }


}
