public class BFS {

  

  public static void main(String[] args) {
    int sr = 0;
    int sc = 0;

    int row = 3;
    int col = 3;
    paths(sr, sc, row - 1, col - 1, "", new boolean[row][col]);

  }

  private static void paths(int sr, int sc, int row, int col, String string, boolean[][] isvisited) {

    if (sr < 0 || sc < 0 || sr > row || sc > col || isvisited[sr][sc] == true) {
      return;
    }
    if (sr == row && sc == col) {
      System.out.println(string);
      return;
    }
    isvisited[sr][sc] = true;
    paths(sr + 1, sc, row, col, string + "D", isvisited);
    paths(sr, sc + 1, row, col, string + "R", isvisited);
    paths(sr - 1, sc, row, col, string + "T", isvisited);
    paths(sr, sc - 1, row, col, string + "L", isvisited);
    isvisited[sr][sc] = false;
  }
  
}
