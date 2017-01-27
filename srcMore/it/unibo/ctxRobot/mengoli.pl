%====================================================================================
% Context ctxRobot  SYSTEM-configuration: file it.unibo.ctxRobot.mengoli.pl 
%====================================================================================
context(ctxrobot, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
%%% -------------------------------------------
%%% -------------------------------------------
qactor( robot , ctxrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxrobot, "it.unibo.robot.Robot" ). 

