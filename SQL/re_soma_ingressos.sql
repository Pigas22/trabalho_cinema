SELECT 
    secao.id_secao as id_secao,
    filme.nome_filme as nome_filme,
    sum(filme.preco) as valor_final
from venda
join secao on venda.id_secao=secao.id_secao
join filme on secao.id_filme= filme.id_filme
group by secao.id_secao,filme.nome_filme
