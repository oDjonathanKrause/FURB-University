% Djonathan, Leonardo, Roberto - IA - 2018.1
clear all;clc;close all;

grupoDados = load('dados\grupodados2');

% Q2.1 -----------------------------------------------------------------------------------------
% Aplique seu kNN a este problema. Qual é a sua precisão de classificação?

% Determina o valor máximo de k
k = 1;

% calcula os rótulos e a precisao 
disp(['Verificando a precisao para o valor de k...']);
rotulosPrevistos = meuKnn(grupoDados.grupoTrain, grupoDados.trainRots, grupoDados.grupoTest, k);
porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);

disp(['A precisao para o Grupo de Dados 2 eh ', num2str(porcentagemPrecisao), '% para k=', num2str(k), '.']);
% Resposta:
% A precisão para o Grupo de Dados 2 é 68.3333% para k=1.

% Q2.2 -----------------------------------------------------------------------------------------
% A precisão pode ser igual a 98% com o kNN. Descubra por que o resultado atual é muito menor. 
% Ajuste o conjunto de dados ou k de tal forma que a precisão se torne 98% e explique o que você fez e por quê.

% Determina o valor máximo de k
k = 1;

% calcula os rótulos e a precisao
disp(['Verificando a precisao para o valor de k...']);
rotulosPrevistos = meuKnn(normal(grupoDados.grupoTrain), grupoDados.trainRots, normal(grupoDados.grupoTest), k);
porcentagemPrecisao = precisao(rotulosPrevistos, grupoDados.testRots);

disp(['A precisao para o Grupo de Dados 2 com normalizacao eh ', num2str(porcentagemPrecisao), '% para k=', num2str(k), '.']);
% Resposta:
% A precisão para o Grupo de Dados 2 com normalização é 98.3333% para k=1.
% Para obter a precisão maior foi necessário normalizar os dados de treinamento e teste.
% Este grupo de dados compara diversas características de vinhos. Estas características são medidas em unidades de medida 
% distintas entre si.
% Para que o cálculo de distância seja utilizado de forma que a influência de cada característica tenha o mesmo peso, é necessário
% normalizar os dados.
% Desta forma todos os dados são representados em um range de números (entre 0 e 1) mantendo a proporção entre os dados.