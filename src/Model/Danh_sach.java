package Model;

import java.io.Serializable;

public class Danh_sach implements Serializable{
	int id;
	String name;
	
	public Danh_sach() {
		id = -1;
		name = null;
	}

	public Danh_sach(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isNameDS() {
		if (this.name.matches("[a-z0-9A-Z-_]{1,50}$")) return true; return false;
	}
	

}
