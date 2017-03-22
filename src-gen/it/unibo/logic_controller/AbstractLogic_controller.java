/* Generated by AN DISI Unibo */ 
package it.unibo.logic_controller;
import alice.tuprolog.Struct;
import alice.tuprolog.Term;
import it.unibo.qactors.QActorContext;
import it.unibo.qactors.ActorTerminationMessage;
import it.unibo.qactors.QActorMessage;
import it.unibo.qactors.QActorUtils;
import it.unibo.contactEvent.interfaces.IEventItem;
import it.unibo.is.interfaces.IOutputEnvView;
import it.unibo.qactors.action.ActionReceiveTimed;
import it.unibo.qactors.action.AsynchActionResult;
import it.unibo.qactors.action.IActorAction;
import it.unibo.qactors.action.IActorAction.ActionExecMode;
import it.unibo.qactors.action.IMsgQueue;
import it.unibo.qactors.akka.QActor;


//REGENERATE AKKA: QActor instead QActorPlanned
public abstract class AbstractLogic_controller extends QActor { 
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
	
	
		public AbstractLogic_controller(String actorId, QActorContext myCtx, IOutputEnvView outEnvView )  throws Exception{
			super(actorId, myCtx,  
			"./srcMore/it/unibo/logic_controller/WorldTheory.pl",
			setTheEnv( outEnvView )  , "init");		
			this.planFilePath = "./srcMore/it/unibo/logic_controller/plans.txt";
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
	    		temporaryStr = "\"control init\"";
	    		println( temporaryStr );  
	    		parg = "loadTheory(\"./extendedWorldTheory.pl\")";
	    		//tout=1 day (24 h)
	    		//aar = solveGoalReactive(parg,86400000,"","");
	    		//genCheckAar(m.name)»		
	    		QActorUtils.solveGoal(parg,pengine );
	    		if( ! planUtils.switchToPlan("waitForNumSonars").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=init WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean waitForNumSonars() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "waitForNumSonars";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"waiting for num sensors\"";
	    		println( temporaryStr );  
	    		//senseEvent
	    		timeoutval = 600000;
	    		aar = planUtils.senseEvents( timeoutval,"numOfSonars","continue",
	    		"" , "",ActionExecMode.synch );
	    		if( ! aar.getGoon() || aar.getTimeRemained() <= 0 ){
	    			//println("			WARNING: sense timeout");
	    			addRule("tout(senseevent,"+getName()+")");
	    			//break;
	    		}
	    		temporaryStr = "\"receive data\"";
	    		println( temporaryStr );  
	    		if( ! planUtils.switchToPlan("waitForConsoleStart").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=waitForNumSonars WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean waitForConsoleStart() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "waitForConsoleStart";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"wait start button\"";
	    		println( temporaryStr );  
	    		//delay
	    		aar = delayReactive(30000,"" , "");
	    		if( aar.getInterrupted() ) curPlanInExec   = "waitForConsoleStart";
	    		if( ! aar.getGoon() ) break;
	    		if( ! planUtils.switchToPlan("robotRunning").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=waitForConsoleStart WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean robotRunning() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "robotRunning";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"running\"";
	    		println( temporaryStr );  
	    		if( ! planUtils.switchToPlan("receiveData").getGoon() ) break;
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=robotRunning WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean receiveData() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "receiveData";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"receiving sonar data\"";
	    		println( temporaryStr );  
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " ??msg(E,\"event\",S,none,obstacle,N)" )) != null ){
	    		if( ! planUtils.switchToPlan("obstacleFound").getGoon() ) break;
	    		}
	    		if( ! planUtils.switchToPlan("evaluateData").getGoon() ) break;
	    		if( planUtils.repeatPlan(0).getGoon() ) continue;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=receiveData WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean evaluateData() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "evaluateData";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?checkdetected" )) != null ){
	    		temporaryStr = "\"detected robot\"";
	    		temporaryStr = QActorUtils.substituteVars(guardVars,temporaryStr);
	    		println( temporaryStr );  
	    		}
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?checkdetected" )) != null ){
	    		if( ! planUtils.switchToPlan("sendTakePicture").getGoon() ) break;
	    		}
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?checkexpression" )) != null ){
	    		if( ! planUtils.switchToPlan("alarm").getGoon() ) break;
	    		}
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=evaluateData WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean alarm() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "alarm";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"alarm\"";
	    		println( temporaryStr );  
	    		//playsound
	    		terminationEvId =  QActorUtils.getNewName(IActorAction.endBuiltinEvent);
	    			 	aar = playSound("./audio/doh.wav", ActionExecMode.synch, terminationEvId, 1500,"" , "" ); 
	    				//println(getName() + " plan " + curPlanInExec  +  " interrupted=" + aar.getInterrupted() + " action goon="+aar.getGoon());
	    				if( aar.getInterrupted() ){
	    					curPlanInExec   = "alarm";
	    					if( ! aar.getGoon() ) break;
	    				} 			
	    		if( ! planUtils.switchToPlan("sendStop").getGoon() ) break;
	    		if( ! planUtils.switchToPlan("waitForConsoleStart").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=alarm WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean prologFailure() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "prologFailure";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"prolog failure\"";
	    		println( temporaryStr );  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=prologFailure WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean sendStart() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "sendStart";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"send start\"";
	    		println( temporaryStr );  
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "start","start", guardVars ).toString();
	    		emit( "start", temporaryStr );
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=sendStart WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean obstacleFound() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "obstacleFound";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"obstacle found\"";
	    		println( temporaryStr );  
	    		if( ! planUtils.switchToPlan("waitForConsoleStart").getGoon() ) break;
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=obstacleFound WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean sendStop() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "sendStop";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"send stop\"";
	    		println( temporaryStr );  
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "stop","stop", guardVars ).toString();
	    		emit( "stop", temporaryStr );
	    		if( ! planUtils.switchToPlan("waitForConsoleStart").getGoon() ) break;
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=sendStop WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean sendTakePicture() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "sendTakePicture";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"send take picture\"";
	    		println( temporaryStr );  
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "takepicture","takepicture", guardVars ).toString();
	    		emit( "takepicture", temporaryStr );
	    		if( ! planUtils.switchToPlan("updateRadarGui").getGoon() ) break;
	    		if( ! planUtils.switchToPlan("waitPictureDone").getGoon() ) break;
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=sendTakePicture WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean waitPictureDone() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "waitPictureDone";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"waiting picture done\"";
	    		println( temporaryStr );  
	    		//delay
	    		aar = delayReactive(1000,"" , "");
	    		if( aar.getInterrupted() ) curPlanInExec   = "waitPictureDone";
	    		if( ! aar.getGoon() ) break;
	    		temporaryStr = "\"pic done\"";
	    		println( temporaryStr );  
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=waitPictureDone WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    public boolean updateRadarGui() throws Exception{	//public to allow reflection
	    try{
	    	curPlanInExec =  "updateRadarGui";
	    	boolean returnValue = suspendWork;
	    while(true){
	    nPlanIter++;
	    		temporaryStr = "\"update radar gui\"";
	    		println( temporaryStr );  
	    		if( (guardVars = QActorUtils.evalTheGuard(this, " !?lastsonar(Distance,SID)" )) != null ){
	    		temporaryStr = QActorUtils.unifyMsgContent(pengine, "p(Distance,SID)","p(Distance,SID)", guardVars ).toString();
	    		emit( "sonarToGui", temporaryStr );
	    		}
	    		returnValue = continueWork;  
	    break;
	    }//while
	    return returnValue;
	    }catch(Exception e){
	       //println( getName() + " plan=updateRadarGui WARNING:" + e.getMessage() );
	       QActorContext.terminateQActorSystem(this); 
	       return false;  
	    }
	    }
	    protected void initSensorSystem(){
	    	//doing nothing in a QActor
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
	
