package regmach;

public class HaltInstr implements Instruction {

  @Override
  public int execute(Register register) {
    return -1;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (!(obj instanceof HaltInstr)) {
      return false;
    }
    
    return this == (HaltInstr)obj;
  }
  
  @Override
  public int hashCode() {
    int hash = 17;
    hash = 31 * hash + "HALT".hashCode();
    
    return hash;
  }
  
  @Override
  public String toString() {
    return "HALT";
  }
}
