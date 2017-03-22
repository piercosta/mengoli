/* Generated by AN DISI Unibo */ 
/*
This code is generated only ONCE
*/
package it.unibo.camera;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.mqtt.utils.MqttUtils;
import it.unibo.qactors.QActorContext;

public class Camera extends AbstractCamera { 
	public Camera(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
		super(actorId, myCtx, outEnvView);
	}

	protected MqttUtils mqttUtil;	
	
//	int i = 0;
	public void takeAPicture (String par)	{
		//ProcessBuilder pb = new ProcessBuilder("sudo", "python", "./camera.py");
		println("takeAPicture init");
		ProcessBuilder pb = new ProcessBuilder("sudo", "python", "./camera.py");
		//ProcessBuilder pb = new ProcessBuilder("python", "C:/Users/Utente/workspace/it.unibo.provacamera/src/camera.py");
		pb.redirectErrorStream(true);
	    
		String string = null;
	    Process proc = null;
	    try {
	      proc = pb.start();
	      BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
	      string = in.readLine();
	    }
	    catch (IOException e) {
	    	println("errore nel python");
	    	println(string);
	        e.printStackTrace();
	    }

//	    -----------------------------
//		TEST SENZA FOTOCAMERA
//	    -----------------------------
//	    File file ;
//		switch (i)	{
//			case 0	:
//				file = new File("./photo.jpg");
//				break;
//			case 1	:
//				file = new File("./photo1.jpg");
//				break;
//			case 2	:
//				file = new File("./photo2.jpg");
//				break;
//			default	:
//				file = new File("./photodefault.jpg");
//		    	println("errore nello switch - i="+i);
//				break;
//		}
//		i++;
	    
	    File file = new File("photo.jpg");
		FileInputStream fis;
		byte[] byteArray = null;
		try {
			fis = new FileInputStream(file);
			byteArray = new byte[(int) file.length()];
			fis.read(byteArray);
			fis.close();
			
			String imgencoded = Base64.getEncoder().encodeToString(byteArray);
			publish("observable_mqtt", "tcp://m2m.eclipse.org:1883", "qacamera", imgencoded, 0, true); //set 0: fire and forget
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
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
	public void publish(String clientid, String brokerAddr, String topic, String msg, int qos, boolean retain) throws Exception{
		println("			%%%  publish init"  );
		getMqttUtils().publish(this, clientid, brokerAddr, topic,msg,qos,retain);
 		println("			%%%  publish done "  );
 	}
	public void subscribe(  String  clientid, String brokerAddr, String topic) throws Exception {
		getMqttUtils().subscribe(this,clientid,brokerAddr, topic);
 		println("			%%%  subscribe done "  );
	}	
}

