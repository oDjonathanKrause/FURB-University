package wavefront;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class WaveFront {

    public static final int ORIG = 998;
    public static final int DEST = 999;
    public static final int WALL = -1;
    private int[][] matrix;

    /**
     * Faz a propação do wavefront e retorna o menor caminho
     */
    public Stack<Character> execute(int[][] matrix) {
        this.matrix = matrix;

        Point dest = findDest(matrix);
        propagate(dest);
        Stack<Character> path = findPath(dest);
        return path;
    }

    /**
     * Procura o número que representa o destino no mapa.
     */
    private Point findDest(int[][] matrix) {
        int x = 0, y = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == DEST) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        return new Point(x, y);
    }

    private Point findOrig() {
        int x = 0, y = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == ORIG) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        return new Point(x, y);
    }

    /**
     * Aplica o WaveFront
     *
     * @param Point ponto de destino no mapa
     */
    private void propagate(Point point) {
        Queue<Point> q = new LinkedList<>();
        Point dest = new Point(point.x, point.y);
        q.add(dest);

        while (!q.isEmpty()) {
            Point actualPoint = q.poll();
            point.x = actualPoint.x;
            point.y = actualPoint.y;

            int actualPointValue = matrix[point.x][point.y];

            Integer cima = null, direita = null, esquerda = null, baixo = null;

            try {
                direita = matrix[point.x][point.y + 1];
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                baixo = matrix[point.x + 1][point.y];
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                esquerda = matrix[point.x][point.y - 1];
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                cima = matrix[point.x - 1][point.y];
            } catch (IndexOutOfBoundsException e) {
            }

            int newValue = (actualPointValue == ORIG || actualPointValue == DEST ? 0 : actualPointValue) + 1;

            if (direita != null && direita == 0) {
                matrix[point.x][point.y + 1] = newValue;
                q.add(new Point(point.x, point.y + 1));
            }
            if (baixo != null && baixo == 0) {
                matrix[point.x + 1][point.y] = newValue;
                q.add(new Point(point.x + 1, point.y));
            }
            if (esquerda != null && esquerda == 0) {
                matrix[point.x][point.y - 1] = newValue;
                q.add(new Point(point.x, point.y - 1));
            }
            if (cima != null && cima == 0) {
                matrix[point.x - 1][point.y] = newValue;
                q.add(new Point(point.x - 1, point.y));
            }
        }
    }

    /**
     * Procura o menor caminho da origem até o destino
     *
     * @param Point destino
     * @return Stack com caracteres que representam o caminho
     */
    private Stack<Character> findPath(Point point) {
        Stack<Character> path = new Stack<>();
        Point robot = findOrig();
        int min = Integer.MAX_VALUE;
        char move = ' ';
        int moveX = 0, moveY = 0;

        point.x = robot.x;
        point.y = robot.y;

        int pivot = matrix[point.x][point.y];
        while (pivot != DEST) {
            Integer cima = null;
            Integer direita = null;
            Integer esquerda = null;
            Integer baixo = null;

            try {
                direita = matrix[point.x][point.y + 1];
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                baixo = matrix[point.x + 1][point.y];
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                esquerda = matrix[point.x][point.y - 1];
            } catch (IndexOutOfBoundsException e) {
            }

            try {
                cima = matrix[point.x - 1][point.y];
            } catch (IndexOutOfBoundsException e) {
            }

            if (direita != null && (direita == DEST)) {
                move = 'D';
                moveY = point.y + 1;
                moveX = point.x;
            } else if (baixo != null && (baixo == DEST)) {
                move = 'B';
                moveX = point.x + 1;
                moveY = point.y;
            } else if (esquerda != null && (esquerda == DEST)) {
                move = 'E';
                moveY = point.y - 1;
                moveX = point.x;
            } else if (cima != null && (cima == DEST)) {
                move = 'C';
                moveX = point.x - 1;
                moveY = point.y;
            } else {
                if (direita != null && min > direita && direita != WALL) {
                    min = direita;
                    move = 'D';
                    moveY = point.y + 1;
                    moveX = point.x;
                }
                if (baixo != null && min > baixo && baixo != WALL) {
                    min = baixo;
                    move = 'B';
                    moveX = point.x + 1;
                    moveY = point.y;
                }
                if (esquerda != null && min > esquerda && esquerda != WALL) {
                    min = esquerda;
                    move = 'E';
                    moveY = point.y - 1;
                    moveX = point.x;
                }
                if (cima != null && min > cima && cima != WALL) {
                    min = cima;
                    move = 'C';
                    moveX = point.x - 1;
                    moveY = point.y;
                }
            }

            point.x = moveX;
            point.y = moveY;
            min = Integer.MAX_VALUE;
            path.push(move);
            pivot = matrix[point.x][point.y];
        }
        return path;

    }
}
