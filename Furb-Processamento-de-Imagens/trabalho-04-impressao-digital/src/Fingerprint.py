import cv2

class Fingerprint(object):
    def __init__(self, img):
        self.raw = img
        self.img = img
        self.endpoints = None
        self.branches = None

    def draw_endpoints(self):
        img_endpoints = self.img.copy()
        if len(self.endpoints) > 0:
            for endpoint in self.endpoints:
                x = endpoint[1]
                y = endpoint[0]
                cv2.circle(img_endpoints, (x, y), 5, (0, 0, 255))
        return img_endpoints
