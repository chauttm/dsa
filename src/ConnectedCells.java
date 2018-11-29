import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Tmct on 11/17/2018.
 */
public class ConnectedCells {
    // Complete the connectedCell function below.
    static int connectedCell(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    int count = visit(0, i,j, matrix);
                    max = count > max ? count : max;
                }
            }
        return max;
    }

    private static int visit(int count, int i, int j, int[][] matrix) {
        try {
            if (matrix[i][j] == 0) return count;
        }
        catch (RuntimeException e) {
            return count;
        }
        matrix[i][j] = 0;
        count = visit(count+ 1, i+1, j, matrix);
        count = visit(count, i-1, j, matrix);
        count = visit(count, i, j+1, matrix);
        count = visit(count, i, j-1, matrix);
        count = visit(count, i+1, j+1, matrix);
        count = visit(count, i+1, j-1, matrix);
        count = visit(count, i-1, j+1, matrix);
        return visit(count, i-1, j-1, matrix);
    }

    static int stack_connectedCell(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[] stack = new int[100];
        int top = -1;
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    stack[++top] = i*m + j;
                    int count = 0;
                    do {
                        int current = stack[top--];
                        int u = current / m;
                        int v = current % m;
                        if (matrix[u][v] != 1) continue;
                        matrix[u][v] = 0;
                        count++;
                        System.err.println(u + ", " + v + " " + count);
                        if (valid(u + 1, v, matrix)) stack[++top] = current + m;
                        if (valid(u - 1, v, matrix)) stack[++top] = current - m;
                        if (valid(u, v + 1, matrix)) stack[++top] = current + 1;
                        if (valid(u, v - 1, matrix)) stack[++top] = current - 1;
                        if (valid(u + 1, v + 1, matrix)) stack[++top] = current + m + 1;
                        if (valid(u + 1, v - 1, matrix)) stack[++top] = current + m - 1;
                        if (valid(u - 1, v + 1, matrix)) stack[++top] = current - m + 1;
                        if (valid(u - 1, v - 1, matrix)) stack[++top] = current - m - 1;
                    } while (top >= 0);
                    max = count > max ? count : max;
                }
            }
        return max;
    }

    private static boolean valid(int i, int j, int[][] matrix) {
        try {
            return matrix[i][j] == 1;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] matrixRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int matrixItem = Integer.parseInt(matrixRowItems[j]);
                matrix[i][j] = matrixItem;
            }
        }

        int result = connectedCell(matrix);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();

        System.out.println(result);

        scanner.close();
    }
}
