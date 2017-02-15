%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.mengoli.pl 
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( led , ctxrobot, "it.unibo.led.MsgHandle_Led"   ). %%store msgs 
qactor( led_ctrl , ctxrobot, "it.unibo.led.Led"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxrobot,"it.unibo.ctxRobot.Evh","endmove,obstacle").  
%%% -------------------------------------------
qactor( robot , ctxrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxrobot, "it.unibo.robot.Robot" ). 

