import java.util.*;

public class RottingOranges {

  static int rottenOranges(int[][] grid) {
    Queue<int[]> q = new ArrayDeque<>();
    int freshOranges = 0;

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 2) q.add(new int[]{i ,j});
        else if (grid[i][j] == 1) freshOranges++;
      }
    }
    int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    int time = 0;

    while (!q.isEmpty() && freshOranges > 0) {
      time++;
      int size = q.size();

      while (size-- > 0) {
        int[] xy = q.poll();

        for (int[] d : directions) {
          int x = xy[0] + d[0];
          int y = xy[1] + d[1];

          if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0 || grid[x][y] == 2) continue;

          q.add(new int[]{x, y});
          grid[x][y] = 2;
          freshOranges--;
        }
      }
    }

    return freshOranges == 0 ? time : -1;
  }

  public static void main(String[] args) {
    int[][] grid = {
        { 2, 1, 1 },
        { 0, 1, 1 },
        { 1, 0, 1 }
    };

    int time = rottenOranges(grid);
    System.out.println(time);
  }

}
