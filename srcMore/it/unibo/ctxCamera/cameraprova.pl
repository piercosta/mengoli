%====================================================================================
% Context ctxCamera  SYSTEM-configuration: file it.unibo.ctxCamera.cameraprova.pl 
%====================================================================================
context(ctxcamera, "localhost",  "TCP", "8037" ).  		 
%%% -------------------------------------------
qactor( camera , ctxcamera, "it.unibo.camera.MsgHandle_Camera"   ). %%store msgs 
qactor( camera_ctrl , ctxcamera, "it.unibo.camera.Camera"   ). %%control-driven 
%%% -------------------------------------------
%%% -------------------------------------------

