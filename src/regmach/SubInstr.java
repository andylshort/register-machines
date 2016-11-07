package regmach;

public class SubInstr implements Instruction {

  private Register reg;
  private int trueLabel;
  private int falseLabel;

  public SubInstr(Register reg, int falseLabel, int trueLabel) {
    this.reg = reg;
    this.falseLabel = falseLabel;
    this.trueLabel = trueLabel;
  }

  @Override
  public int execute() {
    if (reg == null) {
      System.out.println("OOPS!");
    }
    boolean nonZero = reg.subtractOne();
    return (nonZero ? falseLabel : trueLabel);
  }
}
