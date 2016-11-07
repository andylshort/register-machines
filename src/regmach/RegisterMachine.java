package regmach;

public class RegisterMachine {  
  
  public void runProgram(RegisterSet registers, Program program) {
    int currentInstr = 0;

    while (true) {
      Instruction instr = program.getInstruction(currentInstr);
      int returnValue = instr.execute(registers);

      if (returnValue == -1 || returnValue > program.length()) {
        // We've hit a halting configuration: either halt, not an instr, or
        // pointing to erroneous halting instr - so we halt/exit
        break;
      }

      currentInstr = returnValue;
      System.out.println(currentInstr + ": " + registers.toString());
    }
  }
}
