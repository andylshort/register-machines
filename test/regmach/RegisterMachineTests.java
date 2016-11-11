package regmach;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RegisterMachineTests {

  private RegisterSet initialRegisters;
  private RegisterSet workingRegisters;
  private RegisterMachine rm;
  
  @Before
  public void setUp() throws Exception {    
    workingRegisters = new RegisterSet();
    workingRegisters.addRegister(0);
    workingRegisters.addRegister(2);
    workingRegisters.addRegister(7);
    workingRegisters.addRegister(0);
    
    initialRegisters = new RegisterSet(workingRegisters);
    
    rm = new RegisterMachine(workingRegisters);
  }
  
  
  @Test
  public void haltingDoesntAlterRegisters() {
    Program haltingProgram = new Program();
    haltingProgram.putInstruction(0, "HALT");
    
    RegisterSet results = rm.runProgram(haltingProgram);
    assertEquals(initialRegisters, results);
  }
  
  @Test
  public void registerAlteringInstructionsDoChangeRegisterValues() {
    Program program = new Program();
    program.putInstruction(0, "1 -> 1");
    program.putInstruction(1, "HALT");
    
    RegisterSet results = rm.runProgram(program);
    assertNotEquals(initialRegisters, results);
  }
  
  @Test
  public void machineExecutesAdditionProgramCorrectly() {
    Program addingProgram = new Program();
    addingProgram.putInstruction(0, "1 -> 1, 2");
    addingProgram.putInstruction(1, "0 -> 0");
    addingProgram.putInstruction(2, "2 -> 3, 4");
    addingProgram.putInstruction(3, "0 -> 2");
    addingProgram.putInstruction(4, "HALT");
    
    RegisterSet expectedFinalResults = new RegisterSet();
    expectedFinalResults.addRegister(9);
    expectedFinalResults.addRegister(0);
    expectedFinalResults.addRegister(0);
    expectedFinalResults.addRegister(0);
    
    RegisterSet actualFinalResults = rm.runProgram(addingProgram);
    assertEquals(expectedFinalResults, actualFinalResults);
  }
  
  @Test
  public void machineExecutesMultiplicationProgramCorrectly() {
    Program multiplicationProgram = new Program();
    multiplicationProgram.putInstruction(0, "1 -> 1, 6");
    multiplicationProgram.putInstruction(1, "2 -> 2, 4");
    multiplicationProgram.putInstruction(2, "0 -> 3");
    multiplicationProgram.putInstruction(3, "3 -> 1");
    multiplicationProgram.putInstruction(4, "3 -> 5, 0");
    multiplicationProgram.putInstruction(5, "2 -> 4");
    multiplicationProgram.putInstruction(6, "HALT");
    
    RegisterSet expectedFinalResults = new RegisterSet();
    expectedFinalResults.addRegister(14);
    expectedFinalResults.addRegister(0);
    expectedFinalResults.addRegister(7);
    expectedFinalResults.addRegister(0);
    
    RegisterSet actualFinalResults = rm.runProgram(multiplicationProgram);
    assertEquals(expectedFinalResults, actualFinalResults);
  }
}
