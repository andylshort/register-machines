package regmach;

public class Register {

  private int value;
  

  public Register(int value) {
    this.value = value;
  }
  

  public int getValue() {
    return value;
  }

  public void addOne() {
    value += 1;
  }
  
  public boolean subtractOne() {
    if (isZero()) {
      return false;
    } else {
      value -= 1;
      return true;
    }
  }

  public boolean isZero() {
    return value == 0;
  }
  
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (!Register.class.isAssignableFrom(obj.getClass())) {
      return false;
    }
    
    Register r = (Register)obj;
    if (this == r) {
      return true;
    }
    
    return this.value == r.value;
  }
  
  @Override
  public int hashCode() {
    int hash = 17;
    hash = 31 * hash + this.value;
    return hash;
  }
  
  @Override
  public String toString() {
    return Integer.toString(value);
  }
}
