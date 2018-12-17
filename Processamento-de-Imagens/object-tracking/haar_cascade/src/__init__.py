import cv2, sys

"""
    1. O arquivo setup.py faz o download das imagens negativas e cria os diretórios necessários
    2. No arquivo commands.txt estão os comandos utilizados para a fase de treinamento
    
    Processamento de Imagens. BCC FURB 2018/2
    Djonathan Krause, Rafael Rossi.
"""

# Parametros
DIR_CASCADE = '../data/hand_cascade.xml'
MIN_SIZE = 50           # 50
SCALE_FACTOR = 1.1      # 1.1
MIN_NEIGHBORS = 3       # 5

def track():
    # Carrega o arquivo com o cascade
    cascade = cv2.CascadeClassifier(DIR_CASCADE)

    # Inicia a captura de video
    video_capture = cv2.VideoCapture(0)

    while True:
        # Captura frame por frame do vídeo e converte para cinza
        retval, frame = video_capture.read()
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        # Detecta caracteristicas especificadas no Haar Cascade
        objects = cascade.detectMultiScale(gray, scaleFactor=SCALE_FACTOR, minNeighbors=MIN_NEIGHBORS, minSize=(MIN_SIZE, MIN_SIZE))

        # Desenha retangulo no objeto
        for (x, y, w, h) in objects:
            cv2.rectangle(frame, (x, y), (x+w, y+h), (50, 50, 200), 2)

        # Mostra video
        cv2.imshow('Video', frame)

        # q pra sair
        if cv2.waitKey(1) & 0xFF == ord('q'):
           sys.exit()

track()