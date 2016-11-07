package regmach;

public class SubInstr implements Instruction {

  private int reg;
  private int trueLabel;
  private int falseLabel;

  public SubInstr(int reg, int falseLabel, int trueLabel) {
    this.reg = reg;
    this.falseLabel = falseLabel;
    this.trueLabel = trueLabel;
  }

  @Override
  public int execute(RegisterSet registers) {
    Register register = registers.getRegister(reg);
    
    boolean nonZero = register.subtractOne();
    return (nonZero ? falseLabel : trueLabel);
  }
  
  @Override
  public String toString() {
    return reg + " -> " + falseLabel + ", " + trueLabel;
  }
}
