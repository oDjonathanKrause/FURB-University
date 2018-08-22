% Djonathan, Leonardo, Roberto - IA - 2018.1
% 1) Implemente duas funções chamadas correlacao.m e regressao.m.
function [b0, b1] = regressao(x, y)
    numerador = sum((x-mean(x)) .* (y-mean(y)));
    denominador = sum((x-mean(x)) .^ 2);
    b1 = numerador / denominador;
    b0 = mean(y) - b1 .* mean(x);
end