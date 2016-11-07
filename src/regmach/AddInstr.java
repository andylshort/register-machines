package regmach;

public class AddInstr implements Instruction {

  private int reg;
  private int label;

  public AddInstr(int reg, int label) {
    this.reg = reg;
    this.label = label;
  }
  
  public int getRegisterIndex() {
    return reg;
  }
  
  public int getNextLabel() {
    return label;
  }

  @Override
  public int execute(RegisterSet registers) {
    Register register = registers.getRegister(reg);
    register.addOne();
    return label;
  }
  
  @Override
  public String toString() {
    return reg + " -> " + label;
  }
}
