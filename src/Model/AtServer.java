package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface AtServer extends Remote{
	
	public void registerClient(AtClient c) throws RemoteException;
	
	public void callServerMethod(String mess) throws RemoteException;
	
	public void connect() throws RemoteException;
	
	public ArrayList<Nhac> getListNhac() throws RemoteException;
	
	public boolean checkLogin(User user) throws RemoteException;
	
	public boolean checkRegister(User user) throws RemoteException;
	
	public User Login(User user) throws RemoteException;
	
	public ArrayList<Tac_gia> getListTac_gia() throws RemoteException;
	
	public ArrayList<The_loai> getListThe_loai() throws RemoteException;
	
	public boolean addNhac(Nhac n) throws RemoteException;
	
	public boolean editNhac(Nhac n) throws RemoteException;
	
	public boolean deleteNhac(Nhac n) throws RemoteException;
	
	public ArrayList<Nhac> findNhac(Nhac n) throws RemoteException;
	
	public boolean addDSYT(String name, User user) throws RemoteException;
	
	public boolean editDSYT(Danh_sach ds) throws RemoteException;
	
	public boolean deleteDSYT(Danh_sach ds) throws RemoteException;
	
	public ArrayList<Danh_sach> readDS (User user) throws RemoteException;
	
	public ArrayList<Nhac> readNhacInDS(Danh_sach ds) throws RemoteException;
	
	public boolean addNhacToDS(Danh_sach ds, Nhac n) throws RemoteException;
	
	public boolean deleteNhacFromDS(Nhac n, Danh_sach ds) throws RemoteException;
	
	public Danh_sach getDanh_sach(Danh_sach d, User user) throws RemoteException;
	
}
