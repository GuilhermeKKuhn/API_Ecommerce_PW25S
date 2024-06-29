
--INSERT CATEGORIAS
insert into public.categoria(categoria) values ('Animes');
insert into public.categoria(categoria) values ('Filmes');
insert into public.categoria(categoria) values ('Jogos');
insert into public.categoria(categoria) values ('Funkos');


--INSERT PRODUTOS ANIMES
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Action Figure Roronoa Zoro','Action Figure Roronoa Zoro One Piece 14.5 x 12.7 x 7.8 centímetros',53.33,'https://m.media-amazon.com/images/I/61DjOw4RQ+L._AC_SX679_.jpg',1);
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Action Figure Goku Shenlong','Action Figure Goku e Shenlong Dragon Ball GT',110.00,'https://acdn.mitiendanube.com/stores/004/209/712/products/img_4047-23ab49e2530164961917073058469097-480-0.jpg',1);
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Action Figure Naruto','Action Figure Naruto Uzumaki modo Sennin 33 centimetros',86.50,'https://images.tcdn.com.br/img/img_prod/460977/estatua_colecionavel_naruto_uzumaki_modo_sennin_naruto_shippuden_36cm_anime_manga_101517_1_840f0009ee9a3f328ce596e3c822042d.jpg',1);

--INSERT PRODUTOS FILMES
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Action Figure Tartarugas Ninjas','Action Figure Tartarugas Ninjas o Filme',230.80,'https://universogeeky.com/cdn/shop/files/4_743fff41-3d7d-4a96-8bc6-1981ea6682bf_800x.jpg?v=1706533764',2);
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Action Figure Coringa','Action Figure Coringa Heath Ledger Batman',299.99,'https://images.universohq.com/2012/09/HT_joker20_04.jpg',2);

--INSERTS PRODUTOS JOGOS
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Action Figure Ghost','Estatua Ghost do jogo Call of Duty',499.99,'https://http2.mlstatic.com/D_NQ_NP_793160-MLB74343387029_022024-O.webp',3);
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Action Figure Capitão Price','Estatua capitão Price do jogo Call of Duty',499.99,'hhttps://http2.mlstatic.com/D_NQ_NP_839435-MLB74899522579_032024-O.webp',3);

--INSERTS fUNKOS
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Funko One Piece','Funko Pop One Piece Zoro',129.99,'https://m.media-amazon.com/images/I/515S7xza7OL._AC_SX679_.jpg',4);
insert into public.produto(nome,descricao,preco,url_image,categoria)values ('Funko Doutor Estranho','Funko Pop doutor Estranho Marvel',129.99,'https://geekfanaticos.fbitsstatic.net/img/p/funko-pop-doctor-strange-doutor-estranho-specialty-series-1008-doctor-strange-and-the-multiverse-72783/259270-1.jpg?w=540&h=540&v=no-change&qs=ignore',4);