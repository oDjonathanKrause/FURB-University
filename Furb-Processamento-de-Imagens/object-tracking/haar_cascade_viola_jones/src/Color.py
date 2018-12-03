from math import sqrt

class Color:
    def __init__(self, r, g, b):
        self.r = r
        self.g = g
        self.b = b

    def dist(self, another_color):
        r = (self.r - another_color.r) * (self.r - another_color.r)
        g = (self.g - another_color.g) * (self.g - another_color.g)
        b = (self.b - another_color.b) * (self.b - another_color.b)
        return sqrt(r + g + b)

    def to_string(self):
        return str(self.r) + ', ' + str(self.g) + ', ' + str(self.b)