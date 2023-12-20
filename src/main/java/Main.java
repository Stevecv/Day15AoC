import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        // Generates a simple array of Linked Hashmaps
        ArrayList<LinkedHashMap<String, Integer>> boxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            boxes.add(new LinkedHashMap<>());
        }

        // Adds/Removes lenses
        for (String code: PuzzleInput.puzzleInput.split(",")) {
            if (code.endsWith("-")) {
                code = code.replace("-", "");
                int boxNum = hash(code);
                boxes.get(boxNum).remove(code);

            } else if (code.contains("=")) {
                String[] splitCode = code.split("=");
                code = splitCode[0];

                int lenseNum = Integer.parseInt(splitCode[1]);
                int boxNum = hash(code);
                LinkedHashMap<String, Integer> boxContents = new LinkedHashMap<>();
                boxContents.put(code, lenseNum);

                boxes.get(boxNum).put(code, lenseNum);
            }
        }

        //Calculates total power
        int totalFocusingPower = 0;
        for (int bn = 0; bn < boxes.size(); bn++) {
            LinkedHashMap<String, Integer> box = boxes.get(bn);
            int slotNum = 1;
            //Calculates power per lens and adds
            for (String code: box.keySet()) {
                int power = (bn+1)*slotNum*box.get(code);
                slotNum++;
                totalFocusingPower += power;
            }
        }

        System.out.println(totalFocusingPower);
    }

    // Hashes a string into an integer
    public static int hash(String string) {
        int hashCode = 0;
        for (char c: string.toCharArray()) {
            hashCode = getHashValue(c, hashCode);
        }
        return hashCode;
    }

    // Hashes a singular character into its integer value
    public static int getHashValue(char c, int hashCode) {
        int ascii = c;
        hashCode += ascii;
        hashCode = hashCode * 17;
        hashCode = hashCode % 256;
        return hashCode;
    }
}