package regmach;

import java.util.ArrayList;
import java.util.Arrays;
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
  
  public Map<Integer, Instruction> getInstructions() {
    HashMap<Integer, Instruction> instrsCopy = 
        new HashMap<Integer, Instruction>(instructions);
    return Collections.unmodifiableMap(instrsCopy);
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
  
  @Override
  public boolean equals(Object obj) {
    
    if (obj == null) return false;
    
    if (!Program.class.isAssignableFrom(obj.getClass())) return false;
    
    final Program p = (Program) obj;
    
    if (p.instructions == null) return false;
    
    return p.getInstructions().equals(this.instructions);
  }
  
  @Override
  public int hashCode() {
    int hash = 17;
    
    hash = 31 * hash + instructions.hashCode();
    
    return hash;
  }
  
  
  
  public int code() {
    return codeSub(codeInstructions());
    
  }
  
  private int codeSub(List<Integer> codes) {
    if (codes.size() == 0) {
      return 0;
    }
    else {
      List<Integer> codesCopy = new ArrayList<Integer>(codes);
      int code = codesCopy.remove(0);
      
      return doubleBracket(code, codeSub(codesCopy));
    }
  }
  
  
  
  private List<Integer> codeInstructions() {
    List<Integer> codes = new ArrayList<Integer>();
    
    // Need to traverse items in order
    for (int i = 0; i < instructions.size(); i++) {
      Instruction instr = instructions.get(i);
      
      if (instr instanceof AddInstr) {
        AddInstr ai = (AddInstr)instr;
        
        int ri = ai.getRegisterIndex();
        int lj = ai.getNextLabel();
        
        codes.add(doubleBracket(2 * ri, lj));
      }
      else if (instr instanceof SubInstr) {
        SubInstr si = (SubInstr)instr;
        
        int ri = si.getRegisterIndex();
        int lj = si.getGreaterThanZeroLabel();
        int lk = si.getNotGreaterThanZeroLabel();
        
        codes.add(doubleBracket((2 * ri) + 1, singleBracket(lj, lk)));
      }
      else if (instr instanceof HaltInstr) {
        codes.add(0);
      }
    }
    
    return codes;
  }
  
  
  private int doubleBracket(int x, int y) {
    return ((int)(Math.pow(2, x))) * ((2 * y) + 1);
  }
  
  private int singleBracket(int x, int y) {
    return ((int)(Math.pow(2, x))) * ((2 * y) + 1) - 1;
  }
  
  
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
