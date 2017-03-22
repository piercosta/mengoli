%====================================================================================
% Context ctxDomainModelRobot  SYSTEM-configuration: file it.unibo.ctxDomainModelRobot.mengoli.pl 
%====================================================================================
context(ctxdomainmodelrobot, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
%%% -------------------------------------------
eventhandler(evh,ctxdomainmodelrobot,"it.unibo.ctxDomainModelRobot.Evh","start,stop,takepicture,obstacle").  
%%% -------------------------------------------
qactor( robot , ctxdomainmodelrobot, "it.unibo.robot.MsgHandle_Robot" ). 
qactor( robot_ctrl , ctxdomainmodelrobot, "it.unibo.robot.Robot" ). 

