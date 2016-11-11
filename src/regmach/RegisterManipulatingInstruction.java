package regmach;

public abstract class RegisterManipulatingInstruction implements Instruction {

  protected int registerIndex;
  
  
  public RegisterManipulatingInstruction(int registerIndex) {
    this.registerIndex = registerIndex;
  }
  

  public int getRegisterIndex() {
    return registerIndex;
  }
  
  @Override
  public abstract int execute(Register register);

}
