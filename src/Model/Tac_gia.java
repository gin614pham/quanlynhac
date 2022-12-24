package Model;

import java.io.Serializable;

public class Tac_gia implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int id_tac_gia;
	String tac_gia;
	
	public Tac_gia() {
		tac_gia = null;
		id_tac_gia = -1;
	}

	public Tac_gia(int id_tac_gia, String tac_gia) {
		super();
		this.id_tac_gia = id_tac_gia;
		this.tac_gia = tac_gia;
	}

	public void setId_tac_gia(int id_tac_gia) {
		this.id_tac_gia = id_tac_gia;
	}

	public int getId_tac_gia() {
		return id_tac_gia;
	}

	public String getTac_gia() {
		return tac_gia;
	}

	public void setTac_gia(String tac_gia) {
		this.tac_gia = tac_gia;
	}

	@Override
	public String toString() {
		return "Tac_gia [id_tac_gia=" + id_tac_gia + ", tac_gia=" + tac_gia + "]";
	}
	
	
	
}
