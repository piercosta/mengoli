%====================================================================================
% Context ctxModel  SYSTEM-configuration: file it.unibo.ctxModel.mengoli.pl 
%====================================================================================
context(ctxmodel, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( control , ctxmodel, "it.unibo.control.MsgHandle_Control"   ). %%store msgs 
qactor( control_ctrl , ctxmodel, "it.unibo.control.Control"   ). %%control-driven 
qactor( sonar , ctxmodel, "it.unibo.sonar.MsgHandle_Sonar"   ). %%store msgs 
qactor( sonar_ctrl , ctxmodel, "it.unibo.sonar.Sonar"   ). %%control-driven 
qactor( robot , ctxmodel, "it.unibo.robot.MsgHandle_Robot"   ). %%store msgs 
qactor( robot_ctrl , ctxmodel, "it.unibo.robot.Robot"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxmodel,"it.unibo.ctxModel.Evh","start,stop,takepicture,obstacle").  
eventhandler(control_evh,ctxmodel,"it.unibo.ctxModel.Control_evh","numOfSonars,sonar").  
%%% -------------------------------------------

