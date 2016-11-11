package regmach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainProgram {

  public static void main(String[] args) {
    RegisterSet registers = loadInitialRegisterSet();
    Program program = loadProgramFromFile(args[0]);
    
    // Execute the program with the initial configuration    
    RegisterMachine rm = new RegisterMachine(registers);
    rm.runProgram(program);
  }
  
  @SuppressWarnings("resource")
  private static RegisterSet loadInitialRegisterSet() {
    RegisterSet initialConfiguration = new RegisterSet();    
    
    // Load the registers first with an initial configuration
    System.out.println("Enter initial, space-separated configuration:");

    Scanner input = new Scanner(System.in);
    String initialConfig = input.nextLine();

    String[] regValues = initialConfig.split(" ");

    for (int i = 0; i < regValues.length; i++) {
      int value = Integer.parseInt(regValues[i]);
      initialConfiguration.addRegister(value);
    }
    
    if (initialConfiguration.registerCount() < 1) {
      throw new RuntimeException("At least 1 register must be specified.");
    }
    return initialConfiguration;    
  }
  
  @SuppressWarnings("resource")
  private static Program loadProgramFromFile(String filePath) {
    Program program = new Program();
    
    // Load program in from file
    if (filePath == null || filePath.isEmpty()) {
      System.err.println("No input file.");
      System.exit(0);
    }
    
    // Can do extra checks here: file exists etc...
    
    try {
      Scanner fileInput = new Scanner(new File(filePath));
      
      while (fileInput.hasNextLine()) {
        String currentLine = fileInput.nextLine();
        
        Integer index = Integer.parseInt(currentLine.split(":\\s*")[0]);
        String instr = currentLine.split(":\\s*")[1];
        
        program.putInstruction(index, instr);
      }      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    return program;
  }
  
}
