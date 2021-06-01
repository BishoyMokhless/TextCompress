import java.util.Scanner;
import java.util.Vector;

public class LZ78 {
    public int index;
    public char nextChar;
    public static Vector<String> dictionary = new Vector<String>();

    public LZ78(int index, char nextChar) {
        this.index = index;
        this.nextChar = nextChar;
    }

    public int getIndex() {
        return index;
    }

    public char getNextChar() {
        return nextChar;
    }


    public void setIndex(int index) {
        this.index = index;
    }

    public void setNextChar(char nextChar) {
        this.nextChar = nextChar;
    }

    @Override
    public String toString() {
        return "<" + index +
                ", " + nextChar +
                '>';
    }

    public static void main(String[] args) {
        String input = "ABAABABAABABBBBBBBBBBA";
        //String input = "ABAAB-";
        String temp;
        LZ78 t1 = new LZ78(0, input.charAt(0));
        Vector<LZ78> tags = new Vector<>();
        tags.addElement(t1);
        dictionary.add(null);
        dictionary.add(String.valueOf(input.charAt(0)));

        String search = "";
        LZ78 t2 = new LZ78(0, ' ');

        for (int i = 1; i < input.length(); i++) {
            search += input.charAt(i);
            if (!dictionary.contains(search)) {
                dictionary.add(search);
                String x = search.substring(0, search.length() - 1);
                int ind = 0;
                if (dictionary.contains(x))
                    ind = dictionary.indexOf(x);
                t2 = new LZ78(ind, search.charAt(search.length() - 1));
                tags.add(t2);
                search = "";
            }
            if (i == input.length() - 1) {
                if (dictionary.contains(search)) {
                    t2 = new LZ78(dictionary.indexOf(search), '\0');
                    tags.add(t2);
                } else {
                    dictionary.add(search);
                    String x = search.substring(0, search.length() - 1);
                    t2 = new LZ78(dictionary.indexOf(x), search.charAt(search.length() - 1));
                    tags.add(t2);
                }
            }
        }
        System.out.println(tags);
        for(LZ78 test:tags) {
        //for (int i = 0; i < tags.size(); i++) {
            //LZ78 test=tags.elementAt(i);
            if (test.index == 0) {
                System.out.print(test.nextChar);
            } else if (test.index > 0) {
                if (test.nextChar == '\0')
                    System.out.println(dictionary.elementAt(test.index));
                else System.out.print(dictionary.elementAt(test.index) + test.nextChar);
            }
        }

    }
}
