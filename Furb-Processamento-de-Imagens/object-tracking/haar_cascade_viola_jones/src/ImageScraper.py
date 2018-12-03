import cv2
import numpy as np
import urllib.request
import os
import glob

# consts
NEG_IMGS_PATH = '../neg'
FAILED_IMGS_PATH = '../failed'
DATA_PATH = '../data'
INFO_PATH = '../info'
NEG_IMG_SIZE = 100
POS_IMG_SIZE = 50

class ImageScraper:
    def store_raw_imgs(self, imgs_link, max_img_count):
        # Verifica se as pastas necessárias existem
        self.create_dirs()

        # Decoda as urls
        imgs_urls = urllib.request.urlopen(imgs_link).read().decode()

        # Verifica quantas imagens a pasta tem pra setar o contador
        path, dirs, files = next(os.walk('./' + NEG_IMGS_PATH))
        img_count = len(files)

        # Percorre url por url da lista
        print('Salvando imagens...')
        for img_url in imgs_urls.split('\n'):
            try:
                print('Imagem ' + str(img_count) + ' de ' + str(max_img_count))
                # Determina o nome do arquivo que será salvo
                img_path = NEG_IMGS_PATH + '/' + str(img_count) + '.jpg'

                # Salva a imagem de cada url da lista na pasta de imagens
                urllib.request.urlretrieve(img_url, img_path)

                # Carrega a imagem que foi salva em escala de cinza
                img = cv2.imread(img_path, cv2.IMREAD_GRAYSCALE)

                # Determina o tamanho da imagem para o resize
                img_size = NEG_IMG_SIZE

                # Efetua o resize da imagem e salva
                resized_img = cv2.resize(img, (img_size, img_size))
                cv2.imwrite(img_path, resized_img)

                img_count += 1
                if img_count >= max_img_count:
                    return
            except Exception as e:
                print(str(e))

    def remove_failed_imgs(self):
        for file_type in [NEG_IMGS_PATH]:
            print('Procurando imagens com erro em ' + str(file_type))
            for img in os.listdir(file_type):
                for failed in os.listdir(FAILED_IMGS_PATH):
                    try:
                        # Carrega a imagem atual e o exemplos de images com erro
                        current_image_path = str(file_type) + '/' + str(img)
                        current_img = cv2.imread(current_image_path)
                        failed_sample = cv2.imread(FAILED_IMGS_PATH + '/' + str(failed))

                        # Se a imagem atual for igual o exemplo com erro, remove ela da pasta
                        if failed_sample.shape == current_img.shape \
                                and not (np.bitwise_xor(failed_sample, current_img).any()):
                            print('Image ' + current_image_path + 'is failed')
                            os.remove(current_image_path)
                    except Exception as e:
                        print(str(e))

    def create_path_info(self):
        print('Criando arquivos de dados de diretório...')
        for img in os.listdir('../pos'):
            line = 'pos/' + img + ' 1 0 0 ' + str(POS_IMG_SIZE) + ' ' + str(POS_IMG_SIZE) + '\n'
            with open('../pos_dir_info.dat', 'a') as file:
                file.write(line)
        for img in os.listdir('../neg'):
            line = 'neg/' + img + '\n'
            with open('../bg_dir_info.txt', 'a') as file:
                file.write(line)

    def restart(self):
        files = ['../bg_dir_info.txt', '../pos_dir_info.dat', '../positives.vec']
        folders = ['../data', '../info', '../neg']

        for file in files:
            if os.path.exists(file):
                os.remove(file)

        for folder in folders:
            if os.path.exists(folder):
                files = glob.glob(folder + '/*')
                for f in files:
                    os.remove(f)

        self.create_dirs()

    def create_dirs(self):
        print('Verificando diretórios...')
        # Cria a pasta de imagens se ela não existir
        if not os.path.exists(NEG_IMGS_PATH):
            os.makedirs(NEG_IMGS_PATH)
        if not os.path.exists(DATA_PATH):
            os.makedirs(DATA_PATH)
        if not os.path.exists(INFO_PATH):
            os.makedirs(INFO_PATH)