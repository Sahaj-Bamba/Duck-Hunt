package DuckHunt.Online;

import DuckHunt.Global.GameGlobalVariables;
import DuckHunt.Request.OpponentCameraFeed;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class SocketCamService extends Service<Image> {
	
	private DatagramSocket dSock;
	
	public SocketCamService() {
		
		try {
			dSock = new DatagramSocket(GameGlobalVariables.getInstance().getPort()+1);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Task<Image> createTask() {
		return new Task<Image>() {
			@Override
			protected Image call() throws Exception {
				
				try {
					while (!isCancelled()) {
							BufferedImage bimg = getImage();
							
							updateValue(SwingFXUtils.toFXImage(bimg, null));
						
					}
					System.out.println("Cancelled, closing cam");
					System.out.println("Cam closed");
					return getValue();
				} finally {
					dSock.close();
				}
			}
			
		};
	}
	
	private BufferedImage getImage(){
		try {
			byte[] recvBuf = new byte[66000];
			DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
			System.out.println("hell");
			dSock.receive(packet);
			System.out.println("hi");
			ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
			ObjectInputStream is = new ObjectInputStream(byteStream);
			OpponentCameraFeed obj = (OpponentCameraFeed) is.readObject();
			ByteArrayInputStream bais = new ByteArrayInputStream(obj.getImageData());
			return ImageIO.read(bais);
//			Object o = is.readObject();
//			is.close();

//			return ((OpponentCameraFeed)o).getBufferedImage();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}