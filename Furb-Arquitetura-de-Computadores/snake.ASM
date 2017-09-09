	
	    ; AL = out, dispozitivo de saida
	MOV AL, FF ; deixar a bola no inicio
	OUT 4 

	MOV AL, 4F  ; ir 15 passos para baixo  - 0100 1111 = bin e 4F = hexa
	OUT 4


TECLADO:  ; fica jumpando na instrução do teclado
	IN 07  ; le o teclado(k) e joga codigo da tecla em AL

	CMP AL, 41 ; compara e verifica se esta precionando A
	JZ ESQUERDA ; se sim jumpa pra ESQUERDA
	CMP AL, 61 ; compara se e a minusculo
	JZ ESQUERDA

	CMP AL, 44 ; compara e verifica se esta precionando D
	JZ DIREITA ; sem sim jumpa para DIREITA
	CMP AL, 64 ; compara se e d minusculo
	JZ DIREITA

	CMP AL, 57 ; compara e verifica se esta precionando W
	JZ SOBE  ; se sim jumpa PARA SOBE
	CMP AL, 77 ; compara se e w minusculo
	JZ SOBE

	CMP AL, 53 ; compara e verifica se esta precionando S
	JZ DESCE ; se sim jumpa para DESCE
	CMP AL, 73 ; compara se e s minusculo
	JZ DESCE ; 
JMP TECLADO

ESQUERDA:
	MOV AL, 2A ; 2A = 0010 1010 hexa, ou seja, 0010 = esquerda e 1010 = 10 passos
	OUT 4	; joga para saida 4, como especificado no programa
	JMP TECLADO
DIREITA:
	MOV AL, 1A
	OUT 4
	JMP TECLADO
SOBE:
	MOV AL, 8A
	OUT 4
	JMP TECLADO
DESCE:
	MOV AL, 4A
	OUT 4
	JMP TECLADO


JMP TECLADO

FIM: JMP FIM
	END