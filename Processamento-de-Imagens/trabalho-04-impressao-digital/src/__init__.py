import cv2
import glob
import os
import numpy as np
from Holt import Holt
from CrossingNumber import CrossingNumber
from Fingerprint import Fingerprint

###################

images = []
target = None
# 1. Aquisição da imagem
for file in glob.glob("../dataset_test/*.png"): # ALTERAR AQUI O CAMINNO DA PASTA COM AS IMAGENS
    filename = os.path.basename(file)

    if filename == 'target.png':
        target = Fingerprint(cv2.imread(file, 0))
        images.append(target)
    else:
        img = Fingerprint(cv2.imread(file, 0))
        images.append(img)

print(len(images))
control_index = 0

for image in images:
    # 2. Filtro da mediana
    mediana = cv2.medianBlur(image.img, 5)

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
    holt_resultado = cv2.bitwise_not(holt_resultado)

    # 6. Detectar cristas finais e bifurcações
    crossingNumber = CrossingNumber()
    crossing_number_resultado = holt_resultado.copy()
    h, w = crossing_number_resultado.shape
    w_limit = round(w * 0.10)  # deixa uma borda de 10% da imagem
    h_limit = round(h * 0.10)
    crossing_number_resultado = crossing_number_resultado[h_limit:h - h_limit, w_limit:w - w_limit]
    endpoints, branches = crossingNumber.crossing_number(crossing_number_resultado)
    endpoints, branches = crossingNumber.post_processing(crossing_number_resultado, endpoints, branches)

    # updeita as informações da imagem
    image.img = crossing_number_resultado
    image.endpoints = endpoints
    image.branches = branches

for image in images:
    # Em cada imagem contabilizar a quantidade de minúcias existentes
    features = len(image.endpoints) + len(image.branches)

    # pg 43 do artigo
    img_endpoints = image.draw_endpoints()
    cv2.imshow(str(control_index), img_endpoints)
    control_index += 1

cv2.waitKey(0)
cv2.destroyAllWindows()