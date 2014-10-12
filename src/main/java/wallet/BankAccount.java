package wallet;
import javax.validation.constraints.NotNull;

public class BankAccount
{
	int ba_id;
	String account_name;
	@NotNull
	String routing_number;
	@NotNull
	String account_number;

	public BankAccount(int ba_id,String account_name,String routing_number,String account_number)
	{
		this.ba_id=ba_id;
		this.account_name=account_name;
		this.routing_number=routing_number;
		this.account_number=account_number;
	}
	public int getBa_id()
	{
		return ba_id;
	}
	public String getAccount_name()
	{
		return account_name;
	}
	public String getRouting_number()
	{
		return routing_number;
	}
	public String getAccount_number()
	{
		return account_number;
	}
}