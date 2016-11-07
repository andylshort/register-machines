package regmach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainProgram {

  public static void main(String[] args) {

    RegisterSet registers = new RegisterSet();
    
    // Previous Error: Need to load registers before program/instructions referenced.
    System.out.println("Enter initial configuration:");

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String initialConfig = "";

    try {
      initialConfig = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }

    String[] regValues = initialConfig.split(" ");

    for (int i = 0; i < regValues.length; i++) {
      int value = Integer.parseInt(regValues[i]);
      registers.addRegister(value);
    }

    System.out.println();
    

    RegisterMachine rm = new RegisterMachine(registers);
    

    if (args.length != 1) {
      System.out.println("NEED A FILE TO WORK");
      System.exit(0);
    }

    BufferedReader fbr = null;
    try {
      fbr = new BufferedReader(new FileReader(args[0]));

      String line = "";

      while ((line = fbr.readLine()) != null) {
        Integer index = Integer.parseInt(line.split(":\\s*")[0]);
        String instr = line.split(":\\s*")[1];

        rm.addInstruction(index, instr);
      }
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    
    rm.runProgram(registers);
    
    System.out.println("Halted.");
  }
  
}
