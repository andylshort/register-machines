package regmach;

public class SubInstr implements Instruction {
	
	private Register reg;
	private int trueLabel;
	private int falseLabel;
	
	public SubInstr(Register reg, int falseLabel, int trueLabel)
	{
		this.reg        = reg;
		this.falseLabel = falseLabel;
		this.trueLabel  = trueLabel;		
	}
	
	public int execute()
	{
		boolean nonZero = reg.subtractOne();
		return (nonZero ? falseLabel : trueLabel);
	}	
}
