package Model;

import java.io.Serializable;

public class Nhac implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String Ma, Ten, Loi, Tac_gia, The_loai, Hinh_thuc;

	public Nhac() {
		Ma = Ten = Loi = The_loai = Tac_gia = Hinh_thuc = null;
	}

	public Nhac(String ma, String ten, String loi, String hinh_thuc, String the_loai, String tac_gia) {
		super();
		Ma = ma;
		Ten = ten;
		Loi = loi;
		Hinh_thuc = hinh_thuc;
		The_loai = the_loai;
		Tac_gia = tac_gia;
	}

	

	public String getMa() {
		return Ma;
	}

	@Override
	public String toString() {
		return "Nhac [Ma=" + Ma + ", Ten=" + Ten + ", Loi=" + Loi + ", Tac_gia=" + Tac_gia + ", The_loai=" + The_loai
				+ ", Hinh_thuc=" + Hinh_thuc + "]";
	}

	public String getTen() {
		return Ten;
	}

	public String getLoi() {
		return Loi;
	}

	public String getHinh_thuc() {
		return Hinh_thuc;
	}

	public void setMa(String ma) {
		Ma = ma;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public void setLoi(String loi) {
		Loi = loi;
	}

	public void setHinh_thuc(String hinh_thuc) {
		Hinh_thuc = hinh_thuc;
	}

	public String getThe_loai() {
		return The_loai;
	}

	public String getTac_gia() {
		return Tac_gia;
	}

	public void setThe_loai(String the_loai) {
		The_loai = the_loai;
	}

	public void setTac_gia(String tac_gia) {
		Tac_gia = tac_gia;
	}

	public boolean isNhac() {
		if (!this.Ma.matches("[0-9]{1,9}$")) return false;
		if (this.Ten.isEmpty()) setTen("<unknown>");
		if (this.Tac_gia.isEmpty()) setTac_gia("<unknown>");
		
		
		return true;
	}

	public void beforeFind() {
		this.Ten = this.Ten.replace(" ", "%");
		this.Loi = this.Loi.replace(" ", "%");
		this.Tac_gia = this.Tac_gia.replace(" ", "%");
		
		
	}

	

	
}
