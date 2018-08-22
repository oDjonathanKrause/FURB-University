% Djonathan, Leonardo, Roberto - IA - 2018.1
% Semelhante à fase anterior, você deve implementar a função regmultipla.m que calcula os parâmetros 𝛽 para o dados de entrada y e X.

function b = regmultipla(X, y)
	b = ((X' * X) ^-1) * (X' * y);
end