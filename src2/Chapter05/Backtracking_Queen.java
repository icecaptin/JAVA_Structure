package Chapter05;

import java.awt.Point;
import java.util.*;

public class Backtracking_Queen {
    public static List<int[][]> SolveQueen() {
        List<int[][]> solutions = new ArrayList<>();
        int[][] board = new int[8][8];

        Stack<Point> stack = new Stack<>();
        int count = 0;
        int row = 0;

        while (true) {
            if (row == 8) {
                // 모든 퀸이 배치된 경우
                int[][] solution = copyBoard(board);
                solutions.add(solution);

                Point point = stack.pop();
                int col = (int) point.getY();
                board[row - 1][col] = 0;
                count--;
                row--;
                col++;
            }

            boolean placed = false;
            for (int col = 0; col < 8; col++) {
                if (isValid(board, row, col)) {
                    board[row][col] = 1;
                    count++;
                    stack.push(new Point(row, col));
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                if (stack.isEmpty()) {
                    // 모든 경우를 탐색한 경우 종료
                    break;
                }

                Point point = stack.pop();
                int col = (int) point.getY();
                board[row][col] = 0;
                count--;
                row--;
                col++;
            } else {
                row++;
            }
        }

        return solutions;
    }

    public static boolean isValid(int[][] board, int row, int col) {
        // 행 검사
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // 좌측 상단 대각선 검사
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // 우측 상단 대각선 검사
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, 8);
        }
        return copy;
    }

    public static void main(String[] args) {
        List<int[][]> solutions = SolveQueen();

        for (int[][] solution : solutions) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(" " + solution[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
