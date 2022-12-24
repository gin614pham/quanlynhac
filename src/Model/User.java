package Model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String name, password;
	int id, mod;
	
	public User() {
		// TODO Auto-generated method stub
		name = password = null;
		id = mod = -1;
	}

	public User(String name, String password, int id, int mod) {
		super();
		this.name = name;
		this.password = password;
		this.id = id;
		this.mod = mod;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public int getId() {
		return id;
	}

	public int getMod() {
		return mod;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", id=" + id + ", mod=" + mod + "]";
	}
	
	public boolean isName() {
		return name.matches("[a-zA-Z0-9_-]{3,12}$");
	}
	
	public boolean isPassword() {
		return password.matches(".{6,20}");
	}
	
}
