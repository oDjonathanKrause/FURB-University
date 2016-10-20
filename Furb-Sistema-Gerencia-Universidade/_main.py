#! /usr/bin/python
# -*- coding: latin1 -*-




__author__ = "gabriel"
__date__ = "$11/11/2015 10:00:51$"

from Matricula import *
from Aluno import *
from Disciplina import *
from Curso import *


alunos = []
disciplinas = []

if __name__ == "__main__":

    opcao_m1 = True
    while opcao_m1:
        #printa opcoes do menu principal
        print('\n     Menu principal')
        print ('\n1.Disciplinas'
             + '\n2.Alunos'
             + '\n3.Cursos'
             + '\n4.Matriculas')

        opcao_m1 = input()

        #Menu disciplinas
        if opcao_m1 == '1':
          opcao_m1 = False
          opcao_m2 = True

          while opcao_m2:
              #printa as opcoes
              print('\n   Menu de Disciplinas')
              print('\n1. Adicionar disciplina'
                  + '\n2. Remover disciplina'
                  + '\n3. Editar disciplina'
                  + '\n4. Exibir disciplinas'  
                  + '\n5. Voltar ao menu anterior')

              opcao_m2 = input()

              #Add Disciplina
              if opcao_m2 == '1':
                  nome = input('Nome da disciplina: ')
                  cargaH = input('Carga horaria da disciplina: ')
                  valor = input('Valor da disciplina: ')
                  
                  d = Disciplina(nome, cargaH, valor)
                  disciplinas.append(d)
                  
              #Remover Disciplina
              elif opcao_m2 == '2':
                  print('Remover disciplina')
                  opcao_m2 = False

              #Editar Disciplina
              elif opcao_m2 == '3':
                  print('Editar disciplina')
                  opcao_m2 = False

              #Volta ao menu anterior
              elif opcao_m2 == '4':
                  opcao_m1 = True
                  opcao_m2 = False

              else:
                  print("\nOpcao invalida")
                  opcao_m2 = True

        #Menu Alunos
        elif opcao_m1 == '2':
          opcao_m1 = False
          opcao_m2 = True

          while opcao_m2:
              #printa as opcoes
              print('\n   Menu de Alunos')
              print('\n1. Adicionar Aluno'
                  + '\n2. Remover Aluno'
                  + '\n3. Editar Aluno'
                  + '\n4. Listar Alunos'
                  + '\n5. Voltar ao menu anterior')

              opcao_m2 = input()

              #add Aluno
              if opcao_m2 == '1':                  
                  nome = input('Nome do Aluno: ')
                  email = input('Email do Aluno: ')
                  curso = input('Curso do Aluno: ')
                  
                  a = Aluno(nome, email, curso)
                  alunos.append(a)

              #remove aluno
              elif opcao_m2 == '2':
                  if alunos == []:
                      print('Nao ha alunos cadastrados')
                  else:
                      for aluno in alunos:
                          Aluno.printa(aluno)
                      
                      ra = input('Digite o codigo do aluno: ')
                      alunos.pop(int(ra))
            
              #edita aluno
              elif opcao_m2 == '3':

                  if alunos == []:
                      print('Nao ha alunos cadastrados')

                  else:
                      
                      print('\nAlunos cadastrados')
                      for aluno in alunos:
                          Aluno.printa(aluno)
    
                      escolha = input('\nInforme o RA do aluno: ')
                      if int(escolha) == a.RA:           
                          a.set_nome(input('Nome do Aluno: '))
                          a.set_email(input('Email do Aluno: '))
                          a.set_curso(input('Curso do Aluno: '))
                  
              #lista alunos
              elif opcao_m2 == '4':
                  if alunos == []:
                      print('Nao ha alunos cadastrados')
                  else:    
                      print('  Alunos cadatrados')
                      for aluno in alunos:
                          Aluno.printa(aluno)

              #volta ao menu anterior
              elif opcao_m2 == '5':
                  opcao_m1 = True
                  opcao_m2 = False

              else: 
                  print("\nOpcao invalida")
                  opcao_m2 = True

        #Menu Cursos
        elif opcao_m1 == '3':
          opcao_m1 = False
          opcao_m2 = True

          while opcao_m2:
              #printa as opcoes
              print('\n   Menu de Cursos')
              print('\n1. Adicionar Curso'
                  + '\n2. Remover Curso'
                  + '\n3. Editar Curso'
                  + '\n4. Voltar ao menu anterior')

              opcao_m2 = input()

              #add Curso
              if opcao_m2 == '1':
                  print('add Curso')
                  opcao_m2 = False

              #remove Curso
              elif opcao_m2 == '2':
                  print('remove Curso')
                  opcao_m2 = False
            
              #edita Curso
              elif opcao_m2 == '3':
                  print('edita Curso')
                  opcao_m2 = False

              #volta ao menu anterior
              elif opcao_m2 == '4':
                  opcao_m1 = True
                  opcao_m2 = False

              else:
                  print("\nOpcao invalida")
                  opcao_m2 = True                  

        #Menu Matriculas
        elif opcao_m1 == '4':
          opcao_m1 = False
          opcao_m2 = True

          while opcao_m2:
              #printa as opcoes
              print('\n   Menu de Matriculas')
              print('\n1. Adicionar Matriculas'
                  + '\n2. Remover Matriculas'
                  + '\n3. Editar Matriculas'
                  + '\n4. Voltar ao menu anterior')

              opcao_m2 = input()

              #add Matriculas
              if opcao_m2 == '1':
                  print('add Matriculas')
                  opcao_m2 = False

              #remove Matriculas
              elif opcao_m2 == '2':
                  print('remove Matriculas')
                  opcao_m2 = False
            
              #edita Matriculas
              elif opcao_m2 == '3':
                  print('edita Matriculas')
                  opcao_m2 = False

              #volta ao menu anterior
              elif opcao_m2 == '4':
                  opcao_m1 = True
                  opcao_m2 = False

              else:
                  print("\nOpcao invalida")
                  opcao_m2 = True    

        #opcao invalida      
        else:
          print("\nOpcao invalida")
          opcao_m1 = True

        
    
    """
    print ("Hello World")
    alunoss = []
    numero = "00001"
    nome = "Pedro Silva"
    idade = 19
    aluno = Aluno(nome, idade)
    alunoss.append(aluno)
    periodo = "2015.2"
    m = Matricula(numero, aluno, periodo)
    alunoss.remove(aluno)
    print(len(alunoss))
    #print(alunoss[0])
    m.numero = "000002"
    #print(dir(m))
    print(Disciplina.gerar_id())
    print(Disciplina.gerar_id())
    print(Disciplina.gerar_id())
    print(Aluno.alunos)
    nome = "Pedro Silva"
    email = "pedro.silva@email.com"
    curso = Curso(10, "Ciencia da Computacao", 9, "grade")
    dis = Disciplina(20, "Liguagem de Programacão", 4, 600)
    aluno = Aluno(nome, email,curso)
    print(""+str(dis.codigo))
    print ("Hello World")
    """ 
