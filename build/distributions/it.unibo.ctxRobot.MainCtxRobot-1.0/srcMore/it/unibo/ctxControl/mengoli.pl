%====================================================================================
% Context ctxControl  SYSTEM-configuration: file it.unibo.ctxControl.mengoli.pl 
%====================================================================================
context(ctxcontrol, "192.168.43.238",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( logic_controller , ctxcontrol, "it.unibo.logic_controller.MsgHandle_Logic_controller"   ). %%store msgs 
qactor( logic_controller_ctrl , ctxcontrol, "it.unibo.logic_controller.Logic_controller"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh_control,ctxcontrol,"it.unibo.ctxControl.Evh_control","numOfSonars,sonar,obstacle,start,stopControl,startControl").  
%%% -------------------------------------------

