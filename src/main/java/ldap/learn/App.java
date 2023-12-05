package ldap.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		App app = new App();
		app.newConnection();
		System.out.println(authUser("waheed","asd"));
	}

	DirContext connection;
//
//	/* create connection during object creation */
	public void newConnection() {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
		env.put(Context.SECURITY_PRINCIPAL, "uid=admin, ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		try {
			connection = new InitialDirContext(env);
			System.out.println("Hello World!" + connection);
		} catch (AuthenticationException ex) {
			System.out.println(ex.getMessage());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//
//	/* use this to authenticate any existing user */
	public static boolean authUser(String username, String password)
	{
		try {
			System.out.println("username =" + username);
			System.out.println("password =" + password);
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://localhost:10389");
			env.put(Context.SECURITY_PRINCIPAL, "cn="+username+",ou=users,ou=system");  //check the DN correctly
			env.put(Context.SECURITY_CREDENTIALS, password);
			DirContext con = new InitialDirContext(env);
			System.out.println("success");
			con.close();
			return true;
		}catch (Exception e) {
			System.out.println("failed: "+e.getMessage());
			return false;
		}
	}


	

	
//	public static void main(String[] args) throws NamingException {
//
//		App app = new App();
//		app.newConnection();
//		System.out.println(authUser("waheed","a"));
//
//
//	}
}