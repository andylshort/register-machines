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
  public String toString() {
    return Integer.toString(value);
  }
}
