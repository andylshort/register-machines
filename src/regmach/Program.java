package regmach;

import java.util.HashMap;
import java.util.Map;

public class Program {

  private RegisterMachine rm;
  private Map<Integer, Instruction> instructions;

  public Program() {
    this.instructions = new HashMap<Integer, Instruction>();
  }
  
  public int length() {
    return instructions.size();
  }

  public Instruction getInstruction(int index) {
    return instructions.get(index);
  }

  /*public void put(Integer index, String instr) {
    instructions.put(index, InstructionFactory.getInstruction(instr));
  }*/
}
