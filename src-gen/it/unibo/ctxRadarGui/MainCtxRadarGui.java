/* Generated by AN DISI Unibo */ 
package it.unibo.ctxRadarGui;
import it.unibo.qactors.QActorContext;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedSysKb;
public class MainCtxRadarGui  {
  
//MAIN
public static QActorContext initTheContext() throws Exception{
	IOutputEnvView outEnvView = SituatedSysKb.standardOutEnvView;
	String webDir = null;
	return QActorContext.initQActorSystem(
		"ctxradargui", "./srcMore/it/unibo/ctxRadarGui/mengoli.pl", 
		"./srcMore/it/unibo/ctxRadarGui/sysRules.pl", outEnvView,webDir,false);
}
public static void main(String[] args) throws Exception{
	initTheContext();
} 	
}
