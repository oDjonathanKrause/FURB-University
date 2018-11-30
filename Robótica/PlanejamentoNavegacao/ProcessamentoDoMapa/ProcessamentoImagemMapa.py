import numpy as np
import cv2
import math

# Define o tamanho da matriz quadrada
TAMANHO_MATRIZ = 7

# Carrega imagem e transforma em escala de cinza
img = cv2.imread('mapa.jpg')
#img = cv2.imread('teste01.jpg')
img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

# Define quantos pixels cada bloco terá
height, width = img.shape
tamanho_bloco_x = math.floor(width / TAMANHO_MATRIZ)
tamanho_bloco_y = math.floor(height / TAMANHO_MATRIZ)

print('y total: ' + str(height) + '   x total: ' + str(width))
print('tamanho bloco y: ' + str(tamanho_bloco_y) + '   tamanho bloco x: ' + str(tamanho_bloco_x))

mapa = []
#mapa_2d = np.zeros(shape=(TAMANHO_MATRIZ, TAMANHO_MATRIZ), dtype=object)
mapa_2d = [[] for _ in range(TAMANHO_MATRIZ)]
mapa_final = np.zeros(shape=(TAMANHO_MATRIZ, TAMANHO_MATRIZ)).astype(int)

x_atual = 0
y_atual = 0

# Corta a imagem em n blocos. O resultado será um mapa[] com n blocos com pedaços da imagem original
for i in range(0, TAMANHO_MATRIZ):
    for j in range(0, TAMANHO_MATRIZ):
        imagem_cortada = img[y_atual:y_atual + tamanho_bloco_y, x_atual:x_atual + tamanho_bloco_x]
        mapa_2d[i].append(imagem_cortada)
        y_atual += tamanho_bloco_y
    x_atual += tamanho_bloco_x
    y_atual = 0

# Monta a matriz final onde 1 significa obstaculo e 0 livre
for i in range(len(mapa_2d)):
    for j in range(len(mapa_2d)):
        ret, thresh = cv2.threshold(mapa_2d[i][j], 100, 255, 0)
        #cv2.imshow(str(i) + " - " + str(j), mapa_2d[i][j])

        # Vetifica a quantidade de pixels pretos e brancos na imagem, no caso um bloco da imagem original
        pixels_brancos = np.sum(thresh == 255)
        pixels_pretos = np.sum(thresh == 0)
        porcentagem_pixel_preto = (pixels_pretos / (pixels_brancos + pixels_pretos)) * 100


        # Se a maior parte da imagem for preto, esse bloco eh um caminho livre, senao eh um obstaculo
        if porcentagem_pixel_preto >= 10:
            mapa_final[j][i] = int(-1)
            #cv2.imshow(str(i) + " - " + str(j), mapa_2d[i][j])
        else:
            mapa_final[j][i] = int(0)

for i in range(len(mapa_final)):
    print(str(mapa_final[i]))

cv2.imshow('mapa',img)
cv2.waitKey(0)
cv2.destroyAllWindows()

