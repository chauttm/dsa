import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RoadsAndLibraries {
    // Complete the roadsAndLibraries function below.


    static long uf_roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib < c_road) return (long)c_lib * n;

        int id[] = new int[n+1];
        for (int i = 0; i <= n; i++) id[i] = i;
        for (int[] pair: cities) {
            int u = root(pair[0], id);
            int v = root(pair[1], id);
            id[u] = id[v];
        }
        int count = 0;
        for (int i = 1; i <= n; i++)
            if (id[i] == i) count++;

        return (long)count * c_lib + (long)(n - count) * c_road;
    }

    private static int root(int i, int[] id) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    static long dfs_roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        if (c_lib < c_road) return (long)c_lib * n;

        List<Integer> adj[] = new List[n+1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] pair: cities) { adj[pair[0]].add(pair[1]); adj[pair[1]].add(pair[0]); }

        int count = 0;
        boolean visited[] = new boolean[n+1];
        Arrays.fill(visited, false);

        for (int u = 1; u <= n; u++) {
            if (!visited[u]) {
                count++;
                dfs(u, adj, visited);
            }
        }
        return (long)count * c_lib + (long)(n - count) * c_road;
    }

    private static void dfs(int u, List<Integer>[] adj, boolean[] visited) {
        visited[u] = true;
        for (int i: adj[u])
            if (!visited[i]) dfs(i, adj, visited);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
