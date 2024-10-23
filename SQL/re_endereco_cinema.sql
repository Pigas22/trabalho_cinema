select 
    cinema.nome_cinema as nome_cinema,
    endereco.rua as rua
from cinema
inner join endereco on cinema.id_endereco=endereco.id_endereco
