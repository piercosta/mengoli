%====================================================================================
% Context ctxControl  SYSTEM-configuration: file it.unibo.ctxControl.mengoli.pl 
%====================================================================================
context(ctxcontrol, "localhost",  "TCP", "8070" ).  		 
%%% -------------------------------------------
qactor( logic_controller , ctxcontrol, "it.unibo.logic_controller.MsgHandle_Logic_controller"   ). %%store msgs 
qactor( logic_controller_ctrl , ctxcontrol, "it.unibo.logic_controller.Logic_controller"   ). %%control-driven 
qactor( gui_controller , ctxcontrol, "it.unibo.gui_controller.MsgHandle_Gui_controller"   ). %%store msgs 
qactor( gui_controller_ctrl , ctxcontrol, "it.unibo.gui_controller.Gui_controller"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

