% Djonathan, Leonardo, Roberto - IA - 2018.1
clear all;clc;close all;

grupoDados = load('dados\grupodados1');

% Introdução -----------------------------------------------------------------------------------

% Primeira parte
k = 1;
rotulosPrevistos = meuKnn(grupoDados.grupoTrain, grupoDados.trainRots, grupoDados.grupoTest, k);
porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);
disp(['A precisao para o Grupo de Dados 1 com k=', num2str(k), ' eh ', num2str(porcentagemPrecisao), '%.']);
% A precisão para o Grupo de Dados 1 com k=1 é 96%.
figure(1);
visualizaPontos(grupoDados.grupoTest, rotulosPrevistos, 1, 2);
title(["Dados 1 - k=", num2str(k)]);

% Segunda parte
k = 10;
rotulosPrevistos = meuKnn(grupoDados.grupoTrain, grupoDados.trainRots, grupoDados.grupoTest, k);
porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);
disp(['A precisao para o Grupo de Dados 1 com k=', num2str(k), ' eh ', num2str(porcentagemPrecisao), '%.']);
% A precisão para o Grupo de Dados 1 com k=10 é 94%.
figure(2);
visualizaPontos(grupoDados.grupoTest, rotulosPrevistos, 1, 2);
title(["Dados 1 - k=", num2str(k)]);

% Q1.1 -----------------------------------------------------------------------------------------
% Qual é a precisão máxima que você consegue da classificação?
% Armazena o melhor resultado
melhorPorcentagem = 0;

% Determina o valor máximo de k
kMaximo = size(grupoDados.grupoTest, 1) / 2;
% busca a melhor precisão entre os ks
disp(['Verificando a precisao para os valores de k...']);
for k = 1:kMaximo
	rotulosPrevistos = meuKnn(grupoDados.grupoTrain, grupoDados.trainRots, grupoDados.grupoTest, k);
	porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);
	if porcentagemPrecisao > melhorPorcentagem
		melhorPorcentagem = porcentagemPrecisao;
		ksMelhorPorcentagem = [num2str(k)];
	elseif porcentagemPrecisao == melhorPorcentagem
		ksMelhorPorcentagem = [ksMelhorPorcentagem ', ' num2str(k)];
	end
end

disp(['A melhor precisao para o Grupo de Dados 1 eh ', num2str(melhorPorcentagem), '% para os ks ' ksMelhorPorcentagem, '.']);
% Resposta:
% A melhor precisão para o Grupo de Dados 1 é 98% para os ks 3, 4, 6.
% Os calculos foram realizados de k=1 até k=metade dos elementos testados.

% Q1.2 -----------------------------------------------------------------------------------------
% É necessário ter todas as características (atributos) para obter a precisão máxima para esta classificação?

% gera as combinções de características para realizar as comparações
combinacoesCaracteristicas = geraCombinacoes(size(grupoDados.grupoTest, 2));

% detemina os valores para realizar as verificações
porcentagemAlvo = melhorPorcentagem;
kMaximo = size(grupoDados.grupoTest, 1) / 2;

% para cada combinação de característivas, verifica quais os valores de k que geram precisão igual ou superior
% a maior precisão encontrada na questão anterior
for i = 1: size(combinacoesCaracteristicas, 1)
	resultadosMelhores = [];
	caracteristicas = unique(combinacoesCaracteristicas(i,:));
	disp(['Verificando as precisoes para as caracteristicas ' mat2str(caracteristicas), '...']);
	for k = 1:kMaximo
		rotulosPrevistos = meuKnn(grupoDados.grupoTrain(:, caracteristicas), grupoDados.trainRots, grupoDados.grupoTest(:, caracteristicas), k);
		porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);
		if porcentagemPrecisao >= porcentagemAlvo
			resultadosMelhores = [resultadosMelhores; k, porcentagemPrecisao];
		end
	end
	
	%exibe os resultados:
	if size(resultadosMelhores, 1) > 0
		disp(['Para as caracteristicas ', mat2str(caracteristicas), ' os valoes de k/precisao foram:']);
		for j = 1:size(resultadosMelhores, 1)
			disp([num2str(resultadosMelhores(j, 1)), '/', num2str(resultadosMelhores(j, 2)), '%']);
		end
	end
	
end

% Resposta:
% Não é necessário utilizar todas as características para gerar a melhor precisão.
% Quatro combinações de características mostratam valores de presisão = 98%.
% Os calculos foram realizados de k=1 até k=metade dos elementos testados.

% O resultado das verificações foi:
% Para as caracteristicas [1 3] os valoes de k/precisao foram:
% 3/98%
% 4/98%
% 6/98%

% Para as caracteristicas [1 3 4] os valoes de k/precisao foram:
% 5/98%

% Para as caracteristicas [2 3 4] os valoes de k/precisao foram:
% 20/98%
% 22/98%
% 23/98%
% 24/98%
% 25/98%

% Para as caracteristicas [3 4] os valoes de k/precisao foram:
% 4/98%
% 24/98%
% 25/98%