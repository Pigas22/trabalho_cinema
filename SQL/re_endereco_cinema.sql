select cinema.nome_cinema,endereco.rua
from cinema
inner join endereco on cinema.id_endereco=endereco.id_endereco;
