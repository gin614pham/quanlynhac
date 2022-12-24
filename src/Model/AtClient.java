package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AtClient extends Remote{
	
	public void callClientMethod(String mess) throws RemoteException;
	
//	public void getLogin(User user) throws RemoteException;
	
}
