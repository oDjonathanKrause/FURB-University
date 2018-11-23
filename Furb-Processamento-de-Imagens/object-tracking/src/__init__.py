import cv2
import numpy as np
from Color import Color
from Point import Point

# Valores iniciais
TRACKING_COLOR = Color(0, 255, 0)
THRESHOLD = 200
actual_pos = Point(0, 0)

# Cria captura de video
cap = cv2.VideoCapture(0)
cap.set(cv2.CAP_PROP_FRAME_WIDTH, 300)
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 100)

while(True):
    # Captura cada frame
    ret, frame = cap.read()

    # determina o tamanho da imagem
    height, width, o = frame.shape
    record = 500
    count = 0
    avgX = 0
    avgY = 0

    # faz um loop para cada pixel da imagem pulando de n em n
    step_size = 10
    for x in range(0, width - step_size, step_size):
        for y in range(0, height - step_size, step_size):
            # Determina os valores RGB atuais com base no array BGR da imagem/frame
            current_r = frame[y][x][2]
            current_g = frame[y][x][1]
            current_b = frame[y][x][0]

            # Cria a cor atual e calcula a distância euclidiana entre ela e a cor procurada
            current_color = Color(current_r, current_g, current_b)
            d = current_color.dist(TRACKING_COLOR)

            if d < THRESHOLD:
                avgX += x
                avgY += y
                count += 1

            # Se a distancia atual for menor do que a menor já conhecida, atualiza
            """
            if d < record:
                record = d
                actual_pos.x = x
                actual_pos.y = y
            """
    #print(str(actual_pos) + str(record))

    if count > 0:
        actual_pos.x = int(avgX / count)
        actual_pos.y = int(avgY / count)

    # Desenha um circulo na posicao da cor encontrada
    cv2.circle(frame, (actual_pos.x, actual_pos.y), 10, (255, 255, 0))

    # Mostra o video, 1 ou q finaliza
    cv2.imshow('frame', frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# Finaliza o app
cap.release()
cv2.destroyAllWindows()
