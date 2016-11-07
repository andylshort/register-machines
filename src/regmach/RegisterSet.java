package regmach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterSet {

  private List<Register> registers = new ArrayList<Register>();
  
  
  public void addRegister(int initialValue) {
    registers.add(new Register(initialValue));
  }
  
  public Register getRegister(int index) {
    return registers.get(index);
  }
  
  @Override
  public String toString() {
    return Arrays.toString(registers.toArray());
  }
  
}
