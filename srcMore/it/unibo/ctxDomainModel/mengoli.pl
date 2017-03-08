%====================================================================================
% Context ctxDomainModel  SYSTEM-configuration: file it.unibo.ctxDomainModel.mengoli.pl 
%====================================================================================
context(ctxdomainmodel, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( control , ctxdomainmodel, "it.unibo.control.MsgHandle_Control"   ). %%store msgs 
qactor( control_ctrl , ctxdomainmodel, "it.unibo.control.Control"   ). %%control-driven 
qactor( sonar , ctxdomainmodel, "it.unibo.sonar.MsgHandle_Sonar"   ). %%store msgs 
qactor( sonar_ctrl , ctxdomainmodel, "it.unibo.sonar.Sonar"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxdomainmodel,"it.unibo.ctxDomainModel.Evh","start,stop,takepicture,obstacle").  
eventhandler(control_evh,ctxdomainmodel,"it.unibo.ctxDomainModel.Control_evh","numOfSonars").  
%%% -------------------------------------------
qactor( robot , ctxdomainmodel, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxdomainmodel, "it.unibo.robot.Robot" ). 

