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
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebCamService extends Service<Image> {
	
	private final Webcam cam ;
	private final byte[] opponentAdd;
	private final WebcamResolution resolution ;
	private InetAddress address;
	private DatagramSocket dSock;
	
	public WebCamService(Webcam cam, byte[] opponentAdd, WebcamResolution resolution) {
		this.cam = cam;
		this.opponentAdd = opponentAdd;
		this.resolution = resolution;
		cam.setCustomViewSizes(new Dimension[]{resolution.getSize()});
		cam.setViewSize(resolution.getSize());
		
		try {
			dSock = new DatagramSocket();
			address = InetAddress.getByAddress(opponentAdd);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebCamService(Webcam cam, byte[] opponentAdd) {
		this(cam, opponentAdd, WebcamResolution.QVGA);
	}
	
	@Override
	public Task<Image> createTask() {
		return new Task<Image>() {
			@Override
			protected Image call() throws Exception {
				
				try {
					cam.open();
					while (!isCancelled()) {
						if (cam.isImageNew()) {
							BufferedImage bimg = cam.getImage();
							sendImage(bimg);
							updateValue(SwingFXUtils.toFXImage(bimg, null));
						}
					}
					System.out.println("Cancelled, closing cam");
					cam.close();
					System.out.println("Cam closed");
					return getValue();
				} finally {
					cam.close();
					dSock.close();
				}
			}
			
		};
	}
	
	
	public int getCamWidth() {
		return resolution.getSize().width ;
	}
	
	public int getCamHeight() {
		return resolution.getSize().height ;
	}
	
	private void sendImage(BufferedImage bufferedImage){
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(65500);
			ImageIO.write(bufferedImage,"jpg",byteArrayOutputStream);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
			objectOutputStream.writeObject(new OpponentCameraFeed(byteArrayOutputStream.toByteArray()));
			byte[] sendBuf = bos.toByteArray();
			DatagramPacket packet = new DatagramPacket(sendBuf, sendBuf.length, address, GameGlobalVariables.getInstance().getPort()+1);
			dSock.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}