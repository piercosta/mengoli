/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
*/
package it.unibo.console;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.mqtt.utils.MqttUtils;
import it.unibo.qactors.QActorContext;

public class Console extends AbstractConsole { 
	public Console(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
	}

	ConsoleView cv;
	private boolean changeImg;
	public void initializeConsole(String par)	{
		cv = new ConsoleView(this);
	}
	
	public void showImg(String payload) throws IOException {
		if (changeImg){
			println("showImg in Qaconsole");
			byte[] bytes = Base64.getDecoder().decode(payload);
			ByteArrayInputStream is = new ByteArrayInputStream(bytes);
			BufferedImage img = ImageIO.read(is);
			cv.setImage(img);
		}
	}
	
	public void setChangeImg(boolean changeImg) throws IOException {
		this.changeImg = changeImg;
	}
	
	protected MqttUtils mqttUtil;
	
	protected MqttUtils getMqttUtils(){
		if( mqttUtil == null ) mqttUtil = new MqttUtils();
		return mqttUtil;
	}
 	public void connect( String clientid, String brokerAddr, String topic ) throws Exception{
 		println("			%%%  connecting to:" + brokerAddr + " topic=" + topic);
 		getMqttUtils().connect(this,clientid,brokerAddr, topic);
 		println("			%%%  connect done "  );
 	}
 	public void discconnect() throws Exception{
 		getMqttUtils().disconnect();
 	}
	public void publish( String clientid, 
				String brokerAddr, String topic, String msg, int qos, boolean retain) throws Exception{
 		getMqttUtils().publish(this, clientid, brokerAddr, topic,msg,qos,retain);
 	}
	public void subscribe(  String  clientid, String brokerAddr, String topic) throws Exception {
		getMqttUtils().subscribe(this, clientid, brokerAddr, topic);
 		println("			%%%  subscribe done "  );
	}	

}
