% Djonathan, Leonardo, Roberto - IA - 2018.1
clear all;clc;close all;	

grupoDados = load('dados\grupodados3');

% Q3.1 -----------------------------------------------------------------------------------------
% Aplique o kNN ao problema usando k = 1. Qual é a precisão na classificação?

% Determina o valor máximo de k
k = 1;

% calcula os rótulos e a precisao 
disp(['Verificando a precisao para o valor de k...']);
rotulosPrevistos = meuKnn(grupoDados.grupoTrain, grupoDados.trainRots, grupoDados.grupoTest, k);
porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);

disp(['A precisao para o Grupo de Dados 3 eh ', num2str(porcentagemPrecisao), '% para k=', num2str(k), '.']);
% Resposta:
% A precisão para o Grupo de Dados 3 é 70% para k=1.

% Q3.2 -----------------------------------------------------------------------------------------
% A precisão pode ser igual a 92% com o kNN. Descubra por que o resultado atual é muito menor. 
% Ajuste o conjunto de dados ou k de tal forma que a precisão se torne 92% e explique o que você fez e por quê.
melhorPorcentagem = 0;

% Determina o valor máximo de k
kMaximo = size(grupoDados.grupoTest, 1) / 2;

porcentagemMaxima = 92;
% porcentagemMaxima = 100;

% busca a melhor precisão entre os ks
disp(['Verificando a precisao para os valores de k...']);
for k = 1:kMaximo
	rotulosPrevistos = meuKnn(normal(grupoDados.grupoTrain), grupoDados.trainRots, normal(grupoDados.grupoTest), k);
	porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);
	if porcentagemPrecisao > melhorPorcentagem && porcentagemPrecisao <= porcentagemMaxima
		melhorPorcentagem = porcentagemPrecisao;
		ksMelhorPorcentagem = [num2str(k)];
	elseif porcentagemPrecisao == melhorPorcentagem
		ksMelhorPorcentagem = [ksMelhorPorcentagem ', ' num2str(k)];
	end
end

disp(['A melhor precisao, considerando a portentagem maxima de ', num2str(porcentagemMaxima), '%, para o Grupo de Dados 3 com normalizacao eh ', num2str(melhorPorcentagem), '% para os ks ' ksMelhorPorcentagem, '.']);

% Resposta:
% A melhor precisao, considerando a portentagem maxima de 92%, para o Grupo de Dados 3 com normalizacao é 92% para os ks 14, 16, 18, 19, 20, 21, 23, 24.
% Os cálculos foram realizados de k=1 até k=metade dos elementos testados.

% Para obter a precisão maior foi necessário normalizar os dados de treinamento e teste, pelo mesmo motivo que na questão Q2.2.
% Também foi necessário encontrar um valor de k diferente de 1.

% Na busca foram identificados valores de k que resultam em um valor precisão melhor que 92%:
% A melhor precisao, considerando a portentagem maxima de 100%, para o Grupo de Dados 3 com normalizacao é 94% para o k 25.