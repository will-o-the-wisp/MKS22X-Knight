public class KnightBoard{
  private int[][] board;
  private int[][] outgoing;
  private int[][] moves;
  public static void main(String[] args){
    KnightBoard b = new KnightBoard(4,8);
    int ans=0;
    //System.out.println(b.countSolutions(0,0));
    /*for(int i=0;i<b.board.length;i++){
      for(int j=0;j<b.board[0].length;j++){
        ans+=b.countSolutions(i,j);
      }
    }*/
    //System.out.println(ans);
    System.out.println(b);
    System.out.println(b.printOutgoing());
    /*
    */
    for(int i=0;i<4;i++){
      runTest(i);
    }
  }
  //testcase must be a valid index of your input/output array
public static void runTest(int i){

  KnightBoard b;
  int[]m =   {4,5,5,5,5};
  int[]n =   {4,5,4,5,5};
  int[]startx = {0,0,0,1,2};
  int[]starty = {0,0,0,1,2};
  int[]answers = {0,304,32,56,64};
  if(i >= 0 ){
    try{
      int correct = answers[i];
      b = new KnightBoard(m[i%m.length],n[i%m.length]);

      int ans  = b.countSolutions(startx[i],starty[i]);

      if(correct==ans){
        System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
      }else{
        System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
      }
    }catch(Exception e){
      System.out.println("FAIL Exception case: "+i);

    }
  }
}
  //@throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols){
    int r=startingRows;
    int c=startingCols;
    if(r<=0||c<=0){
      throw new IllegalArgumentException();
    }
    board = new int[r][c];
    outgoing = new int[r][c];
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

    if(r>=4&&c>=4){
      outgoing[0][0]=2;
      outgoing[0][c-1]=2;
      outgoing[r-1][0]=2;
      outgoing[r-1][c-1]=2;
      outgoing[0][1]=3;
      outgoing[1][0]=3;
      outgoing[0][c-2]=3;
      outgoing[1][c-1]=3;
      outgoing[r-2][0]=3;
      outgoing[r-1][1]=3;
      outgoing[r-2][c-1]=3;
      outgoing[r-1][c-2]=3;
      outgoing[1][1]=4;
      outgoing[r-2][1]=4;
      outgoing[1][c-2]=4;
      outgoing[r-2][c-2]=4;
      for(int i=2;i<r-2;i++){
        outgoing[i][0]=4;
        outgoing[i][1]=6;
        outgoing[i][c-1]=4;
        outgoing[i][c-2]=6;
      }
      for(int j=2;j<c-2;j++){
        outgoing[0][j]=4;
        outgoing[1][j]=6;
        outgoing[r-1][j]=4;
        outgoing[r-2][j]=6;
      }
      for(int i=2;i<r-2;i++){
        for(int j=2;j<c-2;j++){
          outgoing[i][j]=8;
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
  private String printOutgoing(){
    String ans = "";
    for(int i=0;i<outgoing.length;i++){
      for(int j=0;j<outgoing[0].length;j++){
        if(outgoing[i][j]==0){
          ans+=" _";
        }
        else if(outgoing[i][j]<10){
          ans+=" "+outgoing[i][j];
        }
        else{
          ans+=outgoing[i][j];
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
