package regmach;

public class NullInstr implements Instruction {

  @Override
  public int execute() {
    System.out.println("Erroneous instruction entered into program tried to execute. Exiting...");
    return -1;
  }
}
