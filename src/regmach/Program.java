package regmach;

import java.util.HashMap;
import java.util.Map;

public class Program {

  private Map<Integer, Instruction> instructions;

  public Program() {
    this.instructions = new HashMap<Integer, Instruction>();
  }

  public Instruction getInstruction(int index) {
    return instructions.get(index);
  }
}
