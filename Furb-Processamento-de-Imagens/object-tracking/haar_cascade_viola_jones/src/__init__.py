import cv2, sys, os
from random import randint
from ObjectDetected import ObjectDetected
from Color import Color

"""
    1. O arquivo setup.py faz o download das imagens negativas e cria os diretórios necessários
    2. No arquivo commands.txt estão os comandos utilizados para a fase de treinamento
    
    Processamento de Imagens. BCC FURB 2018/2
    Djonathan Krause, Rafael Rossi.
"""

DIR_CASCADE = '../data/cascade.xml'
MIN_SIZE = 50           # 50
SCALE_FACTOR = 1.1      # 1.1
MIN_NEIGHBORS = 5       # 5

def verify_color(color):
    DINO_COLOR = Color(23, 23, 23)
    if color.r > 20 and color.r < 45:
        if color.g > 28 and color.r < 50:
            if color.b > 30 and color.b < 51:
                return True
    #return DINO_COLOR.dist(color) < 50

def track():
    cascade = cv2.CascadeClassifier(DIR_CASCADE)
    video_capture = cv2.VideoCapture(0)

    while True:
        # Captura frame por frame do vídeo e converte para cinza
        retval, frame = video_capture.read()
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        # Detecta caracteristicas especificadas no Haar Cascade
        objects = cascade.detectMultiScale(gray, scaleFactor=SCALE_FACTOR, minNeighbors=MIN_NEIGHBORS, minSize=(MIN_SIZE, MIN_SIZE))

        # Desenha retangulo no objeto
        for (x, y, w, h) in objects:
            r = frame[y][x][2]
            g = frame[y][x][1]
            b = frame[y][x][0]
            actual_color = Color(r, g, b)
            if verify_color(actual_color):
                print(actual_color.to_string())
                cv2.rectangle(frame, (x, y), (x+w, y+h), (50, 50, 200), 2)

        # Mostra video
        cv2.imshow('Video', frame)

        # q pra sair
        if cv2.waitKey(1) & 0xFF == ord('q'):
           sys.exit()


track()