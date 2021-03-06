/* Generated by AN DISI Unibo */ 
package it.unibo.ctxEventTracer;
import it.unibo.qactors.QActorContext;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.system.SituatedSysKb;
public class MainCtxEventTracer  {
  
//MAIN
public static QActorContext initTheContext() throws Exception{
	IOutputEnvView outEnvView = SituatedSysKb.standardOutEnvView;
	it.unibo.is.interfaces.IBasicEnvAwt env=new it.unibo.baseEnv.basicFrame.EnvFrame( 
		"Env_ctxEventTracer",java.awt.Color.cyan , java.awt.Color.black );
	env.init();
	outEnvView = env.getOutputEnvView();
	String webDir = "./srcMore/it/unibo/ctxEventTracer";
	return QActorContext.initQActorSystem(
		"ctxeventtracer", "./srcMore/it/unibo/ctxEventTracer/eventtracer.pl", 
		"./srcMore/it/unibo/ctxEventTracer/sysRules.pl", outEnvView,webDir,true);
}
public static void main(String[] args) throws Exception{
	initTheContext();
} 	
}
