package wallet;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong greeting_counter = new AtomicLong();
    private final AtomicInteger counter = new AtomicInteger();
    private Map<Integer,User> users = new HashMap<Integer,User>();

    //Creating User
    @RequestMapping(value = "/api/v1/users", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String,String> createUser(@RequestParam(value="email", required=true) @Valid String email,
                                         @RequestParam(value="password", required=true) @Valid String password,
                                         @RequestParam(value="name", required=false, defaultValue="") String name){
    	int u_id = counter.incrementAndGet();
    	users.put(u_id,new User(u_id,email,password,name, new Date(),new Date()));
    	return (users.get(u_id)).getUser();
    }

    //View User
    @RequestMapping(value="/api/v1/users/{user_id}", method=RequestMethod.GET)
    public Map<String,String> viewUser(@PathVariable Integer user_id)
    {
    	return  users.get(user_id).getUser();
    }

    //Update User
    @RequestMapping(value="/api/v1/users/{user_id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,String> updateUser(@PathVariable Integer user_id, @RequestParam(value="email", required=true) @Valid String email,
                                                             @RequestParam(value="password", required=true) @Valid String password)
    {
        User user = users.get(user_id);
        user.setEmail(email);
        user.setPassword(password);
        user.setUpdated_at();
        return user.getUser();
    }

    //create ID Card
    @RequestMapping(value="/api/v1/users/{user_id}/idcards", method=RequestMethod.POST)
    public IDCard createIdCard(@PathVariable Integer user_id, @RequestParam(value="card_name", required=true) @Valid String card_name,
                                                                 @RequestParam(value="card_number", required=true) @Valid String card_number,
                                                                            @RequestParam(value="expiration_date", required=false) String expiration_date)
    {
        User user = users.get(user_id);     
        return user.addId_card(card_name,card_number,expiration_date);
    }

    //List all ID Cards
    @RequestMapping(value="/api/v1/users/{user_id}/idcards", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    public ArrayList<IDCard> listAllCards(@PathVariable Integer user_id)
    {
        User user = users.get(user_id);     
        return user.getId_cards();
    }

    //Delete ID Card
    @RequestMapping(value="/api/v1/users/{user_id}/idcards/{card_id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIDCard(@PathVariable Integer user_id, @PathVariable Integer card_id)
    {
        User user = users.get(user_id);
        user.removeCard(card_id);
        
    }

    //Create Web Login
    @RequestMapping(value="/api/v1/users/{user_id}/weblogins", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public WebLogin createWebLogin(@PathVariable Integer user_id, @RequestParam(value="url", required=true) @Valid String url,
                                                                 @RequestParam(value="login", required=true) @Valid String login,
                                                                            @RequestParam(value="password", required=false) String password)
    {
        User user = users.get(user_id);
        return user.addWeb_login(url,login,password);
        
    }

    //List all web site logins
    @RequestMapping(value="/api/v1/users/{user_id}/weblogins", method=RequestMethod.GET)
    public ArrayList<WebLogin> listAllWebLogins(@PathVariable Integer user_id)
    {
        User user = users.get(user_id);     
        return user.getWeb_logins();
    }

    //Delete Web login
    @RequestMapping(value="/api/v1/users/{user_id}/weblogins/{login_id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWebLogin(@PathVariable Integer user_id, @PathVariable Integer login_id)
    {
        User user = users.get(user_id);
        user.removeWebLogin(login_id);
        
    }

    //Create Bank Account
    @RequestMapping(value="/api/v1/users/{user_id}/bankaccounts", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccount createBankAccount(@PathVariable Integer user_id, @RequestParam(value="account_name", required=false) String account_name,
                                                                 @RequestParam(value="routing_number", required=true) @Valid String routing_number,
                                                                            @RequestParam(value="account_number", required=true) @Valid String account_number)
    {
        User user = users.get(user_id);
        return user.addBank_account(account_name,routing_number,account_number);
        
    }


    //List all Bank Accounts
    @RequestMapping(value="/api/v1/users/{user_id}/bankaccounts", method=RequestMethod.GET)
    public ArrayList<BankAccount> listAllBankAccounts(@PathVariable Integer user_id)
    {
        User user = users.get(user_id);     
        return user.getBank_accounts();
    }


    //Delete Bank Account
    @RequestMapping(value="/api/v1/users/{user_id}/bankaccounts/{ba_id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBankAccount(@PathVariable Integer user_id, @PathVariable Integer ba_id)
    {
        User user = users.get(user_id);
        user.removeBankAccount(ba_id);
        
    }
}