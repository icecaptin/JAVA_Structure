package Chapter05;

import java.util.Stack;

enum Directions { N, NE, E, SE, S, SW, W, NW }

class Items {
    int x;
    int y;
    int dir;

    public Items(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

class Offsets {
    int a;
    int b;

    public Offsets(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class MazeProblem {

    static Offsets[] moves = new Offsets[8];

    public static void path(int[][] maze, int[][] mark, int ix, int iy) {
        mark[1][1] = 1;
        Stack<Items> st = new Stack<>();
        Items temp = new Items(0, 0, 0);
        temp.x = 1;
        temp.y = 1;
        temp.dir = 2;
        st.push(temp);

        while (!st.isEmpty()) {
            Items tmp = st.pop();
            int i = tmp.x;
            int j = tmp.y;
            int d = tmp.dir;

            while (d < 8) {
                int g = i + moves[d].a;
                int h = j + moves[d].b;

                if ((g == ix) && (h == iy)) {
                    mark[g][h] = 2;
                    printBoard(maze, mark);
                    return;
                }

                if ((g >= 0) && (g < maze.length) && (h >= 0) && (h < maze[0].length) && (maze[g][h] == 0) && (mark[g][h] == 0)) {
                    Items next = new Items(g, h, 0);
                    mark[g][h] = 2;
                    st.push(next);
                    i = g;
                    j = h;
                    d = 0;
                } else {
                    d++;
                }
            }
        }

        System.out.println("No path in maze.");
    }

    public static void printBoard(int[][] maze, int[][] mark) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (mark[i][j] == 2) {
                    System.out.print("2 ");
                } else if (maze[i][j] == 1) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
            { 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 1 },
            { 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1 },
            { 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0 },
            { 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
            { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
            { 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1 },
            { 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
            { 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 },
            { 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0 }
        };

        int[][] mark = new int[maze.length][maze[0].length];

        for (int ia = 0; ia < 8; ia++) {
            moves[ia] = new Offsets(0, 0);
        }
        moves[0].a = -1; moves[0].b = 0;
        moves[1].a = -1; moves[1].b = 1;
        moves[2].a = 0; moves[2].b = 1;
        moves[3].a = 1; moves[3].b = 1;
        moves[4].a = 1; moves[4].b = 0;
        moves[5].a = 1; moves[5].b = -1;
        moves[6].a = 0; moves[6].b = -1;
        moves[7].a = -1; moves[7].b = -1;

        System.out.println("maze[12,15]:");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("mark:");
        for (int i = 0; i < mark.length; i++) {
            for (int j = 0; j < mark[0].length; j++) {
                System.out.print(mark[i][j] + " ");
            }
            System.out.println();
        }

        path(maze, mark, 11, 14);

        System.out.println("mark:");
        for (int i = 0; i < mark.length; i++) {
            for (int j = 0; j < mark[0].length; j++) {
                System.out.print(mark[i][j] + " ");
            }
            System.out.println();
        }
    }
}
