import cv2
import math

class CrossingNumber:
	def crossing_number(self, img):
		rows, cols = img.shape
		endpoints = []
		branches = []

		for r in range(rows):
			for c in range (cols):
				total = 0

				# x n n
				# n n n
				# n n n
				if r > 0 and c > 0:
					if img[r - 1, c - 1] == 0:
						total += 1

				# n x n
				# n n n
				# n n n
				if r > 0:
					if img[r - 1, c] == 0:
						total += 1

				# n n x
				# n n n
				# n n n
				if r > 0 and c < cols-1:
					if img[r - 1, c + 1] == 0:
						total += 1

				# n n n
				# x n n
				# n n n
				if c > 0:
					if img[r, c - 1] == 0:
						total += 1

				# n n n
				# n n x
				# n n n
				if c < cols - 1:
					if img[r, c + 1] == 0:
						total += 1

				# n n n
				# n n n
				# x n n
				if r < rows - 1 and c > 0:
					if img[r + 1, c - 1] == 0:
						total += 1

				# n n n
				# n n n
				# n x n
				if r < rows - 1:
					if img[r + 1, c] == 0:
						total += 1

				# n n n
				# n n n
				# n n x
				if r < rows - 1 and c < cols - 1:
					if img[r + 1, c + 1] == 0:
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

	def post_processing_endpoints(self, source, endpoints):
		mask_size = 23
		middle = math.floor(mask_size / 2)

		img = source.copy()
		h, w = img.shape

		true_endpoints = []
		for endpoint in endpoints:
			y = endpoint[0]
			x = endpoint[1]

			# evita IndexError
			if y - (middle + 1) in range(0, h) and y + (middle + 1) in range(0, h) and x - (middle + 1) in range(0,w) and x + (middle + 1) in range(0, w):
				# Cria máscara M sendo o ponto central a crista final
				mask = img[y - middle:y + middle, x - middle:x + middle]

				# Procura os pixels de M que estão conectados ao candidato crista final
				num_labels, labels = cv2.connectedComponents(mask, 4, cv2.CV_32S)
				# trata o resultado do connectedComponents
				for x in range(len(labels)):
					for y in range(len(labels)):
						if labels[x][y] > 0:
							labels[x][y] = 0
						else:
							labels[x][y] = 1

				# seta o pixel central como -1
				labels[middle][middle] = -1

				# verifica componentes conectados ao pixel central
				# verifica pixels conectados a direita
				for i in range((middle + 1), 22):
					if labels[middle, i] != 1:
						break
					else:
						labels[middle, i] = 2

				# pixels conectados a esquerda
				for i in range((middle - 1), 0, -1):
					if labels[middle, i] != 1:
						break
					else:
						labels[middle, i] = 2

				# verifica pixels conectados pra cima
				for i in range((middle + 1), 22):
					if labels[i, middle] != 1:
						break
					else:
						labels[i, middle] = 2

				# pixels conectados pra baixo
				for i in range((middle - 1), 0, -1):
					if labels[i, middle] != 1:
						break
					else:
						labels[i, middle] = 2

				# diagonal baixo direita
				for r in range((middle + 1), 22):
					for c in range((middle + 1), 22):
						if r == c:
							if labels[c, r] != 1:
								break
							else:
								labels[c, r] = 2

				# diagonal baixo esquerda
				for r in range((middle - 1), 0, -1):
					for c in range((middle + 1), 0, -1):
						if r == c:
							if labels[c, r] != 1:
								break
							else:
								labels[c, r] = 2

				# diagonal cima esquerda
				for r in range((middle - 1), 0, -1):
					for c in range((middle - 1), 0, -1):
						if r == c:
							if labels[c, r] != 1:
								break
							else:
								labels[c, r] = 2

				# diagonal cima direita
				for r in range((middle + 1), 22):
					for c in range((middle - 1), 0, -1):
						if r == c:
							if labels[c, r] != 1:
								break
							else:
								labels[c, r] = 2

				# limpa a matriz deixando o ponto central com -1 e as conexões com 1
				for x in range(len(labels)):
					for y in range(len(labels)):
						if labels[x, y] == 1:
							labels[x, y] = 0
						elif labels[x, y] == 2:
							labels[x, y] = 1

				# Contar o número de transições de 0 para 1 encontradas nas bordas
				total = 0
				for x in range(len(labels)):
					for y in range(len(labels)):
						if x == mask_size - 2:
							if labels[x, y - 1] == 1:
								labels[x, y - 1] = 2
								total += 1
						elif x == 0:
							if labels[x, y - 1] == 1:
								labels[x, y - 1] = 2
								total += 1
						elif y == mask_size - 2:
							if labels[x - 1, y] == 1:
								labels[x - 1, y] = 2
								total += 1
						elif y == 0:
							if labels[x - 1, y] == 1:
								labels[x - 1, y] = 2
								total += 1

				#  Se total for 1, então validar o candidato como uma crista final verdadeira
				if total == 1:
					true_endpoints.append(endpoint)
		return true_endpoints

	def post_processing_branches(self, source, branches):
		img = source.copy()
		h, w = img.shape
		neighbor_range = 8
		exploration_range = 23
		middle = math.floor(exploration_range/2)

		for branch in branches:
			# Fazer uma volta no sentido horário pela vizinhança 8 e rotular com 1, 2, 3,
			# respectivamente, os três componentes conexos (no formato de Y) encontrado nesta volta
			y = branch[0]
			x = branch[1]

			if y - (middle + 1) in range(0, h) \
					and y + (middle + 1) in range(0, h) \
					and x - (middle + 1) in range(0, w) \
					and x + (middle + 1) in range(0, w):
				# Cria máscara M
				mask = img[y - middle:y + middle, x - middle:x + middle]
				mask[middle, middle] = 1 # meio

				# Calcula vizinhos da direita
				for i in range(-neighbor_range, neighbor_range, 1):
					mask[middle + i, middle + neighbor_range] = 2

				# Vizinhos da esquerda
				for i in range(-neighbor_range, neighbor_range, 1):
					mask[middle + i, middle - neighbor_range] = 2

				# Vizinhos de cima
					for i in range(-neighbor_range, neighbor_range, 1):
						mask[middle - neighbor_range, middle + i] = 2

				# Vizinhos de baixo
				for i in range(-neighbor_range, neighbor_range, 1):
					mask[middle + neighbor_range, middle + i] = 2
				for row in mask:
					print("\t".join(([str(x) for x in row])))
		return branches

	def post_processing(self, img, endpoints, branches):
		true_endpoints = self.post_processing_endpoints(img, endpoints)
		true_branches = []
		"""
		if len(branches) == 1:
			true_branches = self.post_processing_branches(img, branches)
		else:
			true_branches = branches
		"""

		return true_endpoints, true_branches
