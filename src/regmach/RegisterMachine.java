package regmach;

public class RegisterMachine {
  
  private RegisterSet registers;
  
  
  public RegisterMachine(RegisterSet registers) {
    this.registers = registers;
  }
  

  public RegisterSet runProgram(Program program) {
    int currentInstructionLabel = registers.getRegister(0).getValue();

    while (true) {
      // Check if HALT instruction, or pointing to non-existent instruction
      if (currentInstructionLabel == -1 || !program.containsInstructionLabel(currentInstructionLabel)) {
        break;
      }

      System.out.println(currentInstructionLabel + ": " + registers.toString());
      
      Instruction instr = program.getInstruction(currentInstructionLabel);
      
      /*
       * Check type to ensure getRegisterIndex works. If below fails: break,
       * as we have either a HALT or invalid instruction.
       */
      if (instr instanceof RegisterManipulatingInstruction) {
        RegisterManipulatingInstruction instruction = (RegisterManipulatingInstruction)instr;
        
        Register register = registers.getRegister(instruction.getRegisterIndex());
        currentInstructionLabel = instruction.execute(register);
      } else {
        break;
      }
    }
    
    System.out.println("Halted.");
    
    return registers;
  }
}
