import cv2
import numpy as np

class CrossingNumber:
	def crossingNumber(self, img):
		rows, cols = img.shape
		endpoints = []
		branches = []

		for r in range(rows):
			for c in range (cols):
				total = 0

				# x n n
				# n n n
				# n n n
				if r > 0 and c > 0 and img[r - 1, c - 1] == 0:
					total += 1

				# n x n
				# n n n
				# n n n
				if r > 0 and img[r - 1, c] == 0:
					total += 1

				# n n x
				# n n n
				# n n n
				if r > 0 and c < cols-1 and img[r - 1, c + 1] == 0:
					total += 1

				# n n n
				# x n n
				# n n n
				if c > 0 and img[r, c - 1] == 0:
					total += 1

				# n n n
				# n n x
				# n n n
				if c < cols - 1 and img[r, c + 1] == 0:
					total += 1

				# n n n
				# n n n
				# x n n
				if r < (rows - 1) and c > 0 and img[r + 1, c - 1] == 0:
					total += 1

				# n n n
				# n n n
				# n x n
				if r < (rows - 1) and img[r + 1, c] == 0:
					total += 1

				# n n n
				# n n n
				# n n x
				if r < (rows - 1) and c < (cols - 1) and img[r + 1, c + 1] == 0:
					total += 1

				# crista final
				if total == 1:
					if img[r, c] == 0:
						endpoints.append([r, c])

				# crista bifurcada
				if total == 3:
					if img[r, c] == 0:
						branches.append([r, c])
		return endpoints, branches

"""
img = cv2.imread("../dataset/holt.jpg", 0)
endpoints, branches = crossingNumber(img)

for i in range(len(endpoints)):
	endpoint = endpoints[i]
	y = endpoint[0]
	x = endpoint[1]
	cv2.circle(img, (x, y), 5, (0, 0, 255))

for i in range(len(branches)):
	branch = branches[i]
	y = branch[0]
	x = branch[1]
	cv2.circle(img, (x, y), 5, (0, 0, 255))

cv2.imshow('resultado', img)
cv2.waitKey(0)
cv2.destroyAllWindows()
"""
