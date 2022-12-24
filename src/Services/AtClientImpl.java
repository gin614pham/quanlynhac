package Services;

import java.rmi.RemoteException;

import Model.AtClient;
import Model.User;

public class AtClientImpl implements AtClient{
	
	@Override
	public void callClientMethod(String mess) throws RemoteException {
		System.out.println(mess);
		
	}

//	@Override
//	public void getLogin(User user) throws RemoteException {
//		// TODO Auto-generated method stub
//		this.user = user;
//		System.out.println(this.user);
//	}

	
	
		
}
