import sys
import cv2
import numpy as np

# Carrega imagens
chuvoso = cv2.imread("imagens/chuvoso.png")
seca = cv2.imread("imagens/seca.png")

# Imagem negativa em tons de cinza
chuvoso = cv2.cvtColor(chuvoso, cv2.COLOR_BGR2GRAY)
seca = cv2.cvtColor(seca, cv2.COLOR_BGR2GRAY)

# Faz inversão
chuvoso = cv2.bitwise_not(chuvoso)
seca = cv2.bitwise_not(seca)

# Binarização
retval_chuvoso, chuvoso = cv2.threshold(chuvoso, 200, 255, cv2.THRESH_BINARY)
retval_seca, seca = cv2.threshold(seca, 110, 255, cv2.THRESH_BINARY)

# Faz imagens ficarem do mesmo tamanho para subtrair
chuvoso = cv2.resize(chuvoso, (380, 200)).astype(np.float32)
seca = cv2.resize(seca, (380, 200)).astype(np.float32)

# Subtrai as imagens
subtracao = cv2.subtract(chuvoso, seca)

# Mostra imagem
cv2.imshow('chuvoso', chuvoso)
cv2.imshow('seca', seca)
cv2.imshow('subtracao', subtracao)

# Aguarda input para fechar janela
cv2.waitKey(0)
cv2.destroyAllWindows()