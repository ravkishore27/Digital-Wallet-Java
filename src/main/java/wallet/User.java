package wallet;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import javax.validation.constraints.NotNull;

public class User{
	private int user_id;
	@NotNull
	private String email;
	@NotNull
	private String password;
	private String name;
	private Date created_at; //Date
	private Date updated_at; //Date
	private ArrayList<IDCard> id_cards = new ArrayList<IDCard>();
	private ArrayList<WebLogin> web_logins = new ArrayList<WebLogin>();
	private ArrayList<BankAccount> bank_accounts = new ArrayList<BankAccount>();
	private final AtomicInteger id_card_counter = new AtomicInteger();
	private final AtomicInteger web_login_counter = new AtomicInteger();
	private final AtomicInteger bank_account_counter = new AtomicInteger();

	User(int user_id, String email, String password, String name, Date created_at, Date updated_at)
	{
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;	
	}

	public Map<String,String> getUser()
	{
		Map<String,String> user_details = new LinkedHashMap<String,String>();
		user_details.put("user_id",String.format("%d",user_id));
    	user_details.put("email",email);
    	user_details.put("password",password);
    	if(!name.equals(""))
    	{
    		user_details.put("Name",name);
    	}
    	user_details.put("created_at",getCreated_at());
    	user_details.put("updated at", getUpdated_at());
    	return user_details;
	}

	public IDCard addId_card(String card_name, String card_number, String expiration_date)
	{
		IDCard id_card = new IDCard(id_card_counter.incrementAndGet(),card_name,card_number,expiration_date);
		id_cards.add(id_card);
		return id_card;
	}
	public void removeCard(Integer card_id)
	{
		Iterator<IDCard> iterator = id_cards.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getCard_id() == card_id)
			{
				iterator.remove();
				break;
			}
		}
	}

	public WebLogin addWeb_login(String url, String login, String password)
	{
		WebLogin web_login = new WebLogin(web_login_counter.incrementAndGet(),url,login,password);
		web_logins.add(web_login);
		return web_login;
	}

	public void removeWebLogin(Integer login_id)
	{
		Iterator<WebLogin> iterator = web_logins.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getLogin_id() == login_id)
			{
				iterator.remove();
				break;
			}
		}	
	}

	public BankAccount addBank_account(String account_name, String routing_number, String account_number)
	{
		BankAccount bank_account = new BankAccount(bank_account_counter.incrementAndGet(),account_name,routing_number,account_number);
		bank_accounts.add(bank_account);
		return bank_account;
	}
	public void removeBankAccount(Integer ba_id)
	{
		Iterator<BankAccount> iterator = bank_accounts.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getBa_id() == ba_id)
			{
				iterator.remove();
				break;
			}
		}	
	}
	

	public int getUser_Id()
	{
        	return user_id;
	}
	public String getEmail()
	{
        	return email;
	}
	public String getPassword()
	{
        	return password;
	}
	public String getName()
	{
        	return name;
	}
	public String getCreated_at()
	{
        	return created_at.toString();
	}
	public String getUpdated_at()
	{
        	return updated_at.toString();
	}

	public ArrayList<IDCard> getId_cards()
	{
		return id_cards;
	}
	public ArrayList<WebLogin> getWeb_logins()
	{
		return web_logins;
	}
	public ArrayList<BankAccount> getBank_accounts()
	{
		return bank_accounts;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setUpdated_at()
	{
		updated_at = new Date();
	}
}