import base64
import os
from picamera import PiCamera
from time import sleep
from sys import exit
camera = PiCamera()

sleep(1)
camera.rotation = 180
camera.capture('photo.jpg')
#	with open('C:/Users/Utente/workspace/it.unibo.provacamera/photo.jpg', 'rb') as imageFile: 
#with open('photo.jpg', 'rb') as imageFile: 
#	str = base64.b64encode(imageFile.read()) 
#	print (str)
#	camera.stop_preview()
exit(0)
