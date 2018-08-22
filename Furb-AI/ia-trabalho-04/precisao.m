% Djonathan, Leonardo, Roberto - IA - 2018.1
% Retorna a portentagem de acerto entre os rotulos previstos e os acertados.
function resultado = precisao(rotulosPrevistos, rotulosCorretos)
	rotulosAcertados = rotulosPrevistos == rotulosCorretos;
	resultado = (sum(rotulosAcertados) / length(rotulosPrevistos)) * 100;
end