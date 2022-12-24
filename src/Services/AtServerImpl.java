package Services;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.AtClient;
import Model.AtServer;
import Model.Danh_sach;
import Model.Nhac;
import Model.Tac_gia;
import Model.The_loai;
import Model.User;

public class AtServerImpl implements AtServer{

	AtClient client;
	ConnectDB conn;
	
	@Override
	public void registerClient(AtClient c) throws RemoteException {
		// TODO Auto-generated method stub
		client = c;
	}

	@Override
	public void callServerMethod(String mess) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("==============");
		System.out.println(mess);
		System.out.println(client.toString());
		
	}

	@Override
	public void connect() throws RemoteException {
		// TODO Auto-generated method stub
		conn = new ConnectDB();
		System.out.println("connect SQL");
	}

	@Override
	public ArrayList<Nhac> getListNhac() throws RemoteException {
		// TODO Auto-generated method stub
		for (Nhac n : conn.readNhac()) {
			System.out.println(n);
		}
		return conn.readNhac();
	}

	@Override
	public boolean checkLogin(User user) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Login:" + conn.checkLogin(user));
		return conn.checkLogin(user);
	}

	@Override
	public boolean checkRegister(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.checkRegister(user);
	}

	@Override
	public User Login(User user) throws RemoteException {
		// TODO Auto-generated method stub
		user = conn.getUser(user);
		System.out.println("clien login user:" + user);
		return user;
	}

	@Override
	public ArrayList<Tac_gia> getListTac_gia() throws RemoteException {
		// TODO Auto-generated method stub
		return conn.readTac_gia();
	}

	@Override
	public ArrayList<The_loai> getListThe_loai() throws RemoteException {
		// TODO Auto-generated method stub
		return conn.readThe_loai();
	}

	@Override
	public boolean addNhac(Nhac n) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Thêm bài hát:");
		System.out.println(n.toString());
		return conn.addNhac(n);
	}

	@Override
	public boolean editNhac(Nhac n) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return conn.editNhac(n);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteNhac(Nhac n) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.deleteNhac(n);
	}

	@Override
	public ArrayList<Nhac> findNhac(Nhac n) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.findNhac(n);
	}

	@Override
	public boolean addDSYT(String name, User user) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.addDSYT(name, user);
	}

	@Override
	public boolean editDSYT(Danh_sach ds) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.editDSYT(ds);
	}

	@Override
	public boolean deleteDSYT(Danh_sach ds) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.deleteDSYT(ds);
	}

	@Override
	public ArrayList<Danh_sach> readDS(User user) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.readDS(user);
	}

	@Override
	public ArrayList<Nhac> readNhacInDS(Danh_sach ds) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.readNhacInDS(ds);
	}

	@Override
	public boolean addNhacToDS(Danh_sach ds, Nhac n) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.addNhacToDS(ds, n);
	}

	@Override
	public boolean deleteNhacFromDS(Nhac n, Danh_sach ds) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.deleteNhacFromDS(n, ds);
	}

	@Override
	public Danh_sach getDanh_sach(Danh_sach d, User user) throws RemoteException {
		// TODO Auto-generated method stub
		return conn.getDanh_sach(d, user);
	}








}
