public class KnightBoard{
  private int[][] board;
  private int[][] outgoing;
  private int[][] moves;
  public static void main(String[] args){
    KnightBoard b = new KnightBoard(5,5);
    int ans=0;
    System.out.println(b.countSolutions(0,0));
    /*for(int i=0;i<b.board.length;i++){
      for(int j=0;j<b.board[0].length;j++){
        ans+=b.countSolutions(i,j);
      }
    }*/
    //System.out.println(ans);
    System.out.println(b);
  }
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    if(startingRows<=0||startingCols<=0){
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    outgoing = new int[startingRows][startingCols];
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
    if(startingRows==1||startingCols==1){
      for(int i=0;i<outgoing.length;i++){
        for(int j=0;j<outgoing[0].length;j++){
          outgoing[i][j]=0;
        }
      }
    }
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
    if(startingRow<0||startingCol<0||startingRow>=board.length||
    startingCol>=board[0].length){
      throw new IllegalArgumentException();
    }
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[0].length;j++){
        if(board[i][j]!=0){
          throw new IllegalStateException();
        }
      }
    }
    board[startingRow][startingCol]=1;
    boolean ans = solveH(startingRow, startingCol, 2);
    if(!ans){
      board[startingRow][startingCol]=0;
    }
    return ans;
  }
  private boolean solveH(int row, int col, int level){
    if(level==board.length*board[0].length+1){
      return true;
    }
    for(int i=0;i<moves.length;i++){
      if(addKnight(row+moves[i][0],col+moves[i][1],level)){
            if(solveH(row+moves[i][0],col+moves[i][1],level+1)){
              return true;
            }
            removeKnight(row+moves[i][0],col+moves[i][1]);
         }
    }
    return false;
  }
  private boolean addKnight(int r, int c, int l){
    if(r<board.length&&
        r>=0&&
        c<board[0].length&&
        c>=0&&
        board[r][c]==0){
          board[r][c]=l;
          return true;
        }
    return false;
  }
  private void removeKnight(int r, int c){
    board[r][c]=0;
  }
  /*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  */
  public int countSolutions(int startingRow, int startingCol){
    if(startingRow<0||startingCol<0||startingRow>=board.length||
    startingCol>=board[0].length){
      throw new IllegalArgumentException();
    }
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[0].length;j++){
        if(board[i][j]!=0){
          throw new IllegalStateException();
        }
      }
    }
    board[startingRow][startingCol]=1;
    int ans=countSolutionsH(startingRow,startingCol,2);
    board[startingRow][startingCol]=0;
    return ans;
  }
  private int countSolutionsH(int row, int col, int level){
    int ans=0;
    if(level==board.length*board[0].length+1){
      return 1;
    }
    for(int i=0;i<moves.length;i++){
      if(addKnight(row+moves[i][0],col+moves[i][1],level)){
            ans+=countSolutionsH(row+moves[i][0],col+moves[i][1],level+1);
            removeKnight(row+moves[i][0],col+moves[i][1]);
         }
    }
    return ans;
  }
}
