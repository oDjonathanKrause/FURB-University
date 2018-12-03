% Djonathan, Leonardo, Roberto - IA - 2018.1
clear all;clc; close all
% Faça um script rmdemo.m que faz o seguinte:
% a. Carrega os valores onde a primeira coluna é o tamanho da casa
% A segunda coluna é o número de quartos, e a terceira coluna é o preço da casa
dados = load('dados\data.mat');
dados = dados.data;

% b. x é o tamanho da casa e numero de quartos
X = ones(size(dados, 1), 1);
X = [X(:,1),  dados(:, 1:2)];

% y é o preço da casa
y = dados(:, 3);

% c. Verifique a correlação e a regressão para Tamanho da casa e Preço e Número de quartos e Preço

% Tamanho da casa e Preço
x1 = dados(:,1);
resultadoCorrelacao1 = correlacao(x1, y);
[b0, b1] = regressao(x1, y);
pontosRegressao1 = b0 + b1 * x1;
figure (1);
scatter(x1, y)
hold on;
plot3(x1, pontosRegressao1);
title('Letra C: Tamanho da Casa X Preco');
xlabel('Tamanho da Casa');
ylabel('Preco');

% Número de quartos e Preço
x2 = dados(:,2);
resultadoCorrelacao2 = correlacao(x2, y);
[b0, b1] = regressao(x2, y);
pontosRegressao2 = b0 + b1 * x2;
figure (2);
scatter(x2, y);
hold on;
plot3(x2, pontosRegressao2);
title('Letra C: Numero de Quartos X Preco');
xlabel('Numero de Quartos');
ylabel('Preco');

% d. Faça o gráfico (diagrama) de dispersão dos dados. Neste caso iremos trabalhar com o espaço 3D (verifique a função scatter3).
figure (3);
scatter3(x1, x2, y);

% e. Trace a linha da regressão no Gráfico de Dispersão (verifique a função plot3).
b = regmultipla(X, y);
pontosRegressao = X * b;
hold on;
plot3(x1, x2, pontosRegressao);
xlabel('Tamanho da Casa');
ylabel('Numero de Quartos');
zlabel('Preco');

%f. Mostre na figura os coeficientes de correlação entre Tamanho da casa e Preço e Número de quartos e Preço.
title(["Correlacao - Tamanho da Casa X Preco: ", num2str(resultadoCorrelacao1), " Correlacao - Numero de Quartos X Preco: ", num2str(resultadoCorrelacao2)]);

%g. Calcule o preço de uma casa que tem tamanho de 1650 e 3 quartos. O resultado deve ser igual a 293081.
disp(["O preco da casa eh: ", num2str([1, 1650, 3] * b)]);