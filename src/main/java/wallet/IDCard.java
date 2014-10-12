package wallet;
import java.util.Date;
import javax.validation.constraints.NotNull;

public class IDCard{
	private int card_id; //system_generated
	@NotNull
	private String card_name;
	@NotNull
	private String card_number;
	private String expiration_date; //Date
	

	IDCard(int card_id, String card_name, String card_number, String expiration_date)
	{
		this.card_id = card_id;
		this.card_name = card_name;
		this.card_number = card_number;
		this.expiration_date = expiration_date;
	}

	public int getCard_id()
	{
        	return card_id;
	}
	public String getCard_name()
	{
        	return card_name;
	}
	public String getCard_number()
	{
        	return card_number;
	}
	public String getExpiration_date()
	{
        	return expiration_date;
	}

}
