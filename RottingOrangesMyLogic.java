import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOrangesMyLogic {

  static class Cell {
    int row;
    int col;
    int time;

    Cell(int row, int col, int time) {
      this.row = row;
      this.col = col;
      this.time = time;
    }
  }

  static int timeTakenToRotten(int[][] grid, int oranges) {
    if (oranges == 0) return 0;

    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    boolean[][] isvisited = new boolean[grid.length][grid[0].length];

    int totalTime = -1;

    Queue<Cell> q = new ArrayDeque<>();

    q.add(new Cell(0, 0, 0));
    isvisited[0][0] = true;

    while (!q.isEmpty()) {
      Cell current = q.remove();

      for (int[] dir : directions) {

        int newRow = current.row + dir[0];
        int newCol = current.col + dir[1];

        if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == 1 && !isvisited[newRow][newCol]) {
          q.add(new Cell(newRow, newCol, current.time + 1));
          oranges--;

          isvisited[newRow][newCol] = true;
        }
      }
      totalTime = current.time;
    }

    return oranges == 0 ? totalTime : -1;

  }

  public static void main(String[] args) {
    int[][] grid = {
        { 2, 1, 1 },
        { 0, 1, 1 },
        { 1, 0, 1 }

    };

    int oranges = 0;
    int rotten = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) oranges++;
        else if (grid[i][j] == 2) rotten++;
      }
    }
    if (rotten == 0) {
      System.out.println(-1);
      return;
    }
    if (oranges == 0) {
      System.out.println(0);
      return;
    }
    
    int time = timeTakenToRotten(grid, oranges);

    System.out.println(time);
    
  }

}
