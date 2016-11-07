package regmach;

public interface Instruction {  
  int execute(RegisterSet registers);
}
