INSERT INTO USUARIOS(nome, email, senha) VALUES('Diego', 'diego@email.com', '$2a$10$.I8ASBO7Lc9XHA83/kOOhOiyHt4O5se1RqODDy5qx8mw3V0WSCJXi');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Juliana', 'juliana@email.com', '$2a$10$.I8ASBO7Lc9XHA83/kOOhOiyHt4O5se1RqODDy5qx8mw3V0WSCJXi');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Vanessa', 'vanessa@email.com', '$2a$10$.I8ASBO7Lc9XHA83/kOOhOiyHt4O5se1RqODDy5qx8mw3V0WSCJXi');
INSERT INTO USUARIOS(nome, email, senha) VALUES('Tatiane', 'tatiane@email.com', '$2a$10$.I8ASBO7Lc9XHA83/kOOhOiyHt4O5se1RqODDy5qx8mw3V0WSCJXi');



INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Critica De filme', 'PUBLICO', 1);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Melhores Cenas', 'PUBLICO', 1);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Fale sobre o pos credito', 'PRIVADO', 1);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Perdi meu tempo vendo esse filme', 'PRIVADO', 1);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Critica De filme', 'PUBLICO', 2);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Melhores Cenas', 'PUBLICO', 2);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Fale sobre o pos credito Ruins', 'PRIVADO', 2);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Perdi meu tempo vendo esse filme', 'PRIVADO', 2);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Quero Assistir outra vez', 'PUBLICO', 3);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Qual filme me recomendam', 'PUBLICO', 3);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Diga os melhores filmes da semana', 'PRIVADO', 3);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Tem na Netiflix', 'PRIVADO', 3);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Dicas de filmes infantis', 'PUBLICO', 4);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Esta no cinema', 'PUBLICO', 4);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Piores roteiros', 'PRIVADO', 4);
INSERT INTO GRUPOS_DEBATE(titulo, status, fk_usuario) VALUES('Melhores roteiros', 'PRIVADO', 4);


INSERT INTO  USUARIOS_GRUPOS  (fk_grupo_debate, fk_usuario) VALUES(3, 2);
INSERT INTO  USUARIOS_GRUPOS  (fk_grupo_debate, fk_usuario) VALUES(3, 3);
INSERT INTO  USUARIOS_GRUPOS  (fk_grupo_debate, fk_usuario) VALUES(4, 2);
INSERT INTO  USUARIOS_GRUPOS  (fk_grupo_debate, fk_usuario) VALUES(4, 4);




INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Quero Assistir', 'PUBLICO', 1);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Favoritos', 'PUBLICO', 1);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Filmes Ruins', 'PRIVADO', 1);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Ja Assisti', 'PRIVADO', 1);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Quero Assistir', 'PUBLICO', 2);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Favoritos', 'PUBLICO', 2);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Filmes Ruins', 'PRIVADO', 2);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Ja Assisti', 'PRIVADO', 2);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Quero Assistir', 'PUBLICO', 3);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Favoritos', 'PUBLICO', 3);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Filmes Ruins', 'PRIVADO', 3);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Ja Assisti', 'PRIVADO', 3);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Quero Assistir', 'PUBLICO', 4);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Favoritos', 'PUBLICO', 4);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Filmes Ruins', 'PRIVADO', 4);
INSERT INTO LISTAS_FILMES(nome_lista, status, fk_usuario) VALUES('Ja Assisti', 'PRIVADO', 4);



INSERT INTO FILMES(titulo, imdb_id) VALUES('Sonic', 'tt2622500');
INSERT INTO FILMES(titulo, imdb_id) VALUES('Star Wars: Episode IV - A New Hope', 'tt0076759');
INSERT INTO FILMES(titulo, imdb_id) VALUES('The Shack', 'tt2872518');
INSERT INTO FILMES(titulo, imdb_id) VALUES('Harry Potter and the Prisoner of Azkaban', 'tt0304141');


INSERT INTO AVALIACOES(nota, estrela, status, fk_usuario, fk_filme) VALUES(9.5, 5, 'PUBLICO', 1, 1);
INSERT INTO AVALIACOES(nota, estrela, status, fk_usuario, fk_filme) VALUES(7.5, 3, 'PRIVADO', 3, 1);
INSERT INTO AVALIACOES(nota, estrela, status, fk_usuario, fk_filme) VALUES(7.5, 3, 'PUBLICO', 3, 2);
INSERT INTO AVALIACOES(nota, estrela, status, fk_usuario, fk_filme) VALUES(8.3, 4, 'PRIVADO', 2, 3);
INSERT INTO AVALIACOES(nota, estrela, status, fk_usuario, fk_filme) VALUES(8, 4, 'PRIVADO', 4, 4);




INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(1 , 1);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(1 , 6);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(1 , 11);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(1 , 15);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(2 , 2);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(2 , 7);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(2 , 10);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(2 , 16);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(3 , 3);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(3 , 5);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(3 , 12);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(3 , 14);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(4 , 4);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(4 , 8);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(4 , 9);
INSERT INTO FILMES_LISTA_FILME(fk_filme, fk_lista_filme) VALUES(4 , 13);


INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','Hi hi. Ma vejam só, vejam só.', 1, 1);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PRIVADO','Um, dois três, quatro, PIM, entendeuam? Ha haeeee', 1, 2);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','Com ele ma você vai gerar textuans ha haae.', 1, 3);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','Ma o Silvio Santos Ipsum é muitoam interesanteam.', 1, 4);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','É bom ou não éam?', 2, 1);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PRIVADO','Silvio Santos Ipsum Ma você, topa ou não topamm.', 2, 2);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','Valendo um milhão de reaisammm.', 2, 3);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','É fácil ou não éam?', 3, 1);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','Ma tem ou não tem o celular do milhãouamm?', 3, 4);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','Carla, você tem o ensino fundamentauam?', 4, 3);
INSERT INTO COMENTARIOS(status, texto, fk_usuario, fk_filme) VALUES('PUBLICO','Maa O Ah Ae! Ih Ih! O Raul Gil! ', 4, 4);









