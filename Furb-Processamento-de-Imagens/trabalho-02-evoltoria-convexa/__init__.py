def define_curva(p1, p2, p3):        
        a = ((p2[0] - p1[0]) * (p3[1] - p1[1]))
        b = ((p2[1] - p1[1]) * (p3[0] - p1[0]))
        produtoVetorial = a - b

        return produtoVetorial < 0.000001

# Entrada
pontos = [(1,1),(2,3),(3,2),(5,2),(0,4),(3,4),(4,4),(3,6),(6,5)]

# Odena pelo eixo x
pontos = sorted(pontos)

# Add os dois primeiros pontos na lista superior
inferior = []
superior = []
superior.append(pontos[0])
superior.append(pontos[1])

# Verifica os pontos
for i in range(3, len(pontos)):
        superior.append(pontos[i])
        while len(superior) > 2 and define_curva(superior[-1], superior[-2], superior[-3]):
                superior.pop(len(superior)-2)

inferior.append(pontos[len(pontos)-1])
inferior.append(pontos[len(pontos)-2])

for j in range(len(pontos)-3, -1, -1):
        inferior.append(pontos[j])
        while len(inferior) > 2 and define_curva(inferior[-1], inferior[-2], inferior[-3]):
                inferior.pop(len(inferior)-2)

resultado = sorted(set(superior + inferior))
print(resultado)

