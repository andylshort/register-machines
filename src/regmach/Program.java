package regmach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {

  private Map<Integer, Instruction> instructions;

  
  public Program() {
    this.instructions = new HashMap<Integer, Instruction>();
  }
  
  public Program(HashMap<Integer, Instruction> instructions) {
    this.instructions = instructions;
  }
  
  
  public int length() {
    return instructions.size();
  }

  public Instruction getInstruction(int index) {
    return instructions.get(index);
  }

  public void put(Integer index, String instr) {
    instructions.put(index, InstructionFactory.getInstruction(instr));
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < instructions.size(); i++) {
      sb.append(i);
      sb.append(": ");
      sb.append(instructions.get(i).toString());
      if (i < instructions.size() - 1) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }
  
  /*/**
   * Codes a program into its natural number value
   * @param nums
   * @return
   *
  public int code(List<Integer> nums) {
    if (nums.size() == 0) {
      return 0;
    } else {
      Integer head = nums.get(0);
      nums.remove(0);
      return (int) (Math.pow(2, head)) * ((2 * code(nums)) + 1);
    }
  }*/
  
  /**
   * Takes an input value and decodes it its representative program.
   * @param value
   * @return
   */
  public static Program fromValue(int value) {
    List<Integer> ints = decompose(value);
    
    HashMap<Integer, Instruction> instructions = new HashMap<Integer, Instruction>();

    for (int i = 0; i < ints.size(); i++) {
      instructions.put(i,
          InstructionFactory.getInstruction(
              valueToInstruction(ints.get(i))
          )
      );
    }
    
    return new Program(instructions);
  }

  private static String valueToInstruction(int value) {
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
  
  private static List<Integer> decompose(int num) {
    String binary = Integer.toBinaryString(num);

    // Match a 1 and any number of trailing zeroes
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
}
