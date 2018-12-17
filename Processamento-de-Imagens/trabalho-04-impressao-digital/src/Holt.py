import numpy as np
import cv2

__author__ = "Djonathan Krause"
__credits__ = "Baseado na implementação Java https://github.com/marinalaux/PDI"
__email__ = "odjonathankrause@gmail.com"
__license__ = "MIT"

class Holt:
    def apply(self, img):
        result = img.copy()
        process = img.copy()
        odd = False

        # linha/y/h, colunas/x/w
        height, width = process.shape
        for x in range(0, width-1):
            for y in range(0, height-1):
                if self.v(process[y][x]):
                    neighborhood = self.getNeighborhood(x, y, process)
                    value = self.pixelValue(neighborhood, odd)
                    result[y][x] = value
            process[y][x] = result[y][x]
        return result
    
    def getNeighborhood(self, x, y, img):
        neighbor = np.zeros((3,3))
        for i in range(1, 3):
            for j in range(1, 3):
                x2 = x + i - 1
                y2 = y + j - 1
                neighbor[i][j] = img[y2][x2]
        return neighbor
    
    def pixelValue(self, pixels, odd):
        neighborhood = self.neighborhoodToArray(pixels)
        if not self.isEdge(neighborhood):
            return pixels[1][1]
    
        n = pixels[1][0]
        s = pixels[1][2]
        l = pixels[2][1]
        o = pixels[0][1]
    
        if odd:
            if self.v(l) and self.v(s) and (self.v(n) or self.v(o)):
                return pixels[1][1]
        else:
            if self.v(o) and self.v(n) and (self.v(s) or self.v(l)):
                return pixels[1][1]
        return 0
    
    def isEdge(self, n):
        nConn = n[0] + n[1] + n[2] + n[3] + n[4] + n[5] + n[6] + n[7]
        return (nConn >= 2 and nConn <= 6) and self.isConnected(n)
    
    def isConnected(self, n):
        connection = (1 if n[0] < n[1] else 0) + (1 if n[1] < n[2] else 0) + (1 if n[2] < n[3] else 0) + (1 if n[3] < n[4] else 0) + (1 if n[4] < n[5] else 0) + (1 if n[5] < n[6] else 0) + (1 if n[6] < n[7] else 0) + (1 if n[7] < n[0] else 0)
        return connection == 1
    
    def neighborhoodToArray(self, pixels):
        p2 = pixels[1][0] / 255
        p3 = pixels[2][0] / 255
        p4 = pixels[2][1] / 255
        p5 = pixels[2][2] / 255
        p6 = pixels[1][2] / 255
        p7 = pixels[0][2] / 255
        p8 = pixels[0][1] / 255
        p9 = pixels[0][0] / 255
    
        return [p2, p3, p4, p5, p6, p7, p8, p9]
    
    def v(self, pixel):
        return pixel == 255
