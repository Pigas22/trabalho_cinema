select 
    secao.horario as horario,
    secao.qtd_assentos as qtd_assentos, 
    filme.nome_filme as nome_filme,
    filme.preco as preco
from secao
inner join filme on secao.id_secao=filme.id_filme;
