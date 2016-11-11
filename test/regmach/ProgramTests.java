package regmach;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class ProgramTests {
  
  @Test
  public void identicalProgramsHaveIdenticalHashCodes() {
    HashMap<Integer, Instruction> instrs1 = new HashMap<Integer, Instruction>();
    instrs1.put(0, new SubInstr(0, 0, 1));
    instrs1.put(1, new HaltInstr());
    
    Program program1 = new Program(instrs1);
    
    
    HashMap<Integer, Instruction> instrs2 = new HashMap<Integer, Instruction>();
    instrs2.put(0, new SubInstr(0, 0, 1));
    instrs2.put(1, new HaltInstr());
    
    Program program2 = new Program(instrs2);
    
    assertEquals(program1.hashCode(), program2.hashCode());
  }
  
  @Test
  public void identicalProgramsAreEqual() {
    HashMap<Integer, Instruction> instrs1 = new HashMap<Integer, Instruction>();
    instrs1.put(0, new SubInstr(0, 0, 1));
    instrs1.put(1, new HaltInstr());
    
    Program program1 = new Program(instrs1);
    
    
    HashMap<Integer, Instruction> instrs2 = new HashMap<Integer, Instruction>();
    instrs2.put(0, new SubInstr(0, 0, 1));
    instrs2.put(1, new HaltInstr());
    
    Program program2 = new Program(instrs2);
    
    assertEquals(program1, program2);
  }
  
  
  /*
   * Decoding
   */
  @Test
  public void listOfInstructionsAndCodedProgramAreEqualToOneAnother() {
    int value = 786432;
    
    HashMap<Integer, Instruction> instrs = new HashMap<Integer, Instruction>();
    instrs.put(0, new SubInstr(0, 0, 2));
    instrs.put(1, new HaltInstr());
    Program correctP = new Program(instrs);
    
    
    Program p = Program.fromValue(value);
    
    assertEquals(p, correctP);
  }
  
  @Test
  public void zeroShouldReturnNothing() {
    int value = 0;
    Program p = Program.fromValue(value);
    
    assertEquals(p.length(), 0);
    assertEquals(p.toString(), "");
  }
  
  
  /*
   * Coding
   */
  @Test
  public void programCodesCorrectlyToRespectiveGodelNumbering() {    
    int expectedCodedValue = 786432;
    
    HashMap<Integer, Instruction> instrs = new HashMap<Integer, Instruction>();
    instrs.put(0, new SubInstr(0, 0, 2));
    instrs.put(1, new HaltInstr());
    
    Program p = new Program(instrs);
    
    assertEquals(p.code(), expectedCodedValue);
  }
}
