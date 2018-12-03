class Curso():
    
    #declaracao da lista de cursos
    cursos = []

     #construtor
    def __init__(self, codigo, nome, duracao):
        self.__codigo = codigo
        self.__nome = nome
        self.__duracao = duracao
        self.__grade = {}
    
    def add_disciplina(self,disciplina,semestre):
        #poderia ter uma tupla como chave mas talvez fique mais complicado.
        self.__grade[disciplina] = semestre
        print (self.__grade[disciplina])
        
    #gets e sets   
    def get_codigo(self):
        return self.__codigo
        
    def set_codigo(self, novo_codigo):
        if novo_codigo == "":
            print('Nao pode ser nulo')
        else:
            self.__codigo = novo_codigo
            
    #nome
    def get_nome(self):
        return self.__nome
        
    def set_nome(self, novo_nome):
        if novo_nome == "":
            print("O nome nao pode ser nulo")
        else:
            self.__nome = novo_nome
            
    #duracao
    def get_duracao(self):
        return self.__duracao
        
    def set_duracao(self, nova_duracao):
        if nova_duracao == "":
            print("A nova duracao nao pode ser nula")
        else:
            self.__duracao = nova_duracao

    #grade    
    def get_grade(self):
        return self.__grade
        
    def set_grade(self, nova_grade):
        if nova_grade == "":
            print("A nova grade nao pode ser nula")
        else:
            self.__grade = nova_grade
            

            
    codigo = property(fget=get_codigo, fset=set_codigo)
    duracao = property(fget=get_duracao, fset=set_duracao)
    nome = property(fget=get_nome, fset=set_nome)        
    grade = property(fget=get_grade, fset=set_grade)