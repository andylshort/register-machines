package regmach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMachine {

  private RegisterSet registers;
  private Map<Integer, Instruction> instrs;
  
  public RegisterMachine(RegisterSet registers) {
    // Initialise registers and instructions structures
    this.registers = registers;
    this.instrs = new HashMap<Integer, Instruction>();
  }
  

  public void addInstruction(int idx, String instr) {
    instrs.put(idx, InstructionFactory.getInstruction(this, instr));
  }
  
  public Register getRegister(int index) {
    return registers.getRegister(index);
  }
  
  
  public void runProgram(RegisterSet registers) {
    int currentInstr = 0;

    while (true) {
      Instruction instr = instrs.get(currentInstr);
      int returnValue = instr.execute();

      if (returnValue == -1 || returnValue > instrs.size()) {
        // We've hit a halting configuration: either halt, not an instr, or
        // pointing to erroneous halting instr - so we halt/exit
        break;
      }

      currentInstr = returnValue;
      System.out.println(currentInstr + ": " + registers.toString());
    }
  }
  

  public static List<Integer> decompose(int num) {
    String binary = Integer.toBinaryString(num);

    /*
     * Match a 1 and any number of trailing zeroes
     */
    Pattern zeroPat = Pattern.compile("10*");
    Matcher zeroMatcher = zeroPat.matcher(binary);

    List<Integer> matches = new ArrayList<Integer>();

    while (zeroMatcher.find()) {
      // Lop off the 1 at the start and you get the number of zeroes
      matches.add(zeroMatcher.group().length() - 1);
    }

    Collections.reverse(matches);
    return matches;
  }

  public static int compose(List<Integer> nums) {
    if (nums.size() == 0) {
      return 0;
    } else {
      Integer head = nums.get(0);
      nums.remove(0);
      return (int) (Math.pow(2, head)) * ((2 * compose(nums)) + 1);
    }
  }

  public static void valueToProgram(int value) {
    List<Integer> ints = decompose(value);

    for (int i = 0; i < ints.size(); i++) {
      System.out.println(i + ": " + valueToInstruction(ints.get(i)));
    }
  }

  public static String valueToInstruction(int value) {
    assert (value >= 0) : "Value must be a natural number (>=0).";

    if (value == 0) {
      return "HALT";
    } else {
      List<Integer> valueInts = decompose(value);
      int y = valueInts.get(0);
      int z = ((value / (int) (Math.pow(2, y))) - 1) / 2;

      int i = y / 2;

      if (y % 2 == 0) {
        return i + " -> " + z;
      } else {
        List<Integer> nextValueInts = decompose(z + 1);
        int j = nextValueInts.get(0);
        int k = ((z + 1 / (int) (Math.pow(2, j))) - 1) / 2;

        return i + " -> " + j + ", " + k;
      }
    }
  }
}
