/* Generated by AN DISI Unibo */ 
package it.unibo.robot;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.qactors.QActorContext;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.action.ActionReceiveTimed;
import it.unibo.qactors.action.AsynchActionResult;
import it.unibo.qactors.action.IActorAction;
import it.unibo.qactors.action.IActorAction.ActionExecMode;
import it.unibo.iot.configurator.Configurator;
import it.unibo.iot.executors.baseRobot.IBaseRobot; 
import it.unibo.iot.models.sensorData.distance.IDistanceSensorData;
import it.unibo.iot.models.sensorData.impact.IImpactSensorData;
import it.unibo.iot.models.sensorData.line.ILineSensorData;
import it.unibo.iot.models.sensorData.magnetometer.IMagnetometerSensorData;
import it.unibo.iot.sensors.ISensor; 
import it.unibo.iot.sensors.ISensorObserver;
import it.unibo.iot.sensors.distanceSensor.DistanceSensor;
import it.unibo.iot.sensors.impactSensor.ImpactSensor;
import it.unibo.iot.sensors.lineSensor.LineSensor;
import it.unibo.iot.sensors.magnetometerSensor.MagnetometerSensor;
import it.unibo.qactors.action.IMsgQueue;
import it.unibo.qactors.QActorMessage;
import it.unibo.qactors.QActorUtils;


class QaRobotActor extends it.unibo.qactor.robot.RobotActor{
	public QaRobotActor(
		String name, QActorContext ctx, String worldTheoryPath,
			IOutputEnvView outEnvView, String baserobot, String defaultPlan )  throws Exception{
		super(name, ctx, "./srcMore/it/unibo/robot/plans.txt", worldTheoryPath,
		outEnvView, it.unibo.qactor.robot.RobotSysKb.setRobotBase(ctx, baserobot) , defaultPlan);
	}
}

public class AbstractRobot extends QaRobotActor { 
protected AsynchActionResult aar = null;
protected boolean actionResult = true;
protected alice.tuprolog.SolveInfo sol;
//protected IMsgQueue mysupport ;  //defined in QActor
protected String planFilePath    = null;
protected String terminationEvId = "default";
protected String parg="";
protected boolean bres=false;
protected IActorAction  action;

		protected static IOutputEnvView setTheEnv(IOutputEnvView outEnvView ){
			return outEnvView;
		}


	public AbstractRobot(String actorId, QActorContext myCtx, IOutputEnvView outEnvView ,String baserobot)  throws Exception{
		super(actorId, myCtx,  
		"./srcMore/it/unibo/robot/WorldTheory.pl",
		setTheEnv( outEnvView ) ,baserobot , "init");		
		this.planFilePath = "./srcMore/it/unibo/robot/plans.txt";
		//Plan interpretation is done in Prolog
		//if(planFilePath != null) planUtils.buildPlanTable(planFilePath);
 	}
	@Override
	protected void doJob() throws Exception {
		String name  = getName().replace("_ctrl", "");
		mysupport = (IMsgQueue) QActorUtils.getQActor( name );
 		initSensorSystem();
		boolean res = init();
		//println(getName() + " doJob " + res );
	} 
	/* 
	* ------------------------------------------------------------
	* PLANS
	* ------------------------------------------------------------
	*/
    public boolean init() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "init";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		if( ! planUtils.switchToPlan("waitForStart").getGoon() ) break;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=init WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean waitForStart() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "waitForStart";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = "\"waiting for start\"";
    		println( temporaryStr );  
    		//senseEvent
    		timeoutval = 600000;
    		aar = planUtils.senseEvents( timeoutval,"start","continue",
    		"" , "",ActionExecMode.synch );
    		if( ! aar.getGoon() || aar.getTimeRemained() <= 0 ){
    			//println("			WARNING: sense timeout");
    			addRule("tout(senseevent,"+getName()+")");
    			//break;
    		}
    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?tout(X,Y)" )) != null ){
    		temporaryStr = "tout(X,Y)";
    		temporaryStr = QActorUtils.substituteVars(guardVars,temporaryStr);
    		println( temporaryStr );  
    		}
    		if( (guardVars = QActorUtils.evalTheGuard(this, " ??tout(600000,Y)" )) != null ){
    		if( ! planUtils.switchToPlan("takePicture").getGoon() ) break;
    		}
    		printCurrentEvent(false);
    		//onEvent
    		if( currentEvent.getEventId().equals("start") ){
    		 		String parg = "";
    		 		/* SwitchPlan */
    		 		parg =  updateVars(  Term.createTerm("start"), Term.createTerm("start"), 
    		 			    		  					Term.createTerm(currentEvent.getMsg()), parg);
    		 			if( parg != null ){
    		 				 if( ! planUtils.switchToPlan("running").getGoon() ) break; 
    		 			}//else println("guard  fails");  //parg is null when there is no guard (onEvent)
    		 }
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=waitForStart WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean running() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "running";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = "\"running\"";
    		println( temporaryStr );  
    		//forward
    		if( ! execRobotMove("running","forward",70,0,100000, "takepicture,stop,obstacle" , "takePicture,stopTheRobot,stopTheRobot") ) break;
    		if( ! planUtils.switchToPlan("stopTheRobot").getGoon() ) break;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=running WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean stopTheRobot() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "stopTheRobot";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = "\"Stop the robot\"";
    		println( temporaryStr );  
    		//stop
    		if( ! execRobotMove("stopTheRobot","stop",10,0,0, "" , "") ) break;
    		if( ! planUtils.switchToPlan("waitForStart").getGoon() ) break;
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=stopTheRobot WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    public boolean takePicture() throws Exception{	//public to allow reflection
    try{
    	curPlanInExec =  "takePicture";
    	boolean returnValue = suspendWork;
    while(true){
    nPlanIter++;
    		temporaryStr = "\"start blink led\"";
    		println( temporaryStr );  
    		//left
    		if( ! execRobotMove("takePicture","left",70,0,600, "" , "") ) break;
    		temporaryStr = "\"picture\"";
    		println( temporaryStr );  
    		//delay
    		aar = delayReactive(1000,"" , "");
    		if( aar.getInterrupted() ) curPlanInExec   = "takePicture";
    		if( ! aar.getGoon() ) break;
    		//right
    		if( ! execRobotMove("takePicture","right",70,0,650, "" , "") ) break;
    		temporaryStr = "\"stop blink led\"";
    		println( temporaryStr );  
    		returnValue = continueWork;  
    break;
    }//while
    return returnValue;
    }catch(Exception e){
       //println( getName() + " plan=takePicture WARNING:" + e.getMessage() );
       QActorContext.terminateQActorSystem(this); 
       return false;  
    }
    }
    /* 
    * ------------------------------------------------------------
    * SENSORS
    * ------------------------------------------------------------
    */
    protected void initSensorSystem(){		
    	try {
    		String goal = "consult( \"./src/it/unibo/robot/sensorTheory.pl\" )";
    		SolveInfo sol = QActorUtils.solveGoal( goal ,pengine );
    		if( ! sol.isSuccess() ){
    			//println( "avatar initSensorSystem attempt to load sensorTheory "  );
    			goal = "consult( \"./sensorTheory.pl\" )";
    			QActorUtils.solveGoal( pengine, goal  );
    			//println( "avatar initSensorSystem= "  +  aar.getResult() );
    		}
    		addSensorObservers();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    /*
    //COMPONENTS
     RobotComponent motorleft 
     RobotComponent motorright 
    sensor l1Mock simulated debug=0   
    sensor distFrontMock simulated debug=0   
    sensor mgn1 simulated debug=1   
    sensor impact1 simulated debug=0   
    Composed component rot
    Composed component motors
    */
    protected void addSensorObservers(){
    	for (ISensor<?> sensor : Configurator.getInstance().getSensors()) {
    		//println( "robot sensor= "  + sensor.getDefStringRep() );
    		//println( "robot sensor class= "  + sensor.getClass().getName() );
        	if( sensor instanceof DistanceSensor){
        		DistanceSensor sensorDistance  = (DistanceSensor) sensor;
        		ISensorObserver<IDistanceSensorData> obs = new SensorObserver<IDistanceSensorData>(this,outEnvView);
        //		println( "avatar add observer to  "  + sensorDistance.getDefStringRep() );
        		sensorDistance.addObserver(  obs  ) ;
        	}
        	if( sensor instanceof LineSensor){
        		LineSensor sensorLine = (LineSensor) sensor;
         		ISensorObserver<ILineSensorData> obs = new SensorObserver<ILineSensorData>(this,outEnvView);
        //		println( "avatar add observer to  "  + sensorLine.getDefStringRep() );
        		sensorLine.addObserver(  obs  ) ;
        	}
         	if( sensor instanceof MagnetometerSensor){
        		MagnetometerSensor sensorMagneto = (MagnetometerSensor) sensor;
         		ISensorObserver<IMagnetometerSensorData> obs = new SensorObserver<IMagnetometerSensorData>(this,outEnvView);
        //		println( "avatar add observer to  "  + sensorMagneto.getDefStringRep() );
        		sensorMagneto.addObserver(  obs  ) ;
        	}
    		if( sensor instanceof ImpactSensor){
    			ImpactSensor sensorImpact = (ImpactSensor) sensor;
    			ISensorObserver<IImpactSensorData> obs = new SensorObserver<IImpactSensorData>(this,outEnvView);
    	//		println( "avatar add observer to  "  + sensorMagneto.getDefStringRep() );
    			sensorImpact.addObserver(  obs  ) ;
    		}
    	}		
    }	
    
 
	/* 
	* ------------------------------------------------------------
	* APPLICATION ACTIONS
	* ------------------------------------------------------------
	*/
	/* 
	* ------------------------------------------------------------
	* QUEUE  
	* ------------------------------------------------------------
	*/
	    protected void getMsgFromInputQueue(){
//	    	println( " %%%% getMsgFromInputQueue" ); 
	    	QActorMessage msg = mysupport.getMsgFromQueue(); //blocking
//	    	println( " %%%% getMsgFromInputQueue continues with " + msg );
	    	this.currentMessage = msg;
	    }
  }

