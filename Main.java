public class Main {
    public static void main(String[] args) {
        int row = 0;
        int col = 2;
        for (int i = 0; i < 9; i++) {
            System.out.println("row " + 3 * (row / 3) + i / 3);
            System.out.println("col " + 3 * (col / 3) + i % 3);
        }
    }

}