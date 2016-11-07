package regmach;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainProgram {

  @SuppressWarnings("resource")
  public static void main(String[] args) {

    RegisterSet registers = new RegisterSet();
    Program program = new Program();
    
    // Previous Error: Need to load registers before program/instructions referenced.
    System.out.println("Enter initial configuration:");
    
    Scanner input = new Scanner(System.in);
    String initialConfig = input.nextLine();

    String[] regValues = initialConfig.split(" ");

    for (int i = 0; i < regValues.length; i++) {
      int value = Integer.parseInt(regValues[i]);
      System.out.println(value);
      registers.addRegister(value);
    }

    System.out.println();
    

    RegisterMachine rm = new RegisterMachine();
    

    if (args.length != 1) {
      System.out.println("NEED A FILE TO WORK");
      System.exit(0);
    }
    
    try {
      Scanner fileInput = new Scanner(new File(args[0]));
      
      while (fileInput.hasNextLine()) {
        String currentLine = fileInput.nextLine();
        
        Integer index = Integer.parseInt(currentLine.split(":\\s*")[0]);
        String instr = currentLine.split(":\\s*")[1];
        
        program.put(index, instr);
      }
      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    rm.runProgram(registers, program);
    
    System.out.println("Halted.");
  }
  
}
