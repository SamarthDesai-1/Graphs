import java.util.*;

public class NearestEntrance {

  static class Cell {
    int row;
    int col;
    int steps;

    Cell(int row, int col, int steps) {
      this.row = row;
      this.col = col;
      this.steps = steps;
    }
  }

  static int nearestExit(char[][] maze, int[] entrance) {
    int m = maze.length;
    int n = maze[0].length;

    /* 
      { -1, 0 } = Going UP
      { 1, 0 } = Going DOWN
      { 0, -1 } = Going LEFT
      { 0, 1 } = Going RIGHT
    */
    
    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    boolean[][] isvisited = new boolean[m][n];

    Queue<Cell> q = new ArrayDeque<>();
    q.add(new Cell(entrance[0], entrance[1], 0));
    isvisited[entrance[0]][entrance[1]] = true;

    while (!q.isEmpty()) {
      Cell current = q.remove();

      for (int[] dire : directions) {

        int newRow = current.row + dire[0];
        int newCol = current.col + dire[1];

        
        if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && maze[newRow][newCol] == '.' && !isvisited[newRow][newCol]) {

          if (newRow == 0 || newRow == m - 1 || newCol == 0 || newCol == n - 1) {
            return current.steps + 1;
          }
          q.add(new Cell(newRow, newCol, current.steps + 1));
          isvisited[newRow][newCol] = true;   
        }
      }

    }
    return -1;
  }

  public static void main(String[] args) {
    char[][] maze = {
        { '+', '+', '.','+' },
        { '.', '.', '.','+' },
        { '+', '+', '+','.' },
    };

    int[] entrance = { 1, 2 };

    System.out.println(nearestExit(maze, entrance));

  }

}
