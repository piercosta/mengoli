%====================================================================================
% Context ctxConsole  SYSTEM-configuration: file it.unibo.ctxConsole.mengoli.pl 
%====================================================================================
context(ctxcontrol, "192.168.43.238",  "TCP", "8070" ).  		 
context(ctxconsole, "192.168.43.238",  "TCP", "8039" ).  		 
%%% -------------------------------------------
qactor( console , ctxconsole, "it.unibo.console.MsgHandle_Console"   ). %%store msgs 
qactor( console_ctrl , ctxconsole, "it.unibo.console.Console"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxconsole,"it.unibo.ctxConsole.Evh","startControl,stopControl,mqttmsg").  
%%% -------------------------------------------

