public class KnightBoard{
  private int[][] board;
  private int[][] moves;
  public static void main(String[] args){
    KnightBoard b = new KnightBoard(5,5);
    b.solve(0,0);
    System.out.println(b);
  }
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    moves = new int[][]{
      {1,2},
      {1,-2},
      {-1,2},
      {-1,-2},
      {2,1},
      {2,-1},
      {-2,1},
      {-2,-1},
    };
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
    if(level==board.length*board[0].length-1){
      return true;
    }
    for(int i=0;i<moves.length;i++){
      if(row+moves[i][0]<board.length&&
         row+moves[i][0]>0&&
         col+moves[i][1]<board[0].length&&
         col+moves[i][1]>0&&
         board[row+moves[i][0]][col+moves[i][1]]==0){
            board[row+moves[i][0]][col+moves[i][1]]=level;
            if(solveH(row+moves[i][0],col+moves[i][1],level+1)){
              return true;
            }
            board[row+moves[i][0]][col+moves[i][1]]=0;
         }
    }
    return false;
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
