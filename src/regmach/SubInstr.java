package regmach;

public class SubInstr extends RegisterManipulatingInstruction {

  private int trueLabel;
  private int falseLabel;

  
  public SubInstr(int reg, int falseLabel, int trueLabel) {
    super(reg);
    this.falseLabel = falseLabel;
    this.trueLabel = trueLabel;
  }
  

  /*
   * Accessors
   */
  
  public int getGreaterThanZeroLabel() {
    return falseLabel;
  }
  
  public int getNotGreaterThanZeroLabel() {
    return trueLabel;
  }
  
  
  @Override
  public int execute(Register register) {
    return register.subtractOne() ? falseLabel : trueLabel;
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (!(obj instanceof SubInstr)) {
      return false;
    }
    
    SubInstr other = (SubInstr)obj;    
    return this.registerIndex == other.registerIndex &&
        this.falseLabel == other.falseLabel &&
        this.trueLabel == other.trueLabel;
  }
  
  @Override
  public int hashCode() {
    int hash = 17;
    
    hash = 31 * hash + registerIndex;
    hash = 31 * hash + falseLabel;
    hash = 31 * hash + trueLabel;
    
    return hash;
  }
  
  @Override
  public String toString() {
    return String.format("%d -> %d, %d", 
        registerIndex, 
        falseLabel, 
        trueLabel);
  }
}
