%====================================================================================
% Context ctxSensorEmitter  SYSTEM-configuration: file it.unibo.ctxSensorEmitter.mengoli.pl 
%====================================================================================
context(ctxradar, "localhost",  "TCP", "8033" ).  		 
context(ctxsensoremitter, "localhost",  "TCP", "8133" ).  		 
context(ctxconsole, "localhost",  "TCP", "8039" ).  		 
context(ctxcontrol, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( logic_controller , ctxcontrol, "it.unibo.logic_controller.MsgHandle_Logic_controller"   ). %%store msgs 
qactor( logic_controller_ctrl , ctxcontrol, "it.unibo.logic_controller.Logic_controller"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

