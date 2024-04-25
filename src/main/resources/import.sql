
--INSERT CATEGORIAS
insert into public.tb_categoria(categoria) values ('camisetas');
insert into public.tb_categoria(categoria) values ('calças');


--INSERT PRODUTOS
insert into public.tb_produto(nome,descricao,preco,url_image,tb_categoria)values ('Camisa Masculina','Camisa Masculina',100.50,'imagem camisa masculina',1);
insert into public.tb_produto(nome,descricao,preco,url_image,tb_categoria)values ('Camisa Feminina','Camisa Feminina',110.00,'imagem camisa feminina',2);

