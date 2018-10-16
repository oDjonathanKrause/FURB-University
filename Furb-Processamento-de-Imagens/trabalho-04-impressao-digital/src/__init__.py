import cv2
import numpy as np
from Holt import Holt
#from CrossingNumber import CrossingNumber

###################

# 1. Aquisição da imagem. 0 = cinza
img = cv2.imread("../dataset/img_artigo.jpg", 0)

# 2. Filtro da mediana
mediana = cv2.medianBlur(img, 5)

# 3. Filtro de aguçamento
filtro = np.array([[-1, -1, -1, -1, -1],
                   [-1,  2,  2,  2, -1],
                   [-1,  2,  3,  2, -1],
                   [-1,  2,  2,  2, -1],
                   [-1, -1, -1, -1, -1]])
sharp = cv2.filter2D(mediana, -1, filtro)

# 4. Threshould do Otsu
ret, otsu = cv2.threshold(sharp, 0, 255, cv2.THRESH_OTSU)

# 5. Afinamento método de Holt
holt = Holt()
holt_resultado = holt.apply(otsu)
holt_resultado= cv2.bitwise_not(holt_resultado)

# 6. Crossing Number. Detectar cristas finais e bifurcações
# 6.1 Detectar cristas finais


# Amostra as ibagens
cv2.imshow('img', img)
cv2.imshow('mediana', mediana)
cv2.imshow('sharp', sharp)
cv2.imshow('filtro otsu', otsu)
cv2.imshow('holt', holt_resultado)


cv2.waitKey(0)
cv2.destroyAllWindows()

