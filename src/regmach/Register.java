package regmach;

public class Register {
	
	private int value;
	
	public Register(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void addOne()
	{
		value += 1;
	}
	
	/***
	 * Subtracts one from the register's value.
	 * @return True if non-zero value and value subtracted 1. False if register value is zero.
	 */
	public boolean subtractOne()
	{
		if (isZero())
		{
			return false;
		}
		else
		{
			value -= 1;
			return true;
		}
	}
	
	public boolean isZero()
	{
		return value == 0;
	}
}
