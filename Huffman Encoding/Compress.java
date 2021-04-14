import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Compress {

    private static final int ALPHABET_SIZE = 256;

    //this method is used to round up length of bits
    static int RoundUp(int x)
    {
        return ((x + 7) & (-8));
    }

    // https://www.w3schools.com/java/showjava.asp?filename=demo_files_read
    public static String ReadFile(String filename) {
        Path path = Paths.get(filename);
        String contents = null;
        try {
            contents = Files.readString(path, StandardCharsets.US_ASCII);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contents;
    }

    //create and return huffman encoding
    public static HuffmanEncodedResult compress(final String data) {
        final int[] freq = buildFrequencyTable(data);
        final Node root = buildHuffmanTree(freq);
        final Map<Character, String> lookupTable = buildLookupTable(root);

        return new HuffmanEncodedResult(generateEncodedData(data, lookupTable), root);
    }

    private static String generateEncodedData(String data,
                                     Map<Character, String> lookupTable) {
        final StringBuilder builder = new StringBuilder();
        for (final char character : data.toCharArray()) {
            builder.append(lookupTable.get(character));
        }
        return builder.toString();
    }

    private static Map<Character, String> buildLookupTable(final Node root) {
        final Map<Character, String> lookupTable = new HashMap<>();
        buildLookupTableImpl(root, "", lookupTable);
        
        return lookupTable;
    }

    private static void buildLookupTableImpl(final Node node,
                                             final String s,
                                             final Map<Character, String> lookupTable) {
        if (!node.isLeaf()) {
            buildLookupTableImpl(node.leftChild, s + '0', lookupTable);
            buildLookupTableImpl(node.rightChild, s + '1', lookupTable);
        } else {
            lookupTable.put(node.character, s);
        }
    }

    //build a huffman tree
    private static Node buildHuffmanTree(int[] freq) {
        final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (char i=0; i<ALPHABET_SIZE; i++) {
            if (freq[i] > 0) {
                priorityQueue.add(new Node(i, freq[i], null, null));
            }
        }

        if (priorityQueue.size() == 1) {
            priorityQueue.add(new Node('\0', 1, null, null));
        }

        while (priorityQueue.size() > 1) {
            final Node left = priorityQueue.poll();
            final Node right = priorityQueue.poll();
            final Node parent = new Node('\0', left.frequency + right.frequency, left, right);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    //build a frequency table
    private static int[] buildFrequencyTable(final String data) {
        final int[] freq = new int[ALPHABET_SIZE];
        for (final char character : data.toCharArray()) {
            freq[character]++;
        }
        return freq;
    }

    static class Node implements Comparable<Node>{
        private final char character;
        private final int frequency;
        private final Node leftChild;
        private final Node rightChild;

        private Node(final char character,
                     final int frequency,
                     final Node leftChild,
                     final Node rightChild) {
            this.character = character;
            this.frequency = frequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        boolean isLeaf() {
            return this.leftChild == null && this.rightChild == null;
        }

        @Override
        public int compareTo(Node o) {
            final int frequencyComparison = Integer.compare(this.frequency, o.frequency);
            if (frequencyComparison != 0) {
                return frequencyComparison;
            }
            return Integer.compare(this.character, o.character);
        }
    }

    static class HuffmanEncodedResult {
        final Node root;
        String encodedData;

        HuffmanEncodedResult(final String encodedData,
                             final Node root) {
            this.encodedData = encodedData;
            this.root = root;
        }
    }

    public static void main(String[] args) throws IOException {
        String filename = args[0];
        String textfile = ReadFile(filename);
        final String test = textfile;
        long totalTime = 0;
        long start;
        long tmp;

        System.out.print("Generating frequency data...");
        start = System.currentTimeMillis();
        final int[] ft = buildFrequencyTable(test);
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");

        System.out.print("Building the Huffman tree...");
        start = System.currentTimeMillis();
        final Node n = buildHuffmanTree(ft);
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");

        System.out.print("Generating the Huffman codes...");
        start = System.currentTimeMillis();
        final Map<Character, String> lookup = buildLookupTable(n);
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");

        System.out.print("Encoding the document...");
        start = System.currentTimeMillis();
        HuffmanEncodedResult encoding = compress(test);
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");

        //write out outputs
        System.out.print("Writing output file...");
        start = System.currentTimeMillis();
        filename = filename.replaceAll(".txt", "");
        String zipfile = filename + ".zip301";
        try(FileOutputStream fos=new FileOutputStream(zipfile)) {
            String temp = "";
            for (Map.Entry<Character, String> pair : lookup.entrySet()) {
                if (pair.getKey().equals(' ')) {
                    temp += pair.getValue() + " " + "space" + "\n";
                }
                else if (pair.getKey().equals('\n')) {
                    temp += pair.getValue() + " " + "newline" + "\n";
                }
                else if (pair.getKey().equals('\r')) {
                    temp += pair.getValue() + " " + "return" + "\n";
                }
                else if (pair.getKey().equals('\t')) {
                    temp += pair.getValue() + " " + "tab" + "\n";
                }
                else {
                    temp += pair.getValue() + " " + pair.getKey() + "\n";
                }
            }
            temp += "*****" + "\n" + encoding.encodedData.length() + "\n";
            fos.write(temp.getBytes());

            int m = encoding.encodedData.length();
            String dt = "";
            dt = encoding.encodedData;
            int rounded = RoundUp(m);

            if ((m % 8) != 0){
                for (int i=0; i<rounded; i++) {
                    dt = dt + "0";
                }
            }

            for (int i = 0; i < m; i += 8) {
                String singleByte = dt.substring(i, i + 8);
                int value = Integer.parseInt(singleByte, 2);
                fos.write(value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tmp = System.currentTimeMillis() - start;
        totalTime += tmp;
        System.out.println(tmp/1000.0 + " sec");
        System.out.println("Wrote output to: " + filename + ".zip301");
        System.out.println("TOTAL TIME: " + (totalTime / 1000.0) + " seconds.");
    }
}
