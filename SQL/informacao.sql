select secao.horario,secao.qtd_assentos, filme.nome_filme,filme.preco
from secao
inner join filme on secao.id_secao=filme.id_filme;
