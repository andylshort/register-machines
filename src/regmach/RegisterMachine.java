package regmach;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMachine {
	
	/*
	 * Regexes
	 * n: x -> y      ---->  (\d:\s*\d\s*->\s*\d)[^,]
	 * n: x -> y, z   ---->  (\d:\s*\d\s*->\s*\d,\s*\d)
	 * HALT           ---->  HALT
	 */
	private static final String addPattern = "(\\d\\s*->\\s*\\d)";
	private static final String subPattern = "(\\d\\s*->\\s*\\d,\\s*\\d)";
	private static final String haltPattern = "HALT";
	
	private static Pattern addPat  = Pattern.compile(addPattern);
	private static Pattern subPat  = Pattern.compile(subPattern);
	private static Pattern haltPat = Pattern.compile(haltPattern);
	
	private Map<Integer, Register> regs;
	private Map<Integer, Instruction> instrs;
	
	public RegisterMachine()
	{
		// Initialise registers and instructions structures
		this.regs   = new HashMap<Integer, Register>();
		this.instrs = new HashMap<Integer, Instruction>();
	}
	
	public void addRegister(int idx, Register reg)
	{
		regs.put(idx, reg);
	}
	
	public void addInstruction(int idx, Instruction instr)
	{
		instrs.put(idx, instr);
	}
	
	public Instruction retrieveInstruction(int idx)
	{
		return instrs.get(idx);
	}
	
	public Set<Integer> instrKeySet()
	{
		return instrs.keySet();
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		RegisterMachine rm = new RegisterMachine();
		
		Map<Integer, String> lines = new HashMap<Integer, String>();
		
		if (args.length != 1)
		{
			System.out.println("NEED A FILE TO WORK");
			System.exit(0);
		}

		BufferedReader fbr = null;
		try
		{
			fbr = new BufferedReader(new FileReader(args[0]));
			
			String line = "";
			
			while ((line = fbr.readLine()) != null)
			{
				Integer index = Integer.parseInt(line.split(":\\s*")[0]);
				String instr = line.split(":\\s*")[1];
				
				lines.put(index, instr);
			}
		}
		catch (FileNotFoundException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
			
		System.out.println("Enter initial configuration:");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String initialConfig = "";
		
		try
		{
			initialConfig = br.readLine();			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		String[] regValues = initialConfig.split(" ");
		
		for (int i = 0; i < regValues.length; i++)
		{
			int value = Integer.parseInt(regValues[i]);
			rm.addRegister(i, new Register(value));
		}
		
		System.out.println();
		
		for (Map.Entry<Integer, String> entry : lines.entrySet())
		{
			int label = entry.getKey();
			String instr = entry.getValue();
			
			Instruction instruction = rm.convertToInstruction(instr);
			
			rm.addInstruction(label, instruction);
		}
		
		/* Run program */
		int currentInstr = 0;
		
		while (true)
		{
			Instruction instr = rm.retrieveInstruction(currentInstr);
			int returnValue = instr.execute();
			
			if (returnValue == -1 || returnValue > Collections.max(rm.instrKeySet()))
			{
				/* We've hit a halting configuration: either halt, not an instr, or
				 * pointing to erroneous halting instr - so we halt/exit */
				System.exit(0);
			}
			
			currentInstr = returnValue;
			rm.printConfig(currentInstr);
		}
	}
	
	public Instruction convertToInstruction(String s)
	{
		Matcher addMatch  = addPat.matcher(s);
		Matcher subMatch  = subPat.matcher(s);
		Matcher haltMatch = haltPat.matcher(s);
		
		if (addMatch.find() && !s.contains(","))
		{
			String[] parts = s.split("\\s*->\\s*");
			
			int reg = Integer.parseInt(parts[0]);
			Register register = regs.get(reg);
			
			int label = Integer.parseInt(parts[1]);
			
			return new AddInstr(register, label);
		}
		else if (subMatch.find() && s.contains(","))
		{
			String[] parts = s.split("\\s*->\\s*");
			String[] labels = parts[1].split(",\\s*");
			
			int reg = Integer.parseInt(parts[0]);
			Register register = regs.get(reg);
			
			/*
			 * False = able to subtract one
			 * True = reg value is zero
			 */
			int falseLabel  = Integer.parseInt(labels[0]);
			int trueLabel = Integer.parseInt(labels[1]);
			
			return new SubInstr(register, falseLabel, trueLabel);
		}
		else if (haltMatch.find())
		{
			return new HaltInstr();
		}
		else
		{
			return new NullInstr();
		}
	}
	
	public void printConfig(int idx)
	{
		System.out.print(idx + ": ");
		printRegs(regs);
	}
	
	private static void printRegs(Map<Integer, Register> regs)
	{
		for (Map.Entry<Integer, Register> entry : regs.entrySet())
		{
			Register reg = entry.getValue();
			System.out.print(reg.getValue() + " ");
		}
		System.out.println();
	}

	
	public static List<Integer> decompose(int num)
	{
		String binary = Integer.toBinaryString(num);
		
		/*
		 * Match a 1 and any number of trailing zeroes
		 */
		Pattern zeroPat = Pattern.compile("10*");
		Matcher zeroMatcher = zeroPat.matcher(binary);
		
		List<Integer> matches = new ArrayList<Integer>();
		
		while (zeroMatcher.find())
		{
			// Lop off the 1 at the start and you get the number of zeroes
			matches.add(zeroMatcher.group().length() - 1);
		}
		
		Collections.reverse(matches);
		return matches;
	}
	
	public static int compose(List<Integer> nums)
	{
		if (nums.size() == 0)
		{
			return 0;
		}
		else
		{
			Integer head = nums.get(0);
			nums.remove(0);
			return (int)(Math.pow(2, head)) * ((2 * compose(nums)) + 1);
		}		
	}
	
	public static void valueToProgram(int value)
	{
		List<Integer> ints = decompose(value);
		
		for (int i = 0; i < ints.size(); i++)
		{
			System.out.println(i + ": " + valueToInstruction(ints.get(i)));
		}
	}
	
	public static String valueToInstruction(int value)
	{
		assert (value >= 0) : "Value must be a natural number (>=0).";
		
		if (value == 0)
		{
			return "HALT";
		}
		else
		{
			List<Integer> valueInts = decompose(value);
			int y = valueInts.get(0);
			int z = ((value / (int)(Math.pow(2, y))) - 1) / 2;
			
			int i = y / 2;
			
			if (y % 2 == 0)
			{				
				return i + " -> " + z;
			}
			else
			{
				List<Integer> nextValueInts = decompose(z + 1);
				int j = nextValueInts.get(0);
				int k = ((z + 1 / (int)(Math.pow(2, j))) - 1) / 2;
				
				return i + " -> " + j + ", " + k;
			}
		}
	}
}
