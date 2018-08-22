% Djonathan, Leonardo, Roberto - IA - 2018.1
% 1) Implemente duas funções chamadas correlacao.m e regressao.m.

function resultado = correlacao(x, y)
    numerador = sum((x-mean(x)) .* (y-mean(y)));

    v1 = sum((x - mean(x)) .^ 2);
    v2 = sum((y - mean(y)) .^ 2);
    denominador = sqrt(v1 .* v2);

    resultado = numerador / denominador;
end