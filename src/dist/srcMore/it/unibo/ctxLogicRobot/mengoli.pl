%====================================================================================
% Context ctxLogicRobot  SYSTEM-configuration: file it.unibo.ctxLogicRobot.mengoli.pl 
%====================================================================================
context(ctxlogicrobot, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( led , ctxlogicrobot, "it.unibo.led.MsgHandle_Led"   ). %%store msgs 
qactor( led_ctrl , ctxlogicrobot, "it.unibo.led.Led"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh_logicrobot,ctxlogicrobot,"it.unibo.ctxLogicRobot.Evh_logicrobot","start").  
%%% -------------------------------------------
qactor( robot , ctxlogicrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxlogicrobot, "it.unibo.robot.Robot" ). 

