% Djonathan, Leonardo, Roberto - IA - 2018.1
clear all;clc;close all;
% Faça um script demo_regressaop.m que faz o seguinte:
% a. Baixe o arquivo data_preg.mat. A primeira coluna representa os valores de x e a segunda coluna representa os valores de y.
dados = load('dados\data_preg.mat');
dados = dados.data;
x = dados(:, 1);
y = dados(:, 2);

% b. Faça o Gráfico de dispersão dos dados.
figure(1)
scatter(x, y);

% c. Use a função polyfit para gerar a linha de regressão para N = 1 e trace-o no gráfico de dispersão na cor vermelha (plot (x, y, 'r')).
b = fliplr(polyfit(x, y, 1));
y1 = regressaop(b, x, 1);
hold on
plot(x, y1, 'r');

% d. Trace a linha de regressão para N = 2 no gráfico na cor verde.
b = fliplr(polyfit(x, y, 2));
y2 = regressaop(b, x, 2);
hold on
plot(x, y2, 'g');

% e. Trace a linha de regressão para N = 3 no gráfico na cor preta.
b = fliplr(polyfit(x, y, 3));
y3 = regressaop(b, x, 3);
hold on
plot(x, y3, 'k');

% f. Trace a linha de regressão para N = 8 no gráfico na cor amarela.
b = fliplr(polyfit(x, y, 8));
y4 = regressaop(b, x, 8);
hold on
plot(x, y4, 'y');

% g. Calcule o Erro Quadrático Médio (EQM) para cada linha de regressão. Qual é o mais preciso?
residuo = (y1 - y) .^2;
eqm1 = (sum(residuo)) / size(y, 1);
residuo = (y2 - y) .^2;
eqm2 = (sum(residuo)) / size(y, 1);
residuo = (y3 - y) .^2;
eqm3 = (sum(residuo)) / size(y, 1);
residuo = (y4 - y) .^2;
eqm4 = (sum(residuo)) / size(y, 1);

% Resposta: A linha de regressão para N=8 é mais precisa para esse cenário pois obteve o menor erro quadrático médio.

% h. Para evitar o overfitting, divida os dados aleatoriamente em Dados de Treinamento e Dados de Teste. Use os primeiros 10% dos dados como conjunto de teste, e o resto como de treinamento.
pontosOriginais = [x,y];
resultados = zeros(4, 1);

% determina se as etapas de treinamento e verificação são mostradas em imagem
% 0 = não - 1 = sim
mostraSegundoGrafico = 0;
% determina a quantidade de iterações que serão executadas
quantidadeIteracoes = 1000;
% realiza loop para medir várias tentativas de aprendizagem
for loop = 1:quantidadeIteracoes
	
	% randomiza os pontos
	pontosRandomicos = pontosOriginais(randperm(size(pontosOriginais,1)),:);

	% divide os novos pontos em vetores
	xNovo = pontosRandomicos(:, 1);
	yNovo = pontosRandomicos(:, 2);
	
	% divide os pontos entre treinamento e teste
	idxDezPorcento = round((size(x, 1) * 10) / 100); 
	% ordena linhas somente para exibição no gráfico
	xyTeste = sortrows([xNovo(1:idxDezPorcento), yNovo(1:idxDezPorcento)]);
	xyTreinamento = sortrows([xNovo(idxDezPorcento+1:size(x, 1)), yNovo(idxDezPorcento+1:size(x, 1))]);
	xTeste = xyTeste(:, 1);
	yTeste = xyTeste(:, 2);
	xTreinamento = xyTreinamento(:, 1);
	yTreinamento = xyTreinamento(:, 2);
	
	% gera gráfico
	if (mostraSegundoGrafico)
		figure(2);
		if (loop ~= 1)
			pause(1);
		end
		clf reset;
		scatter(xTreinamento, yTreinamento);
		hold on
		scatter(xTeste, yTeste, 'r');
	end
	
	% i. Repita os passos de c - f, mas agora use apenas os dados de treinamento para ajustar a linha de regressão.
	% i.c. 
	b = fliplr(polyfit(xTreinamento, yTreinamento, 1));
	y1 = regressaop(b, xTeste, 1);
	yt1 = regressaop(b, xTreinamento, 1);
	if (mostraSegundoGrafico)
		hold on
		plot(xTreinamento, yt1, 'r');
	end

	% i.d.
	b = fliplr(polyfit(xTreinamento, yTreinamento, 2));
	y2 = regressaop(b, xTeste, 2);
	yt2 = regressaop(b, xTreinamento, 2);
	if (mostraSegundoGrafico)
		hold on
		plot(xTreinamento, yt2, 'g');
	end

	% i.e.
	b = fliplr(polyfit(xTreinamento, yTreinamento, 3));
	y3 = regressaop(b, xTeste, 3);
	yt3 = regressaop(b, xTreinamento, 3);
	if (mostraSegundoGrafico)
		hold on
		plot(xTreinamento, yt3, 'k');
	end

	% i.f.
	b = fliplr(polyfit(xTreinamento, yTreinamento, 8));
	y4 = regressaop(b, xTeste, 8);
	yt4 = regressaop(b, xTreinamento, 8);
	if (mostraSegundoGrafico)
		hold on
		plot(xTreinamento, yt4, 'y');
	end

	% j.
	residuo = (y1 - yTeste) .^2;
	eqm1 = (sum(residuo)) / size(yTeste, 1);
	residuo = (y2 - yTeste) .^2;
	eqm2 = (sum(residuo)) / size(yTeste, 1);
	residuo = (y3 - yTeste) .^2;
	eqm3 = (sum(residuo)) / size(yTeste, 1);
	residuo = (y4 - yTeste) .^2;
	eqm4 = (sum(residuo)) / size(yTeste, 1);
		
	[s, i] = sort([eqm1, eqm2, eqm3, eqm4]);
	resultados(i(1)) = resultados(i(1)) + 1;
end
disp(["Os resultados para ", num2str(quantidadeIteracoes)," iteracoes foram:"]);
disp(["N=1: ", num2str(resultados(1)), " vezes foi melhor"])
disp(["N=2: ", num2str(resultados(2)), " vezes foi melhor"])
disp(["N=3: ", num2str(resultados(3)), " vezes foi melhor"])
disp(["N=8: ", num2str(resultados(4)), " vezes foi melhor"])

% k. Resposta: Depende de quais dados são escolhidos como treinamento e teste.
% Em três execuções consecutivas de 1000 iteracoes, randomizando os pontos e dividindo-os entre 
% grupos de teste e treinamento, os resultados foram:

% Execucao 1:
% N=1: 16 vezes foi melhor
% N=2: 136 vezes foi melhor
% N=3: 328 vezes foi melhor
% N=8: 520 vezes foi melhor

% Execucao 2:
% N=1: 32 vezes foi melhor
% N=2: 130 vezes foi melhor
% N=3: 300 vezes foi melhor
% N=8: 538 vezes foi melhor

% Execucao 3:
% N=1: 24 vezes foi melhor
% N=2: 138 vezes foi melhor
% N=3: 280 vezes foi melhor
% N=8: 558 vezes foi melhor

% Para a maioria dos cenários a regressão para N=8 foi melhor, porém muitas vezes a regressão N=3 também foi.
% Existem casos que até a regressão N=1 foi melhor que as demais.