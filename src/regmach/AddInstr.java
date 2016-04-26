package regmach;

public class AddInstr implements Instruction {

  private Register reg;
  private int label;

  public AddInstr(Register reg, int label) {
    this.reg = reg;
    this.label = label;
  }

  @Override
  public int execute() {
    reg.addOne();
    return label;
  }
}
