package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Danh_sach;
import Model.Nhac;
import Model.Tac_gia;
import Model.The_loai;
import Model.User;

public class ConnectDB {
	
	private Connection conn = null;
	
	public ConnectDB() {
		try{
			   String userName = "root";
			   String password = "";
			   String url = "jdbc:mysql://localhost/mydbnhac";
			   Class.forName ("com.mysql.cj.jdbc.Driver");
			   conn = DriverManager.getConnection(url, userName, password);
			   
		} catch(Exception e){
			   System.out.println(e.getMessage());
		}
	}
	
	// lấy danh sách nhạc
	public ArrayList<Nhac> readNhac(){
		ArrayList<Nhac> list = new ArrayList<>();
		
		String sql = "SELECT ma, ten, loi, `tac_gia`.`ten_tac_gia` AS tac_gia, `the_loai`.`ten_the_loai` AS the_loai, `hinh_thuc`.`ten_hinh_thuc` AS hinh_thuc \r\n"
				+ " FROM `danh_sach_nhac`\r\n"
				+ "INNER JOIN `tac_gia` ON `tac_gia`.`id_tac_gia` = `danh_sach_nhac`.`tac_gia`\r\n"
				+ "INNER JOIN `the_loai` ON `the_loai`.`id_the_loai` = `danh_sach_nhac`.`the_loai`\r\n"
				+ "INNER JOIN `hinh_thuc` ON `hinh_thuc`.`id_hinh_thuc` = `danh_sach_nhac`.`hinh_thuc` WHERE 1 ";
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				Nhac n = new Nhac();
				n.setMa(rs.getString(1));
				n.setTen(rs.getString(2));
				n.setLoi(rs.getString(3));
				n.setTac_gia(rs.getString(4));
				n.setThe_loai(rs.getString(5));
				n.setHinh_thuc(rs.getString(6));
				
				list.add(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// lấy danh sách tác giả
	public ArrayList<Tac_gia> readTac_gia(){
		ArrayList<Tac_gia> list = new ArrayList<>();
		
		String sql = "SELECT * FROM `tac_gia`";
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				Tac_gia tg = new Tac_gia();
				tg.setId_tac_gia(rs.getInt(1));
				tg.setTac_gia(rs.getString(2));
				
				list.add(tg);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// lấy danh sách thể loại
	public ArrayList<The_loai> readThe_loai(){
		ArrayList<The_loai> list = new ArrayList<>();
		
		String sql = "SELECT * FROM `the_loai`";
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				The_loai tl = new The_loai();
				tl.setId_the_loai(rs.getInt(1));
				tl.setThe_loai(rs.getString(2));
				
				list.add(tl);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// thêm nhạc
	public boolean addNhac(Nhac n) {
		Statement stmt;
		try {
			if(getIDTac_Gia(n.getTac_gia()) == -1) {
				addTacGia(n.getTac_gia());
			}
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM `danh_sach_nhac`");
			rs.moveToInsertRow();
			rs.updateString(1, n.getMa());
			rs.updateString(2, n.getTen());
			rs.updateString(3, n.getLoi());
			rs.updateInt(4, getIDTac_Gia(n.getTac_gia()));
			rs.updateInt(5, getIDThe_loai(n.getThe_loai()));
			rs.updateInt(6, getIDHinh_thuc(n.getHinh_thuc()));
			rs.insertRow();
			
			System.out.println("Thành công!!");
			return true;
		} catch (SQLException e) {
			System.out.println("Thất bại!");
			return false;
		}
	}
	
	// sửa nhạc
	public boolean editNhac(Nhac nhac) throws SQLException {
		
		if(getIDTac_Gia(nhac.getTac_gia()) == -1) {
			addTacGia(nhac.getTac_gia());
		}
		
		String sql = "UPDATE `danh_sach_nhac` SET "
				+ "ten = '" + nhac.getTen() + "', "
				+ "loi = '" + nhac.getLoi() + "', "
				+ "tac_gia = '" + getIDTac_Gia(nhac.getTac_gia())  + "', "
				+ "the_loai = '" + getIDThe_loai(nhac.getThe_loai()) + "', "
				+ "hinh_thuc = '" + getIDHinh_thuc(nhac.getHinh_thuc()) + "' "
				+ "where ma = " + nhac.getMa();
		PreparedStatement kn = conn.prepareStatement(sql);
		return kn.executeUpdate() > 0;
	}
	
	public boolean deleteNhac(Nhac nhac) {
		String sql = "DELETE FROM `danh_sach_nhac` WHERE `danh_sach_nhac`.`ma` = \'" + nhac.getMa() + "\'";
		PreparedStatement kn;
		try {
			kn = conn.prepareStatement(sql);
			return kn.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	
	}
	
	public ArrayList<Nhac> findNhac(Nhac nhac){
		
		ArrayList<Nhac> l = new ArrayList<>();
		
		String sql = "SELECT ma, ten, loi, `tac_gia`.`ten_tac_gia` AS tac_gia, `the_loai`.`ten_the_loai` AS the_loai, `hinh_thuc`.`ten_hinh_thuc` AS hinh_thuc \r\n"
				+ " FROM `danh_sach_nhac`\r\n"
				+ "INNER JOIN `tac_gia` ON `tac_gia`.`id_tac_gia` = `danh_sach_nhac`.`tac_gia`\r\n"
				+ "INNER JOIN `the_loai` ON `the_loai`.`id_the_loai` = `danh_sach_nhac`.`the_loai`\r\n"
				+ "INNER JOIN `hinh_thuc` ON `hinh_thuc`.`id_hinh_thuc` = `danh_sach_nhac`.`hinh_thuc`\r\n"
				+ "WHERE  `danh_sach_nhac`.`ma` like '%" + nhac.getMa()+ "%'\r\n"
				+ "AND `danh_sach_nhac`.`ten` like '%"+ nhac.getTen() +"%'\r\n"
				+ "AND `danh_sach_nhac`.`loi` like '%" + nhac.getLoi() + "%'\r\n"
				+ "AND `tac_gia`.`ten_tac_gia` like '%"+ nhac.getTac_gia() +"%'\r\n"
				+ "AND `the_loai`.`ten_the_loai` like '%"+ nhac.getThe_loai() +"%'\r\n"
				+ "AND `hinh_thuc`.`ten_hinh_thuc` like '%"+ nhac.getHinh_thuc() +"%';";
	
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				Nhac n = new Nhac();
				n.setMa(rs.getString(1));
				n.setTen(rs.getString(2));
				n.setLoi(rs.getString(3));
				n.setTac_gia(rs.getString(4));
				n.setThe_loai(rs.getString(5));
				n.setHinh_thuc(rs.getString(6));
				
				l.add(n);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
		
	}
	
	public void addTacGia(String tg) throws SQLException {
		
		String sql = "INSERT INTO `tac_gia` (`ten_tac_gia`) VALUES (?)";
		PreparedStatement kn = conn.prepareStatement(sql);
		kn.setString(1, tg);
		kn.executeUpdate();
		
	}
	
	
	// kiểm tra đăng nhâp
	public boolean checkLogin(User user) {
		
		String sql = "SELECT * FROM `user` WHERE user_name = '" + user.getName() + "' AND password = '" + user.getPassword() + "'";
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			if (rs.next()) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// kiểm tra đăng ký
	public boolean checkRegister(User user) {
		
		String sql = "INSERT INTO `user` (`user_name`, `password`) VALUES (?,?)";
		try {
			PreparedStatement kn = conn.prepareStatement(sql);
			kn.setString(1, user.getName());
			kn.setString(2, user.getPassword());
			return kn.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// lấy người dùng
	public User getUser(User user) {
		String sql = "SELECT * FROM `user` WHERE user_name = '" + user.getName() + "' AND password = '" + user.getPassword() + "'";
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			rs.next();
			user.setId(rs.getInt(1));
			user.setMod(rs.getInt(4));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	// thêm danh sách yêu thích
	public boolean addDSYT(String name, User user) {
		
		String sql = "INSERT INTO `user-danhsach` (`id_user`, `name`) VALUES (?,?)";
		
		PreparedStatement kn;
		try {
			kn = conn.prepareStatement(sql);
			kn.setInt(1, user.getId());
			kn.setString(2, name);
			
			return kn.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	// sửa tên danh sách
	public boolean editDSYT(Danh_sach ds) {
		
		String sql = "UPDATE `user-danhsach` SET `name` = '" + ds.getName() + "' WHERE `user-danhsach`.`id` = " + ds.getId();
		
		PreparedStatement kn;
		try {
			kn = conn.prepareStatement(sql);
			return kn.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	// xóa danh sách
	public boolean deleteDSYT(Danh_sach ds) {
		
		String sql = "DELETE FROM `user-danhsach` WHERE `user-danhsach`.`id` = " + ds.getId();
		
		try {
			PreparedStatement kn = conn.prepareStatement(sql);
			return kn.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	//đọc danh sách 
	
	public ArrayList<Danh_sach> readDS(User user) {
		ArrayList<Danh_sach> ds = new ArrayList<>();
		
		String sql = "SELECT * FROM `user-danhsach` WHERE `id_user` = " + user.getId();
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				Danh_sach d = new Danh_sach();
				d.setId(rs.getInt(1));
				d.setName(rs.getString(3));
				
				ds.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ds;
	}
	
	// lấy nhạc trong danh sách 
	public ArrayList<Nhac> readNhacInDS(Danh_sach ds){
		ArrayList<Nhac> l = new ArrayList<>();
		
		String sql = "SELECT `danh_sach_nhac`.*, `tac_gia`.*, `the_loai`.*, `hinh_thuc`.*, `nhac-danhsach`.* "
				+ "FROM `danh_sach_nhac` INNER JOIN `tac_gia` ON `danh_sach_nhac`.`tac_gia` = `tac_gia`.`id_tac_gia` "
				+ "INNER JOIN `the_loai` ON `danh_sach_nhac`.`the_loai` = `the_loai`.`id_the_loai` "
				+ "INNER JOIN `hinh_thuc` ON `danh_sach_nhac`.`hinh_thuc` = `hinh_thuc`.`id_hinh_thuc` "
				+ "INNER JOIN `nhac-danhsach` ON `nhac-danhsach`.`id_nhac` = `danh_sach_nhac`.`ma` "
				+ "WHERE `nhac-danhsach`.`id_danhsach` = " + ds.getId();
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				Nhac n = new Nhac();
				
				n.setMa(rs.getString(1));
				n.setTen(rs.getString(2));
				n.setLoi(rs.getString(3));
				n.setTac_gia(rs.getString(8));
				n.setThe_loai(rs.getString(10));
				n.setHinh_thuc(rs.getString(12));
				
				l.add(n);
				
			}
				
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}
	
	//thêm nhạc vào danh sách
	public boolean addNhacToDS(Danh_sach ds, Nhac n) {
		
		String sql = "INSERT INTO `nhac-danhsach` (`id_nhac`, `id_danhsach`) VALUES (?,?)";
		
		try {
			PreparedStatement kn = conn.prepareStatement(sql);
			kn.setString(1, n.getMa());
			kn.setInt(2, ds.getId());
			
			return kn.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	// xóa nhạc khỏi danh sách
	public boolean deleteNhacFromDS(Nhac n, Danh_sach ds) {
		
		String sql = "DELETE FROM `nhac-danhsach` WHERE `nhac-danhsach`.`id_nhac` = " + n.getMa() + " AND `nhac-danhsach`.`id_danhsach` = " + ds.getId() + " LIMIT 1";
		
		try {
			PreparedStatement kn = conn.prepareStatement(sql);
			return kn.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return false;
	}
	
	public Danh_sach getDanh_sach(Danh_sach d, User user) {
		Danh_sach ds = new Danh_sach();
		
		String sql = "SELECT * FROM `user-danhsach` WHERE `id_user` = " + user.getId() + " AND `name` = '" + d.getName() + "' ORDER BY `id` DESC";
		
		PreparedStatement kn;
		try {
			kn = conn.prepareStatement(sql);
			ResultSet rs = kn.executeQuery();
			if (rs.next()) {
				ds.setId(rs.getInt(1));
				ds.setName(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ds;
	}
	
	// lấy id tác giã
	public int getIDTac_Gia(String name) {
		int id = -1;
			
		String sql = "SELECT id_tac_gia FROM `tac_gia` WHERE ten_tac_gia = '" + name + "' LIMIT 1";
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return id;
	}
	
	public int getIDThe_loai(String name) {
		int id = -1;
			
		String sql = "SELECT id_the_loai FROM `the_loai` WHERE ten_the_loai = '" + name + "' LIMIT 1";
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return id;
	}
	
	public int getIDHinh_thuc(String name) {
		int id = -1;
			
		String sql = "SELECT id_hinh_thuc FROM `hinh_thuc` WHERE ten_hinh_thuc = '" + name + "' LIMIT 1";
		
		try {
			PreparedStatement rl = conn.prepareStatement(sql);
			ResultSet rs = rl.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return id;
	}
		
	
}
