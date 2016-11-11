package regmach;

public class NullInstr implements Instruction {

  @Override
  public int execute(Register register) {
    System.out.println("Erroneous instruction entered into program tried to execute. Exiting...");
    return -1;
  }
}
