package regmach;

public class NullInstr implements Instruction {

  @Override
  public int execute(RegisterSet registers) {
    System.out.println("Erroneous instruction entered into program tried to execute. Exiting...");
    return -1;
  }
}
