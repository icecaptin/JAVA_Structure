package Chapter05;
import java.util.Stack;
import java.awt.Point;


public class Backtracking_Queen {
    public static void SolveQueen(int[][] d) {
        int count = 0;
        Stack<Point> st = new Stack<>();

        for (int ix = 0; ix < d.length; ix++) {
            for (int iy = 0; iy < d.length; iy++) {
                if (NextMove(d, ix, iy)) {
                    Point p = new Point(ix, iy);
                    d[ix][iy] = 1;
                    count++;
                    st.push(p);
                    break;
                }
            }
        }

        while (count < 8) {
            Point p = st.pop();
            int ix = (int) p.getX();
            int iy = (int) p.getY();
            d[ix][iy] = 0;
            count--;

            for (int nextiy = iy + 1; nextiy < d[0].length; nextiy++) {
                if (NextMove(d, ix, nextiy)) {
                    Point nextPoint = new Point(ix, nextiy);
                    d[ix][nextiy] = 1;
                    count++;
                    st.push(nextPoint);
                    break;
                }
            }
        }
    }

    public static boolean checkrow(int[][] d, int crow) {
    	//배열 d에서 crow에 Queen 을 놓을수 잇나?
        for (int iy = 0; iy < d.length; iy++) {
            if (d[crow][iy] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkcol(int[][] d, int ix, int iy) {
        for (int i = 0; i < d.length; i++) {
            if (d[i][iy] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagSW(int[][] d, int cx, int cy) {
    	//배열 d에 d[cx][cy]의 sw대각선에 배치가능?
        int x = cx + 1;
        int y = cy - 1;

        while (x < d.length && y >= 0) {
            if (d[x][y] == 1) {
                return false;
            }
            x++;
            y--;
        }
        return true;
    }

    public static boolean checkDiagSE(int[][] d, int cx, int cy) {
        int x = cx + 1;
        int y = cy + 1;

        while (x < d.length && y < d[0].length) {
            if (d[x][y] == 1) {
                return false;
            }
            x++;
            y++;
        }
        return true;
    }

    public static boolean NextMove(int[][] d, int ix, int iy) {
        return checkrow(d, ix) && checkcol(d, ix, iy) && checkDiagSW(d, ix, iy) && checkDiagSE(d, ix, iy);
    }

    public static void main(String[] args) {
        int[][] data = new int[8][8]; //8x8 
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = 0;
            }
        }

        SolveQueen(data);

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(" " + data[i][j]);
            }
            System.out.println();
        }
    }
}