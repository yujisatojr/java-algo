import java.util.Scanner;

public class Decode {
    // declare the alphabet variable for referring to index
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        // read data from standard input
        Scanner s = new Scanner(System.in);
        String line = s.nextLine();
        int key = s.nextInt();
        s.close();

        // loop through the line and figure out a specific index
        char letter;
        int index;
        char cipher;
        for (int i=0; i<line.length(); i++) {
            // each character in the line
            letter = line.charAt(i);

            // if there is a space in the line, print out a space
            if (letter == ' ') {
                System.out.print(" ");
                continue;
            }

            // specify the index of the letter from key
            index = alphabet.indexOf(letter);
            index -= key;

            // when the index go under 0, get the mod of the index by dividing the number by 26
            if (index < 0) {
                index += 26;
            }
            // use mod to keep the number in the correct range
            index = index % 26;

            // get the alphabet letters of the new index
            cipher = alphabet.charAt(index);

            // print the output
            System.out.print(cipher);
        }
    }
}
