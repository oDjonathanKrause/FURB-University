class Aluno(object):

    __RA = 0
    
    #construtor
    def __init__(self, nome, email, curso):
        self.__RA = type(self).__RA  #WTF
        self.__nome = nome
        self.__email = email
        self.__curso = curso
        type(self).__RA += 1
     
    #get RA
    def get_ra(self):
        return self.__RA
     
    def set_ra(self, ra):
        self.__RA = ra
     
    #get e set de email    
    def get_email(self):
        return self.__email

    def set_email(self, novo_email):
        if novo_email == "":
            print ("a email nao pode ser nula")
        else:
            self.__email = novo_email
    
    #get e set de nome  
    def get_nome(self):
        return self.__nome

    def set_nome(self, novo_nome):
        if novo_nome == "":
            print ("O nome do aluno nao pode ser nulo")
        else:
            self.__nome = novo_nome
    
    #get e set Curso
    def get_curso(self):
        return self.__curso
    
    def set_curso(self, curso):
        if not curso:
            print ("O curso nao pode ser nulo")
        else:
            self.__curso = curso
         

    def printa(self):
        print(self.RA, '-', self.nome)






    RA = property(fget=get_ra, fset=set_ra)
    nome = property(fget=get_nome, fset=set_nome)
    email = property(fget=get_email, fset=set_email)
    curso = property(fget=get_curso, fset=set_curso)
