INICIO:	
;para cima
	MOV AL, 1  ; manda 1(true) para o bit um da placa
	OUT 6

VERIFICA_SE_TOPO: 
	IN 06 ; leitura do endereco 6(elevador) e joga em AL
	AND AL, 04
	CMP AL, 04  ; verifica se aingiu o topo, se sim desvia para ATINGIU_TOPO
	JZ ATINGIU_TOPO ; faz o jumpeamento para ATINGIU_TOPO
	JMP VERIFICA_SE_TOPO

ATINGIU_TOPO:
	MOV AL, 2  ; manda para baixo
	OUT 6

;para baixo

VERIFICA_SE_DOWN:
	IN 06 ; faz a leitura e joga em AL
	AND AL, 08
	CMP AL, 08
	JZ ATINGIU_BAIXO
	JMP VERIFICA_SE_DOWN

ATINGIU_BAIXO:
	MOV AL, 0
	OUT 6


JMP INICIO

	END