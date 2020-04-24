package DuckHunt.Main;

import DuckHunt.Other.GameGlobalVariables;
import DuckHunt.Request.OpponentCameraFeed;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class HandleUDP extends Thread {
	
	private DatagramSocket listen = null;
	
	@Override
	public void run() {
		
//		try {
//			listen = new DatagramSocket(GameGlobalVariables.getInstance().getPort()+1);
//		} catch (SocketException e) {
//			e.printStackTrace();
//		}
		
		while (true){
			try {
				
				listen = new DatagramSocket(GameGlobalVariables.getInstance().getPort()+1);
				byte[] recvBuf = new byte[100000];
				DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
				System.out.println("listening packet");
				listen.receive(packet);
				System.out.println("got packet");
				ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
				ObjectInputStream is = new ObjectInputStream(byteStream);
				OpponentCameraFeed opponentCameraFeed = (OpponentCameraFeed) is.readObject();
				GameGlobalVariables.getInstance().getGAMER().sendUDP(opponentCameraFeed.getGroup(),opponentCameraFeed.getFrom(), packet);
				listen.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
//				listen.close();
			}
		}
		
	}
	
	
	
}
