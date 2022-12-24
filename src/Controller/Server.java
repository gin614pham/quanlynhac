package Controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import Model.AtServer;
import Services.AtServerImpl;

public class Server {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		AtServer server = new AtServerImpl();
		UnicastRemoteObject.exportObject(server, 3113);
		LocateRegistry.createRegistry(1099);
		Naming.bind("//localhost/srvobj", server);
		System.out.println("Server start");
		System.out.println("Server IPv4: " +getIP());
		server.connect();
		System.out.println("Waiting for client request ...");
		
	}
	
	public static String getIP() throws UnknownHostException {
		InetAddress IP = InetAddress.getLocalHost();
		return IP.getHostAddress();
	}

}
