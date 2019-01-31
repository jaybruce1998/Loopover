import java.util.ArrayList;
public class Loopover
{
   private char[][] board, b;
   public Loopover(char[][] board, char[][] b)
   {
      this.board=board;
      this.b=b;
   }
   public char[][] getBoard()
   {
      return board;
   }
   public String left(int r)
   {
      char c=board[r][0];
      for(int i=0; i<4; i++)
         board[r][i]=board[r][i+1];
      board[r][4]=c;
      return "L"+r;
   }
   public String right(int r)
   {
      char c=board[r][4];
      for(int i=4; i>0; i--)
         board[r][i]=board[r][i-1];
      board[r][0]=c;
      return "R"+r;
   }
   public String up(int c)
   {
      char t=board[0][c];
      for(int i=0; i<4; i++)
         board[i][c]=board[i+1][c];
      board[4][c]=t;
      return "U"+c;
   }
   public String down(int c)
   {
      char t=board[4][c];
      for(int i=4; i>0; i--)
         board[i][c]=board[i-1][c];
      board[0][c]=t;
      return "D"+c;
   }
   public char[][] newBoard()
   {
      char[][] b=new char[5][5];
      for(int i=0; i<5; i++)
         for(int j=0; j<5; j++)
            b[i][j]=(char)(65+i*5+j);
      return b;
   }
   private int[] coordinatesOf(char[][] b, char c)
   {
      for(int i=0; i<5; i++)
         for(int j=0; j<5; j++)
            if(b[i][j]==c)
               return new int[]{i, j};
      return new int[]{-1, -1};
   }
   private ArrayList<String> rowify(char l)
   {
      ArrayList<String> a=new ArrayList<>();
      int[] coord=coordinatesOf(board, l);
      int r=coord[0], c=coord[1];
      if(r==4)
      {
         a.add(down(c));
         a.add(right(0));
         a.add(up(c));
         r=0;
         c=(c+1)%5;
      }
      for(int i=c; i<4; i++)
         a.add(right(r));
      for(int i=r; i<4; i++)
         a.add(down(4));
      a.add(left(4));
      return a;
   }
   private ArrayList<String> colify(char l)
   {
      ArrayList<String> a=new ArrayList<>();
      int[] coord=coordinatesOf(board, l);
      int r=coord[0], c=coord[1];
      if(c==4)
      {
         for(int i=r; i<4; i++)
            a.add(down(4));
         a.add(left(4));
         for(int i=r; i<4; i++)
            a.add(up(4));
         c=3;
      }
      for(int i=c; i<4; i++)
         a.add(right(4));
      return a;
   }
   private int discrepancy()
   {
      for(int i=1; i<5; i++)
         if(board[4][i]!=b[4][i])
            return i;
      return 0;
   }
   public ArrayList<String> solve()
   {
      ArrayList<String> a=new ArrayList<>();
      int c;
      for(int i=0; i<4; i++)
      {
         for(int j=0; j<4; j++)
            a.addAll(rowify(b[i][j]));
         for(int j=0; j<4; j++)
            a.add(up(j));
      }
      for(int i=1; i<4; i++)
      {
         a.addAll(colify(b[i][4]));
         a.add(up(4));
      }
      a.addAll(colify(b[4][0]));
      a.add(right(4));
      for(int d=discrepancy(); d>0; d=discrepancy())
      {
         if(board[0][4]==b[0][4])
         {
            for(int i=d; i<4; i++)
               a.add(right(4));
            a.add(up(4));
            for(int i=d; i<4; i++)
               a.add(left(4));
            a.add(down(4));
         }
         c=coordinatesOf(b, board[0][4])[1];
         for(int i=c; i<4; i++)
            a.add(right(4));
         a.add(up(4));
         for(int i=c; i<4; i++)
            a.add(left(4));
         if(board[3][4]==b[0][4])
         {
            d=discrepancy();
            for(int i=d; i<4; i++)
               a.add(right(4));
            a.add(down(4));
            for(int i=d; i<4; i++)
               a.add(left(4));
         }
         else
         {
            c=coordinatesOf(b, board[3][4])[1];
            for(int i=c; i<4; i++)
               a.add(right(4));
            a.add(down(4));
            for(int i=c; i<4; i++)
               a.add(left(4));
         }
      }
      return a;
   }
   public boolean equals(char[][] b)
   {
      for(int i=0; i<5; i++)
         for(int j=0; j<5; j++)
            if(board[i][j]!=b[i][j])
               return false;
      return true;
   }
}