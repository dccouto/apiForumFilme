# Desafio Java - REST API - Movie Challenge

O [Movie Challenge] disponibiliza uma API REST para que seus usuários possam trocar informações sobre os filmes que assistiram e sobre os que ainda desejam assistir.


Recursos disponíveis para acesso via API:
* [**Filme**](#reference/filme)
* [**Avaliação**](#reference/avaliação)
* [**Grupo**](#reference/grupo)
* [**Lista De Filme**](#reference/lista)
* [**Usuários**](#reference/usuarios)

## Informações importantes
### Banco de dados
+ A API utiliza banco de dados em memória, não sendo necessário a instalação do mesmo.
+ Ao iniciar a aplicação a API irá popular a base de dados com algumas entradas para teste. O arquivo está no diretório `src/main/resources/data.sql`
+ Usuario padrão pré cadastrado: username `diego@email.com` senha:`123456`
+ Para acessar o Banco de dados utilize o link: http://localhost:8080/h2-console/login.jsp
+ Dados para acesso `JDBC URL: jdbc:h2:mem:movie-challenge` `User Name: sa`



## Métodos
Requisições para a API devem seguir os padrões:
| Método | Descrição |
|---|---|
| `GET` | Retorna informações de um ou mais registros. |
| `POST` | Utilizado para criar um novo registro. |
| `PUT` | Atualiza dados de um registro ou altera sua situação. |
| `DELETE` | Remove um registro do sistema. |



# Autenticação

Nossa API utiliza [basic Auth] como forma de autenticação/autorização.

Para utilizar a API através, você precisará utilizar uma das seguintes opções:

1. Utilizar um dos usuários pré-cadastrado na base de dados:

2. Cadastrar um `email`, `senha` e definir um `nome` de usuário, utilizados na solicitação de acesso;

+ Request (application/json) (`POST`)

    + Body

            {
                "nome": "diego",
                "email": "diego@email.com",
                "senha": "123456" 
            }


*Para realizar o `login` deve-se informa o `e-mail` e a `senha`.*

---


# Group Recursos


# Filme [/api/filme]

Os filmes que podem ser consultados e adicionado nas listas.


### Buscar filme por título (Read) [GET /{titulo}]
	```
    http://localhost:8080/api/filme/cave
    ```
+ Parameters
	+ titulo (required, titulo, `cave`) ... Título do filme
		

+ Response 200 (application/json)
	
	+ Body
	
			{
				"imdbRating": "4.2",
				"imdbID": "tt4915318",
				"Title": "Cave",
				"Year": "2016",
				"Released": "02 Sep 2016",
				"Genre": "Adventure, Crime, Thriller",
				"Director": "Henrik Martin Dahlsbakken",
				"Poster": "https://m.media-amazon.com/images/M/MV5BMDRlNDc1MzktMjE2Yy00MDJkLWE2MzQtMmVhZThkZDgwNzQ5XkEyXkFqcGdeQXVyMzE1NjE5NDA@._V1_SX300.jpg"
			}

+ Response 204 (application/json)


# Usuario [/api/usuario]

Cadastra um novo usuário no sistema


### Criar usuário (Create) [POST /cadastrar]
	```
	http://localhost:8080/api/usuario/cadastrar
	```

+ Attributes (object)

    + nome: nome do usuário (string, required)
    + email: email do usuario que será utilizado para realizar o login (string, required)
	+ senha: senha do usuário (string, required)


+ Request (application/json)

    + Body

			{
				"nome": "user_1",
				"email": "user_1@email.com",
				"senha": "123456" 
			}

+ Response 200 (application/json)


# Avaliação [/api/filme/avaliar]

Cria, consulta e exclui uma avaliação um filme com `nota` e `estrela`

### Buscar avaliaçõs públicas por id do filme (Read) [GET /avaliacoes-por-filme/{idFilme}]
	```
    http://localhost:8080/api/filme/avaliar/avaliacoes-por-filme/1
    ```

+ Parameters
	+ idFilme (required, idFilme, `1`) ... ID do filme
	
+ Response 200 (application/json)

    + Body
	
			[
			  {
				"idAvaliacao": 1,
				"nota": 10,
				"estrela": 5,
				"status": "PUBLICO",
				"usuario": {
				  "idUsuario": 1,
				  "nome": "Diego",
				  "email": "diego@email.com"
				},
				"filme": {
				  "idFilme": 1,
				  "titulo": "Sonic",
				  "imdbID": "tt2622500"
				}
			  }
			]

### Cria avaliação de um Filme (Read) [POST]
	```
    http://localhost:8080/api/filme/avaliar
    ```
+ Parameters
    + nota (required, number, `8`) ... Nota do filme
    + estrela (required, number, `5`) ... Estrelas para o filme
    + status (required, string, `PRIVADO`) ... Status PUBLICO ou PRIVADO
    + filme (object)
		+ imdbID (required, string, `tt0114723`) ... Código imdb do filme


+ Request (application/json)

    + Body

			{
				"nota": 8,
				"estrela": 5,
				"status": "PRIVADO",
				"filme": {
					"imdbID": "tt0114723"
			}

}


+ Response 200 (application/json)

    + Body

			{
				{
				  "idAvaliacao": 6,
				  "nota": 8,
				  "estrela": 5,
				  "status": "PRIVADO",
				  "usuario": {
					"idUsuario": 1,
					"nome": "Diego",
					"email": "diego@email.com"
				  },
				  "filme": {
					"idFilme": 5,
					"titulo": "Trial by Fire",
					"imdbID": "tt0114723"
				  }
				}
			}
		
		
		
### Exclui uma avaliação de nota e estrela do filme (Read) [DELETE /excluir/{idAvaliacao}]
	```
    http://localhost:8080/api/filme/avaliar/excluir/2
    ```
		
+ Parameters
    + idAvaliacao (required, string, `2`) ... Id da avaliação

+ Response 200 (application/json)
	Excluído com sucesso



# Grupo [/api/grupo]

Cria e consulta grupos de discussão

### Cria um grupo publico (Create) [POST /criar]
	```
    http://localhost:8080/api/grupo/criar
    ```
+ Parameters
    + usuarioId (required, number, `1`) ... Id do usuário proprietário do grupo
    + titulo (required, string, `Grupo dos star wars`) ... Título do grupo
    + status (required, string, `PUBLICO`) ... Status PUBLICO



+ Request (application/json)

    + Body
	
			{
				"usuarioId": 1,
				"titulo": "Grupo dos star wars",
				"status": "PUBLICO"
			}
		
+ Response 200 (application/json)
    + Body
	
			{
			  "idGrupoDebate": 17,
			  "titulo": "Grupo dos star wars",
			  "status": "PUBLICO",
			  "usuariosAutorizados": null,
			  "autor": {
				"idUsuario": 1,
				"nome": "Diego",
				"email": "diego@email.com"
			  }
			}


### Cria um grupo privado (Create) [POST /criar]
	```
    http://localhost:8080/api/grupo/criar
    ```
+ Parameters
    + usuarioId (required, number, `1`) ... Id do usuário proprietário do grupo
    + titulo (required, string, `Grupo dos star wars`) ... Título do grupo
    + status (required, string, `PRIVADO`) ... Status PRIVADO
	+ usuariosAutorizados (required, array, [1, 2, 3]) ... Array de ID de usuários autorizados


+ Request (application/json)

    + Body
	
			{
				"usuarioId": "1",
				"titulo": "Grupo Criado pela api rest",
				"status": "PRIVADO",
				"usuariosAutorizados": [1, 2,3]
			}
		
+ Response 200 (application/json)
    + Body
	
			{
			  "idGrupoDebate": 18,
			  "titulo": "Grupo Criado pela api rest",
			  "status": "PRIVADO",
			  "usuariosAutorizados": [
				{
				  "idUsuario": 1,
				  "nome": "Diego",
				  "email": "diego@email.com"
				},
				{
				  "idUsuario": 2,
				  "nome": "Juliana",
				  "email": "juliana@email.com"
				},
				{
				  "idUsuario": 3,
				  "nome": "Vanessa",
				  "email": "vanessa@email.com"
				}
			  ],
			  "autor": {
				"idUsuario": 1,
				"nome": "Diego",
				"email": "diego@email.com"
			  }
			}


### Busca grupos publicos (Read) [GET /buscar-grupos-publicos]
	```
    http://localhost:8080/api/grupo/buscar-grupos-publicos
    ```

+ Response 200 (application/json)
    + Body
	
			[
			  {
				"idGrupoDebate": 1,
				"titulo": "Critica De filme",
				"status": "PUBLICO",
				"usuariosAutorizados": [],
				"autor": {
				  "idUsuario": 1,
				  "nome": "Diego",
				  "email": "diego@email.com"
				}
			  },
			
			  {
				"idGrupoDebate": 5,
				"titulo": "Critica De filme",
				"status": "PUBLICO",
				"usuariosAutorizados": [],
				"autor": {
				  "idUsuario": 2,
				  "nome": "Juliana",
				  "email": "juliana@email.com"
				}
			  }		 
			]


# Lista [/api/lista-filme]

Cria, atualiza e consulta uma lista

### Retorna todas as listas do usuário logado (Read) [GET /participante]
	```
    http://localhost:8080/api/lista-filme/participante
    ```

+ Response 200 (application/json)
    + Body
	
			[
			  {
				"idLista": 1,
				"nomeLista": "Quero Assistir",
				"status": "PRIVADO",
				"usuario": {
				  "idUsuario": 1,
				  "nome": "Diego",
				  "email": "diego@email.com"
				},
				"listaFilmes": [
				  {
					"idFilme": 1,
					"titulo": "Sonic",
					"imdbID": "tt2622500"
				  },
				]
			  },
			  {
				"idLista": 2,
				"nomeLista": "Favoritos",
				"status": "PUBLICO",
				"usuario": {
				  "idUsuario": 1,
				  "nome": "Diego",
				  "email": "diego@email.com"
				},
				"listaFilmes": [
				  {
					"idFilme": 2,
					"titulo": "Star Wars: Episode IV - A New Hope",
					"imdbID": "tt0076759"
				  },
				  {
					"idFilme": 8,
					"titulo": "Inside Out",
					"imdbID": "tt2096673"
				   }
				]
			  }		  
			]



### Retorna todas as listas do Públicas (Read) [GET]
	```
    http://localhost:8080/api/lista-filme/
    ```

+ Response 200 (application/json)
    + Body
	
			[
			  {
				"idLista": 1,
				"nomeLista": "Quero Assistir",
				"status": "PUBLICO",
				"usuario": {
				  "idUsuario": 3,
				  "nome": "Vanessa",
				  "email": "vanessa@email.com"
				},
				"listaFilmes": [
				  {
					"idFilme": 1,
					"titulo": "Sonic",
					"imdbID": "tt2622500"
				  },
				]
			  },
			  {
				"idLista": 2,
				"nomeLista": "Favoritos",
				"status": "PUBLICO",
				"usuario": {
				  "idUsuario": 1,
				  "nome": "Diego",
				  "email": "diego@email.com"
				},
				"listaFilmes": [
				  {
					"idFilme": 2,
					"titulo": "Star Wars: Episode IV - A New Hope",
					"imdbID": "tt0076759"
				  },
				  {
					"idFilme": 8,
					"titulo": "Inside Out",
					"imdbID": "tt2096673"
				   }
				]
			  }		  
			]


### Retorna os filmes da lista filtrados por 'ano' ou 'diretor' (Read) [GET /filtrar/{tipoFiltro}/{filtro}/{idLista}]
	```
    http://localhost:8080/api/lista-filme/filtrar/ano/2013/1
    ```

+ Parameters
    + tipoFiltro (required, string, `ano`) ... Parâmetro para o filtro (ano ou diretor)
    + filtro (required, string, `2013`) ... Ano a ser filtrado
    + idLista (required, string, `1`) ... ID da lista


+ Response 200 (application/json)
    + Body
	
			[
			  {
				"imdbRating": "4.5",
				"imdbID": "tt2622500",
				"Title": "Sonic",
				"Year": "2013",
				"Released": "05 Jan 2013",
				"Genre": "Short, Action, Adventure, Fantasy, Sci-Fi",
				"Director": "Eddie Lebron",
				"Poster": "https://m.media-amazon.com/images/M/MV5BZWJkMTU5MjUtNGFmOC00NDFmLTk3NGYtMDgzMDY1NmI5NmFiXkEyXkFqcGdeQXVyMzYzNzc1NjY@._V1_SX300.jpg"
			  }
			]


### Criar nova lista para o usuário logado (Create) [POST /nova-lista]
	```
    http://localhost:8080/api/lista-filme/nova-lista
    ```

+ Parameters
    + nomeLista (required, string, `Assistindo`) ... Parâmetro para o filtro (ano ou diretor)
    + status (required, string, `PRIVADO`) ... Status PUBLICO ou PRIVADO

+ Request (application/json)

    + Body
	
			{
				"nomeLista" : "Assistindo",
				"status" : "PRIVADO"	
			}

+ Response 200 (application/json)
    + Body
	
			{
			  "idLista": 20,
			  "nomeLista": "Assistindo",
			  "status": "PRIVADO",
			  "usuario": {
				"idUsuario": 2,
				"nome": "Juliana",
				"email": "juliana@email.com"
			  },
			  "listaFilmes": []
			}



### Adicionar filme na lista do usuário logado (Create) [POST /adicionar-filme/{idLista}]
	```
    http://localhost:8080/api/lista-filme/adicionar-filme/1
    ```

+ Parameters
    + idLista (required, number, `1`) ... ID la lista
    + imdbID (required, string, `tt2096673`) ... imdb do filme a ser inserido

+ Request (application/json)

    + Body
	
			{
				"imdbID": "tt2096673"
			}

+ Response 200 (application/json)
    + Body
	
			{
			  "idLista": 1,
			  "nomeLista": "Quero Assistir",
			  "status": "PUBLICO",
			  "usuario": {
				"idUsuario": 1,
				"nome": "Diego",
				"email": "diego@email.com"
			  },
			  "listaFilmes": [
			   {
				  "idFilme": 8,
				  "titulo": "Inside Out",
				  "imdbID": "tt2096673"
				}
			  ]
			}


### Altera a visibilidade da lista do usuário logado (Update) [PUT /alterar-visibilidade/{idLista}]
	```
    http://localhost:8080/api/lista-filme/alterar-visibilidade/1
    ```

+ Parameters
    + idLista (required, number, `1`) ... ID la lista
	+ status (required, number, `PRIVADO`) ... Status PUBLICO ou PRIVADO
  
+ Request (application/json)

    + Body
	
			{
				"status": "PRIVADO"
			}


+ Response 200 (application/json)
    + Body
	
			{
			  "idLista": 1,
			  "nomeLista": "Quero Assistir",
			  "status": "PRIVADO",
			  "usuario": {
				"idUsuario": 1,
				"nome": "Diego",
				"email": "diego@email.com"
			  },
			  "listaFilmes": [
			   {
				  "idFilme": 8,
				  "titulo": "Inside Out",
				  "imdbID": "tt2096673"
				}
			  ]
			}
