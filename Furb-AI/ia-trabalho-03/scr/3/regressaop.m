% Djonathan, Leonardo, Roberto - IA - 2018.1
function resultado = regressaop(b, X, n)
	resultado = 0;
	for i = 1:(n+1)
		resultado += b(i) * X .^(i-1);
	end
end