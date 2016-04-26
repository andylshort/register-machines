package regmach;

public class HaltInstr implements Instruction {

  @Override
  public int execute() {
    return -1;
  }
}
