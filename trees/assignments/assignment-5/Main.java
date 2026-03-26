import java.util.*;

public class Main {

    static int V;
    static int[][] adjMatrix;
    static ArrayList<ArrayList<Integer>> adjList;
    static Scanner sc = new Scanner(System.in);

    // Create Graph
    static void createGraph() {
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();

        adjMatrix = new int[V][V];
        adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        for (int i = 0; i < e; i++) {
    System.out.print("Enter edge (u v): ");
    int u = sc.nextInt();
    int v = sc.nextInt();

    
    if (u < 0 || v < 0 || u >= V || v >= V) {
        System.out.println("Invalid edge! Vertex out of range. Try again.");
        i--; // retry same edge
        continue;
    }

   
    adjMatrix[u][v] = 1;
    adjMatrix[v][u] = 1;

    adjList.get(u).add(v);
    adjList.get(v).add(u);
}

        System.out.println("Graph created successfully!");
    }

    // ===== BFS MATRIX (Handles disconnected) =====
    static void bfsMatrix(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        System.out.print("BFS (Matrix): ");

        // Start from given node
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int i = 0; i < V; i++) {
                if (adjMatrix[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }

        // Handle disconnected components
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfsMatrixUtil(i, visited);
            }
        }

        System.out.println();
    }

    static void bfsMatrixUtil(int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int i = 0; i < V; i++) {
                if (adjMatrix[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    // ===== DFS MATRIX =====
    static void dfsMatrix(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < V; i++) {
            if (adjMatrix[node][i] == 1 && !visited[i]) {
                dfsMatrix(i, visited);
            }
        }
    }

    static void dfsMatrixFull(int start) {
        boolean[] visited = new boolean[V];

        System.out.print("DFS (Matrix): ");
        dfsMatrix(start, visited);

        // Handle disconnected
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsMatrix(i, visited);
            }
        }

        System.out.println();
    }

    // ===== BFS LIST =====
    static void bfsList(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new LinkedList<>();

        System.out.print("BFS (List): ");

        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }

        // Handle disconnected
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfsListUtil(i, visited);
            }
        }

        System.out.println();
    }

    static void bfsListUtil(int start, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }

    // ===== DFS LIST =====
    static void dfsList(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfsList(neighbor, visited);
            }
        }
    }

    static void dfsListFull(int start) {
        boolean[] visited = new boolean[V];

        System.out.print("DFS (List): ");
        dfsList(start, visited);

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsList(i, visited);
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        createGraph();

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. BFS (Matrix)");
            System.out.println("2. DFS (Matrix)");
            System.out.println("3. BFS (List)");
            System.out.println("4. DFS (List)");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter start node: ");
                int start = sc.nextInt();

                if (start < 0 || start >= V) {
                    System.out.println("Invalid start node!");
                    continue;
                }

                switch (choice) {
                    case 1:
                        bfsMatrix(start);
                        break;
                    case 2:
                        dfsMatrixFull(start);
                        break;
                    case 3:
                        bfsList(start);
                        break;
                    case 4:
                        dfsListFull(start);
                        break;
                }
            }

        } while (choice != 5);

        sc.close();
    }
}