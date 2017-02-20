%====================================================================================
% Context ctxControl  SYSTEM-configuration: file it.unibo.ctxControl.mengoli.pl 
%====================================================================================
context(ctxcontrol, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( control_logic , ctxcontrol, "it.unibo.control_logic.MsgHandle_Control_logic"   ). %%store msgs 
qactor( control_logic_ctrl , ctxcontrol, "it.unibo.control_logic.Control_logic"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

