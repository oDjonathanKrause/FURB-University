% Djonathan, Leonardo, Roberto - IA - 2018.1
% Função que gera as combinações de características de acordo com a quantidade de características
function resultado = geraCombinacoes(limite)
	% gera combinações
	resultadoTotal = geraCombinacoesRec(limite, 1, []);
	resultado = [];
	
	% retorna somente as combinações que não geram repetição de verificações durante os calculos de k de cada combinação
	for i = 1:size(resultadoTotal, 1)
		if existeCombinacao(resultado, resultadoTotal(i, :)) == 0
			resultado = [resultado; resultadoTotal(i, :)];
		end
	end
end

% Função recursiva que gera as combinações tendo como ponto de parada a quantidade de características
% são geradas as combinações de forma sequencial de [1 1 1 1] a [4 4 4 4]
% nenhum indice maior possui um valor menor que um indice menor
% portanto [4 3 2 1] não é gerado
function resultado = geraCombinacoesRec(limite, atual, resultadoAnterior)
	if size(resultadoAnterior, 2) == limite
		resultado = resultadoAnterior;
	else
		resultado = [];
		for i = atual:limite 
			resultado = [resultado; geraCombinacoesRec(limite, i, [resultadoAnterior i])];	
		end
	end

end

% função que verifica se a função unique() retorna um valor de unique() que já existe nos resultados
% por exemplo, unique([1 1 1 2]) = [1 2] e unique([1 1 2 2]) = [1 2]
function resultado = existeCombinacao(resultadoAtual, novoResultado)
	resultado = 0;
	for i = 1:size(resultadoAtual, 1)
		uniqueResultadoAtual = unique(resultadoAtual(i, :));
		uniqueNovoResultado = unique(novoResultado);
		if size(uniqueResultadoAtual, 2) == size(uniqueNovoResultado, 2) && uniqueResultadoAtual == uniqueNovoResultado
			resultado = 1;
			return;
		end
	end
end



