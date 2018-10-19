package wavefront;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int[][] matrix =
        {{ 0,  0,   0,   0,   0,  0,  0 },
         { 0, -1,   0,  -1,  -1,  0,  998 },
         { 0,  0,  -1,   0,   0,  0, -1 },
         { 0,  0,   0,   0,   -1, -1,  999 },
         { 0, -1,   0,  -1,   0,  0,  0 },
         { 0,  0,   0,   0,   0,  0,  0 },
         { 0,  0,   0,   0,   0,  0,  0 }};
        
        Stack<Character> path = new WaveFront().execute(matrix);
        System.out.println("Matriz:\n" + toString(matrix) + "\n");
        System.out.println("Caminho: " + path);
        
        String str = "{ ";
        for (Character c : path) {
            str += "'" + c.charValue() + "', "; 
        }
        System.out.println(str);
        
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

}
