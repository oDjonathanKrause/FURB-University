from cv2 import cv2
import numpy as np
import cv2

"""
    Djonathan Krause, Rafael Rossi.
    Processamento de Imagens - FURB 2018/2
"""

# Carrega imagens
chuvoso = cv2.imread('imagens/chuvoso.png', 1)
seca = cv2.imread('imagens/seca.png', 1)

# Escala de cinza
chuvoso = cv2.cvtColor(chuvoso, cv2.COLOR_RGB2GRAY)
seca = cv2.cvtColor(seca, cv2.COLOR_RGB2GRAY)

# Inverte
chuvoso = cv2.bitwise_not(chuvoso)
seca = cv2.bitwise_not(seca)

# Adição
chuvoso = cv2.add(chuvoso, 40)
seca = cv2.add(seca, -50)

# binarizar 190 chuvoso, 110 estiagem
r, chuvoso = cv2.threshold(chuvoso, 210, 255, cv2.THRESH_BINARY)
r2, seca = cv2.threshold(seca, 73, 255, cv2.THRESH_BINARY)

# abertura
abertura = np.ones((2, 2), np.int8)
chuvoso = cv2.morphologyEx(chuvoso, cv2.MORPH_OPEN, abertura)
seca = cv2.morphologyEx(seca, cv2.MORPH_OPEN, abertura)

# subtração
chuvoso = cv2.resize(chuvoso, (385, 200))
seca = cv2.resize(seca, (385, 200))
resSub = cv2.subtract(chuvoso, seca)
resSub = cv2.resize(resSub, (385, 200))

cv2.imshow('Resultado Subtracao', resSub)

cv2.imshow('SECA', seca)

# Aguarda input para fechar janela
cv2.waitKey(0)
cv2.destroyAllWindows()