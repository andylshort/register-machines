package regmach;

public class HaltInstr implements Instruction {

  @Override
  public int execute(RegisterSet registers) {
    return -1;
  }
  
  @Override
  public String toString() {
    return "HALT";
  }
}
