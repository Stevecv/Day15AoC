public class Main {
    public static void main(String[] args) {
        int total = 0;
        for (String code: PuzzleInput.puzzleInput.split(",")) {
            total += hash(code);
        }
        System.out.println(total);
    }

    public static int hash(String string) {
        int hashCode = 0;
        for (char c: string.toCharArray()) {
            hashCode = getHashValue(c, hashCode);
        }
        return hashCode;
    }

    public static int getHashValue(char c, int hashCode) {
        int ascii = c;
        hashCode += ascii;
        hashCode = hashCode * 17;
        hashCode = hashCode % 256;
        return hashCode;
    }
}
