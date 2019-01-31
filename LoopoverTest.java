import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
public class LoopoverTest {
   private Looper l;
   @Test
   public void FixedTest()
   {
      l=new Looper();
      Loopover L;
      l.up(0);
      l.left(0);
      l.down(0);
      l.right(0);
      L=l.loopdyDoop();
      List<String> a=L.solve();
      System.out.println("Testing for:");
      System.out.println(l.boardString());
      assertEquals(true, l.valid(a, l.newBoard()));
   }
   @Test
   public void RandomTests()
   {
      Looper l=new Looper();
      Loopover L;
      List<String> a;
      for(int i=0; i<100; i++)
      {
         l.scramble();
         L=l.loopdyDoop();
         a=L.solve();
         System.out.println("Testing for:");
         System.out.println(l.boardString());
         assertEquals(true, l.valid(a, l.newBoard()));
      }
   }
}