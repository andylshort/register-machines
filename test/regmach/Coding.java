package regmach;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class Coding {

  @Test
  public void test() {
    HashMap<Integer, Instruction> instrs = new HashMap<Integer, Instruction>();
    instrs.put(0, new SubInstr(0, 0, 2));
    instrs.put(1, new HaltInstr());
    
    Program p = new Program(instrs);
    
    //System.out.println(p.code());
    assertEquals(p.code(), 786432);
  }

}
