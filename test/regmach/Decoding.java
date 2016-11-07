package regmach;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class Decoding {

  @Test
  public void test() {
    int value = 786432;
    
    HashMap<Integer, Instruction> instrs = new HashMap<Integer, Instruction>();
    instrs.put(0, new SubInstr(0, 0, 2));
    instrs.put(1, new HaltInstr());
    Program correctP = new Program(instrs);
    
    
    Program p = Program.fromValue(value);
    // System.out.println(p.getInstructions().hashCode());
    // System.out.println(correctP.getInstructions().hashCode());
    
    // System.out.println(p.toString());
    // System.out.println(correctP.toString());
    
    assertEquals(p.toString(), correctP.toString());
  }

}
