% Djonathan, Leonardo, Roberto - IA - 2018.1
function rotuloPrevisto = meuKnn(dadosTrain, rotuloTrain, dadosTeste, k)
	dist = zeros(size(dadosTeste, 1), size(dadosTrain, 1));
	rotuloPrevisto = zeros(size(dadosTeste, 1), 1);
	%Para cada exemplo de teste
	for i = 1:size(dadosTeste, 1)
		% Calcule a distância entre o exemplo de teste e os dados de treinamento
		for j = 1:size(dadosTrain, 1)
			soma = dadosTeste(i, :) - dadosTrain(j, :);
			soma2 = sum(soma .^ 2);
			val = sqrt(soma2);
			dist(i, j) = val;
		end
		% Ordene as distâncias. A ordem iX de cada elemento ordenado é importante:
		% [distOrdenada ind] = sort(...);
		[distOrdenada ind] = sort(dist(i, :));
		% O rótulo previsto corresponde ao rótulo do exemplo mais próximo (iX(1))
		% rotuloPrevisto(i) = rotuloTrain(ind(1));
		% Agora, a moda (função mode) dos rótulos correspondentes são os rótulos previstos.
		rotuloPrevisto(i) = mode(rotuloTrain(ind(1:k)));
	end
end
