class Disciplina(object):

    id = 0

    #Construror
    def __init__(self, codigo, nome, cargaH, valor):
        self.__codigo = Disciplina.gerar_id()
        self.__nome = nome
        self.__cargaH = cargaH
        self.__valor = valor
     
    #get e set do codigo
    def get_codigo(self):
        return self.__codigo

    def set_codigo(self, novo_codigo):
        if novo_codigo == "":
            print ("O codigo nao pode ficar em branco")
        else:
            self.__codigo = novo_codigo
            #print ("Alterado com sucesso")    
    
    #get e set do nome    
    def get_nome(self):
        return self.__nome

    def set_nome(self, novo_nome):
        if novo_nome == "":
            print ("Nao pode ser nulo")
        else:
            self.__nome = novo_nome
            #alterado

    
    #get e set de carga horaria
    def get_carga_horaria(self):
        return self.__cargaH
    
    def set_carga_horaria(self):
        if nova_carga == "":
            print("Nao pode ser nulo")
        else:
            self.__cargaH = nova_carga
            #alterado com sucesso
    
    
    #get e set de valor
    def get_valor(self):
        return self.__valor
        
    def set_valor(self):
        if novo_valor == "":
            print("Valor nao pode ser nulo")
        else:
            self.__valor = novo_valor
            #alterado ok
    
    #WTF
    @classmethod
    def gerar_id(cls):
        cls.id += 1
        print(len(str(cls.id)))
        strZeros = ""
        for i in range(len(str(cls.id)),4):
            strZeros += "0"
        return strZeros + str(cls.id)
    

    codigo = property(fget=get_codigo, fset=set_codigo)
    nome = property(fget=get_nome, fset=set_nome)
    cargaH = property(fget=get_carga_horaria, fset=set_carga_horaria)
    valor = property(fget=get_valor, fset=set_valor)



    