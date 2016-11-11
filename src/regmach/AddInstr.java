package regmach;

public class AddInstr extends RegisterManipulatingInstruction {

  private int label;

  
  public AddInstr(int reg, int label) {
    super(reg);
    this.label = label;
  }
  
  
  /*
   * Accessors
   */
  public int getNextLabel() {
    return label;
  }

  
  @Override
  public int execute(Register register) {
    register.addOne();
    return label;
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (!(obj instanceof AddInstr)) {
      return false;
    }
    
    AddInstr other = (AddInstr)obj;    
    return this.registerIndex == other.registerIndex &&
        this.label == other.label;
  }
  
  @Override
  public int hashCode() {
    int hash = 17;
    
    hash = 31 * hash + registerIndex;
    hash = 31 * hash + label;
    
    return hash;
  }
  
  @Override
  public String toString() {
    return registerIndex + " -> " + label;
  }
}
