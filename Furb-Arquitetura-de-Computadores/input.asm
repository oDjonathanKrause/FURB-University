data segment
; add your data here!

BUFFER DB 20,?," "


MENSAGEM DB "INSIRA UM TEXTO COM NO MINIMO 10 LETRAS",13,10,"$" ;13,10 = PULA LINHA


MENSAGEM2 DB 13,10,"OBRIGADO POR DIGITAR",13,10,"$"

ends

stack segment
    dw 128 dup(0)
ends

code segment
start:
; set segment registers:
mov ax, data
mov ds, ax
mov es, ax

; add your code here

MOV AH,9
MOV DX,OFFSET MENSAGEM
INT 21H

REPETE: 
;AQUI VOCE DIGITA O TEXTO
MOV AH,0AH 
MOV DX,OFFSET BUFFER 
INT 21H

MOV BX,OFFSET BUFFER + 1 
CMP [BX],10
JL REPETE



MOV AH,9
MOV DX,OFFSET MENSAGEM2
INT 21H



; wait for any key.... 
mov ah, 1
int 21h

mov ax, 4c10h ; exit to operating system.
;MOV AH,4CH
;MOV AL,00H
int 21h 
ends

end start ; set entry point and stop the assembler.