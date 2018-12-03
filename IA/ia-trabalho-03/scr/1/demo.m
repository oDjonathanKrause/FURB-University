% Djonathan, Leonardo, Roberto - IA - 2018.1
% 2) Faça um script no Octave chamado demo.m onde para cada dataset faça os seguintes comandos:
clear all;clc; close all

% Declaração de cenários
x1 = [10;8;13;9;11;14;6;4;12;7;5];
y1 = [8.04;6.95;7.58;8.81;8.33;9.96;7.24;4.26;10.84;4.82;5.68];
x2 = [10;8;13;9;11;14;6;4;12;7;5];
y2 = [9.14;8.14;8.47;8.77;9.26;8.10;6.13;3.10;9.13;7.26;4.74];
x3 = [8;8;8;8;8;8;8;8;8;8;19];
y3 = [6.58;5.76;7.71;8.84;8.47;7.04;5.25;5.56;7.91;6.89;12.50];

% Determina o cenário que será executado 
for cenario = 1:3
	% Escolhe o cenário
	switch (cenario)
		case 1
			x = x1;
			y = y1;
		case 2
			x = x2;
			y = y2;
		case 3
			x = x3;
			y = y3;
	endswitch
	
	% seleciona imagem
	figure(cenario);
	
	% a. Faça um Gráfico de Dispersão (veja função Scatter).
	scatter(x, y);

	% b. Calcule o coeficiente de correlação.
	resultadoCorrelacao = correlacao(x, y);

	% c. Trace a linha da regressão no Gráfico de Dispersão (utilize a função hold on para isto)
	[b0, b1] = regressao(x, y);
	pontosRegressao = b0 + b1 * x;
	hold on;
	plot(x, pontosRegressao);

	% d. Mostre os coeficientes de correlação e regressão no Gráfico de Dispersão (utilize as funções title e num2str)
	title(["Correlacao: ", num2str(resultadoCorrelacao), " B0: ", num2str(b0), " B1: ", num2str(b1)]);

end

% 3. O segundo dataset não é apropriado para regressão linear pois a natureza da relação entre os dados não é linear.
% Para este caso a regressão polinomial oferesse melhores resultados.
% O terceiro dataset também não é apropriado pois a qualidade da relação entre os dados não é boa.