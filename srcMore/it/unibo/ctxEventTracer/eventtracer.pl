%====================================================================================
% Context ctxEventTracer  SYSTEM-configuration: file it.unibo.ctxEventTracer.eventTracer.pl 
%====================================================================================
context(ctxeventtracer, "localhost",  "TCP", "8027" ).  		 
%%% -------------------------------------------
qactor( qaevtracer , ctxeventtracer, "it.unibo.qaevtracer.MsgHandle_Qaevtracer"   ). %%store msgs 
qactor( qaevtracer_ctrl , ctxeventtracer, "it.unibo.qaevtracer.Qaevtracer"   ). %%control-driven 
%%% -------------------------------------------
eventhandler(evh,ctxeventtracer,"it.unibo.ctxEventTracer.Evh","sonar").  
%%% -------------------------------------------

