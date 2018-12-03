#Import necessary libraries
import cv2
from darkflow.darkflow.net.build import TFNet
import numpy
import time

#threshold specifies the confidence needed to create a box, model specifies the location, load is to setup the weights,  gpu utilizes the graphics card
options = {'threshold': 0.2,'model': 'tiny/yolov2.cfg','load': 'bin/yolov2.weights','gpu': 1.0}

#This initializes the model to make predictions
network = TFNet(options)

#Create a list of random colours for variation of box colours; it will be an array that is 3 elements long
randcolour = [tuple(numpy.random.rand(3)*255) for i in range(10)]

#Specify the webcam as the camera being used (0 is the default camera)
capt = cv2.VideoCapture(0)

#Set the frame size to the full 1080p
capt.set(cv2.CAP_PROP_FRAME_WIDTH, 1920)
capt.set(cv2.CAP_PROP_FRAME_HEIGHT, 1080)


while True:
    #Time how long each frame takes
    start = time.time()
    #Read from capture device
    ret, frame = capt.read()
    #This line will pass in the frame and make the prediction
    results = network.return_predict(frame)
    #This loop will run while the capture device is running
    if ret:
        #This will use the random colour and display the prediction as well as the confidence level
        for color, result in zip(randcolour, results):
            #Takes the top left and bottom right coordinates
            coord1 = (result['topleft']['x'], result['topleft']['y'])
            coord2 = (result['bottomright']['x'], result['bottomright']['y'])
            label = result['label']
            conf = result['confidence']
            text = '{}: {:.0f}%'.format(label, conf * 100)
            frame = cv2.rectangle(frame, coord1, coord2, color, 7)
            frame = cv2.putText(
                frame, text, coord1, cv2.FONT_HERSHEY_SIMPLEX, 1, (0, 0, 0), 2)
        #Settings for frame window
        cv2.imshow('frame', frame)
        #Print out the frames per second with 1 decimal
        print('FPS {:.1f}'.format(1 / (time.time() - start)))

    #The e key will exit the window
    if cv2.waitKey(1) & 0xFF == ord('e'):
        break

#Release capture device
capt.release()
#Exits the windows
cv2.destroyAllWindows()