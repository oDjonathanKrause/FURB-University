% Djonathan, Leonardo, Roberto - IA - 2018.1
% função que realiza a normalização de dados
function resultado = normal(dados)
	resultado = zeros(size(dados));
	% varre coluna a coluna da matriz para realizar a normalização
	for j = 1:size(dados, 2)
		valorMaximo = max(dados(:, j));
		valorMinimo = min(dados(:, j));
		% para cada linha da coluna calcula o valor normalizadoda posição da matriz
		for i = 1:size(dados, 1)
			resultado(i, j) = (dados(i, j) - valorMinimo) / (valorMaximo - valorMinimo);
		end
	end
end