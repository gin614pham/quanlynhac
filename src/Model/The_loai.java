package Model;

import java.io.Serializable;

public class The_loai implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int id_the_loai;
	String the_loai;
	
	public The_loai() {
		the_loai = null;
		id_the_loai = -1;
	}

	public The_loai(String the_loai, int id_the_loai) {
		super();
		this.the_loai = the_loai;
		this.id_the_loai = id_the_loai;
	}

	public void setId_the_loai(int id_the_loai) {
		this.id_the_loai = id_the_loai;
	}

	public int getId_the_loai() {
		return id_the_loai;
	}

	public String getThe_loai() {
		return the_loai;
	}

	public void setThe_loai(String the_loai) {
		this.the_loai = the_loai;
	}

	@Override
	public String toString() {
		return "The_loai [id_the_loai=" + id_the_loai + ", the_loai=" + the_loai + "]";
	}
	
	
	
}
