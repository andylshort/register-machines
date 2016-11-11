package regmach;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InstructionFactory {
  
  /*
   * TODO: Is this the concern of parsing the instruction in the Factory, or
   * in the instructions themselves? Having such similar patterns (Pattern,
   * Matcher, find, process) seems like there should just be a regex
   * lookup in a dictionary to classes, and to return the parsed instruction.
   */

  /*
   * Regexes
   * n: x -> y ----> ^\d:\d->\s*\d$
   * n: x -> y, z ----> ^\d:\d->\d,\d$
   * HALT ----> HALT
   */
  private static final String addPattern = "(^\\d->\\d$)";
  private static final String subPattern = "(^\\d->\\d,\\d$)";
  private static final String haltPattern = "^HALT$";

  private static Pattern addPat = Pattern.compile(addPattern);
  private static Pattern subPat = Pattern.compile(subPattern);
  private static Pattern haltPat = Pattern.compile(haltPattern);
  
  
  public static Instruction getInstruction(String instruction) {    
    instruction = instruction.trim().replaceAll("\\s", "");
    
    // Identify which instruction type it is and process accordingly
    Matcher addMatcher  = addPat.matcher(instruction);
    Matcher subMatcher  = subPat.matcher(instruction);
    Matcher haltMatcher = haltPat.matcher(instruction);
    
    if (addMatcher.find()) {
      String[] parts = instruction.split("->");

      int reg = Integer.parseInt(parts[0]);
      int label = Integer.parseInt(parts[1]);

      return new AddInstr(reg, label);
    }    
    else if (subMatcher.find()) {
      String[] parts = instruction.split("->");
      String[] labels = parts[1].split(",");

      int reg = Integer.parseInt(parts[0]);

      int falseLabel = Integer.parseInt(labels[0]);
      int trueLabel = Integer.parseInt(labels[1]);

      return new SubInstr(reg, falseLabel, trueLabel);
    }    
    else if (haltMatcher.find()) {
      return new HaltInstr();
    }    
    else {
      return new NullInstr();
    }    
  }
}
