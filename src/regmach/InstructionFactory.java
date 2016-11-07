package regmach;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionFactory {

  /*
   * Regexes n: x -> y ----> (\d:\s*\d\s*->\s*\d)[^,] n: x -> y, z ---->
   * (\d:\s*\d\s*->\s*\d,\s*\d) HALT ----> HALT
   */
  private static final String addPattern = "(\\d\\s*->\\s*\\d)";
  private static final String subPattern = "(\\d\\s*->\\s*\\d,\\s*\\d)";
  private static final String haltPattern = "HALT";

  private static Pattern addPat = Pattern.compile(addPattern);
  private static Pattern subPat = Pattern.compile(subPattern);
  private static Pattern haltPat = Pattern.compile(haltPattern);
  
  
  public static Instruction getInstruction(RegisterMachine rm, String instruction) {
    
    Matcher addMatcher  = addPat.matcher(instruction);
    Matcher subMatcher  = subPat.matcher(instruction);
    Matcher haltMatcher = haltPat.matcher(instruction);
    
    
    if (addMatcher.find() && !instruction.contains(",")) {
      String[] parts = instruction.split("\\s*->\\s*");

      int reg = Integer.parseInt(parts[0]);
      Register register = rm.getRegister(reg);

      int label = Integer.parseInt(parts[1]);

      return new AddInstr(register, label);
    }
    else if (subMatcher.find() && instruction.contains(",")) {
      String[] parts = instruction.split("\\s*->\\s*");
      String[] labels = parts[1].split(",\\s*");

      int reg = Integer.parseInt(parts[0]);
      Register register = rm.getRegister(reg);

      // False = able to subtract one True = reg value is zero
      int falseLabel = Integer.parseInt(labels[0]);
      int trueLabel = Integer.parseInt(labels[1]);

      return new SubInstr(register, falseLabel, trueLabel);
    }
    else if (haltMatcher.find()) {
      return new HaltInstr();
    }
    else {
      return new NullInstr();
    }
    
  }
  
  /*
   * Parsing methods
   */
  
//  private AddInstr asAddInstr(String s) {
//    String[] parts = s.split("\\s*->\\s*");
//
//    int reg = Integer.parseInt(parts[0]);
//    Register register = regs.get(reg);
//
//    int label = Integer.parseInt(parts[1]);
//
//    return new AddInstr(register, label);
//  }
//  
//  private SubInstr asSubInstr(String s) {
//    String[] parts = s.split("\\s*->\\s*");
//    String[] labels = parts[1].split(",\\s*");
//
//    int reg = Integer.parseInt(parts[0]);
//    Register register = regs.get(reg);
//
//    /*
//     * False = able to subtract one True = reg value is zero
//     */
//    int falseLabel = Integer.parseInt(labels[0]);
//    int trueLabel = Integer.parseInt(labels[1]);
//
//    return new SubInstr(register, falseLabel, trueLabel);
//  }
//
//  private HaltInstr asHaltInstr(String s) {
//    return new HaltInstr();
//  }
}
