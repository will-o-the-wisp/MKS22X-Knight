public class KnightBoard{
  private int[][] board;
  public static void main(String[] args){
    KnightBoard b = new KnightBoard(3,10);
    System.out.println(b);
  }
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
  }
  public String toString(){
    String ans = "";
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[0].length;j++){
        if(board[i][j]==0){
          ans+=" _";
        }
        else if(board[i][j]<10){
          ans+=" "+board[i][j];
        }
        else{
          ans+=board[i][j];
        }
        ans+=" ";
      }
      ans+="\n";
    }
    return ans;
  }
  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  */
  public boolean solve(int startingRow, int startingCol){
    return solveH(startingRow, startingCol, 0);
  }
  private boolean solveH(int row, int col, int level){
    if(level==board.length*board[0].length){
      return true;
    }

    return false;
  }
  private boolean addKnight(int r, int c, int l){
    board[r][c]=l;
  }
  private boolean removeKnight(int r, int c){
    board[r][c]=0;
  }
  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol){
    return 0;
  }
}
