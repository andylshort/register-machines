package regmach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterSet {

  private List<Register> registers;
  
  
  /*
   * Constructors
   */
  public RegisterSet() {
    registers = new ArrayList<Register>();
  }
  
  public RegisterSet(RegisterSet regSet) {
    this();
    for (Register r : regSet.getRegisters()) {
      registers.add(new Register(r.getValue()));
    }
  }
  
  
  public void addRegister(int initialValue) {
    registers.add(new Register(initialValue));
  }
  
  public Register getRegister(int index) {
    return registers.get(index);
  }
  
  public List<Register> getRegisters() {
    return new ArrayList<Register>(registers);
  }
  
  public int registerCount() {
    return registers.size();
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (!RegisterSet.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    
    RegisterSet rs = (RegisterSet)obj;
    
    if (this == rs) {
      return true;
    }
    
    if (this.registerCount() != rs.registerCount()) {
      return false;
    }
    
    int i = 0;
    for (Register r : this.registers) {
      if (!r.equals(rs.getRegister(i))) {
        return false;
      }
      i++;
    }
    
    return true;
  }
  
  @Override
  public int hashCode() {
    int hash = 17;
    hash = 31 * hash + registers.hashCode();
    return hash;
  }
  
  @Override
  public String toString() {
    return Arrays.toString(registers.toArray());
  }
  
}
