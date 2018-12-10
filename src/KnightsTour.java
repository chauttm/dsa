import java.util.Arrays;

public class KnightsTour {
    final int n;
    int[][] board;
    int[] offsetX = {-2, -2, -1, -1, 1,  1,  2,  2};
    int[] offsetY = {-1,  1,  2, -2, 2, -2,  1, -1};

    KnightsTour(int n) {
        this.n = n;
        board = new int[n][n];
        for (int[] row : board) Arrays.fill(row, -1);
        board[0][0] = n*n;
        dfs(0, 0);
    }

    private boolean dfs(int x, int y) {
        if (board[x][y] == 1) return true;

        for (int i = 0; i < 8; i++) {
            int nextX = x + offsetX[i];
            int nextY = y + offsetY[i];
            if (onBoard(nextX, nextY) && board[nextX][nextY] < 0) {
                board[nextX][nextY] = board[x][y] - 1;
                if (dfs(nextX, nextY)) return true;
                board[nextX][nextY] = -1;
            }
        }
        return false;
    }

    private boolean onBoard(int x, int y) {
        return (x < n && y < n && x >=0 && y >= 0);
    }

    private void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) System.out.printf("%3d", board[i][j]);
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        KnightsTour horse = new KnightsTour(5);
        horse.print();
        horse = new KnightsTour(6);
        horse.print();
        horse = new KnightsTour(7);
        horse.print();
        horse = new KnightsTour(8);
        horse.print();
    }
}