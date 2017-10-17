
            
class Matricula():

    #contrutor
    def __init__(self, numero, aluno, periodo):
        self.__numero = numero
        self.__aluno = aluno
        self.__periodo = periodo
        
    #gets e sets de numero    
    def get_numero(self):
        return self.__numero
    
    def set_numero(self, novo_numero):
        if novo_numero == "":
            print("O numero nao pode ser nulo")
        else:
            print("Numero: %s" % novo_numero)
            self.__numero = novo_numero 

    #gets e sets de aluno
    def get_aluno(self):
        return self.__aluno
    
    def set_aluno(self, novo_aluno):
        if novo_aluno == "":
            print("O aluno nao pode ser nulo")
        else:
            self.__aluno = novo_aluno
            
    #gets e sets do periodo
    def get_periodo(self):
        return self.__periodo
        
    def set_periodo(self, novo_periodo):
        if novo_periodo == "":
            print("O periodo nao pode ser nulo")
        else:
            self.__periodo = novo_periodo

      
    numero = property(fget=get_numero, fset=set_numero)
    aluno = property(fget=get_aluno, fset=set_aluno)
    periodo = property(fget=get_periodo, fset=set_periodo)