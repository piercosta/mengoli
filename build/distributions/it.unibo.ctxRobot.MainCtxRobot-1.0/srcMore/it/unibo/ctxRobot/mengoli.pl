%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.mengoli.pl 
%====================================================================================
context(ctxcontrol, "192.168.43.238",  "TCP", "8070" ).  		 
context(ctxrobot, "192.168.43.121",  "TCP", "8090" ).  		 
%%% -------------------------------------------
qactor( camera , ctxrobot, "it.unibo.camera.MsgHandle_Camera"   ). %%store msgs 
qactor( camera_ctrl , ctxrobot, "it.unibo.camera.Camera"   ). %%control-driven 
qactor( sonarobstacle , ctxrobot, "it.unibo.sonarobstacle.MsgHandle_Sonarobstacle"   ). %%store msgs 
qactor( sonarobstacle_ctrl , ctxrobot, "it.unibo.sonarobstacle.Sonarobstacle"   ). %%control-driven 
qactor( led , ctxrobot, "it.unibo.led.MsgHandle_Led"   ). %%store msgs 
qactor( led_ctrl , ctxrobot, "it.unibo.led.Led"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxrobot,"it.unibo.ctxRobot.Evh","start,stop,takepicturedone").  
%%% -------------------------------------------
qactor( robot , ctxrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxrobot, "it.unibo.robot.Robot" ). 

