from cv2 import cv2
import numpy as np
import cv2
import glob

# Carrega imagens
todas_iris = [cv2.imread(file) for file in glob.glob("imagens/*.jpg")]

img = todas_iris[0]
img_cinza = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
img_cinza = cv2.GaussianBlur(img_cinza, (5, 5), 0)

iris = cv2.HoughCircles(img_cinza, cv2.HOUGH_GRADIENT, 1, 20, param1=50, param2=30, minRadius=180, maxRadius=190)
pupila = cv2.HoughCircles(img_cinza, cv2.HOUGH_GRADIENT, 1.1, 20, param1=10, param2=50, minRadius=30, maxRadius=50)

height,width, z = img.shape
mask = np.zeros((height,width), np.uint8)

# Desenha circulo da iris
for i in iris[0, :]:
    cv2.circle(img, (i[0], i[1]), i[2], (0, 0, 0), 2)
    cv2.circle(mask, (i[0], i[1]), i[2], (255, 255, 255), thickness=-1)

# Desenha circulos da pupila
for i in pupila[0, :]:
    cv2.circle(img, (i[0], i[1]), 2, (0, 0, 0), 100)

# Aplica threshold
_,thresh = cv2.threshold(mask,1,255,cv2.THRESH_BINARY)
masked_data = cv2.bitwise_and(img, img, mask=mask)

# Encontra contornos
contours = cv2.findContours(thresh,cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_SIMPLE)
x,y,w,h = cv2.boundingRect(contours[0])

# Corta imagem conforme a mascara
img_final = masked_data[y:y+h,x:x+w]

cv2.imshow('olho',img_final)
cv2.waitKey(0)
cv2.destroyAllWindows()