import cv2
import numpy as np

###################

# 1. Aquisição da imagem. 0 = cinza
img = cv2.imread("../dataset/1_1.png", 0)

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
#holt_1 = np.array([[0, 1, x], [1, 1, x], [x, x, 0]])
#holt_2 = np.array([[x, 1, 0], [x, 1, 1], [0, x, x]])
#holt_3 = np.array([[0, x, x], [x, 1, 1], [x, 1, 0]])
#holt_4 = np.array([[x, x, 0], [1, 1, x], [0, 1, x]])

zhang_suen = thinning(otsu)

# Amostra as ibagens
cv2.imshow('img', img)
cv2.imshow('mediana', mediana)
cv2.imshow('sharp', sharp)
cv2.imshow('filtro otsu', otsu)
cv2.imshow('zhang suen', zhang_suen)

cv2.waitKey(0)
cv2.destroyAllWindows()

