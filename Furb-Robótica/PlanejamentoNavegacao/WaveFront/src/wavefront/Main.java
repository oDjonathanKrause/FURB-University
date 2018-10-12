package wavefront;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int[][] matrix =
        {{0,  0,  0,  0,  0,  0,  999},
         {0,  0,  0, -1, -1,  0,  0},
         {0,  0, -1,  0,  0,  0, -1},
         {0,  0,  0,  0, -1,  0,  0},
         {0, -1,  0,  0,  0,  0,  0},
         {0,  0,  0, -1,  0, -1,  0},
         {0,  0,  0,  0,  0,  0,  998}};
        
        Stack<Character> path = new WaveFront().execute(matrix);
        System.out.println("Matriz:\n" + toString(matrix) + "\n");
        System.out.println("Caminho: " + path);
    }

    public static String toString(int[][] matrix) {
        String strMatrix = new String();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                strMatrix += matrix[i][j] + "\t";
            }
            strMatrix += "\n";
        }

        return strMatrix;
    }

    public static void test() {
        // Matriz de teste. Resultado deve ser [B, B, E, E, E, B, E, E, C]
        int[][] testMatrix = {
            {0, -1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, -1, 0, 0},
            {0, -1, 0, 0, 0, 0, 0},
            {0, 0, 0, -1, -1, 998, 0},
            {-1, 0, 0, 0, 0, 0, 0},
            {999, -1, 0, 0, 0, 0, 0},
            {0, 0, 0, -1, -1, -1, 0}};

        Stack<Character> path = new WaveFront().execute(testMatrix);
        System.out.println("Matriz:\n" + toString(testMatrix) + "\n");
        System.out.println("Caminho: " + path);
    }

}
