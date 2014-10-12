package wallet;
import javax.validation.constraints.NotNull;

public class WebLogin
{
	int login_id;
	@NotNull
	String url;
	@NotNull
	String login;
	@NotNull
	String password;
	public WebLogin(int login_id,String url,String login,String password)
	{
		this.login_id=login_id;
		this.url=url;
		this.login=login;
		this.password=password;
	}
	public int getLogin_id()
	{
		return login_id;
	}
	public String getUrl()
	{
		return url;
	}
	public String getLogin()
	{
		return login;
	}
	public String getPassword()
	{
		return password;
	}
}