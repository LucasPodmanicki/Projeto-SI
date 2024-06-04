## Sistema de Venda de Produtos Online 
Este projeto é uma implementação de um sistema de venda de produtos online, semelhante a Amazon, Magalu e MercadoLivre. O sistema foi desenvolvido para atender aos requisitos de um modelo clássico de e-commerce, 
incluindo a realização de compras e a visualização de extratos de vendas. O projeto foi desenvolvido como parte dos requisitos da disciplina de Engenharia de Software do curso de Ciência da Computação.

    
## Descrição
<p>O sistema de venda de produtos online é responsável por gerenciar a compra e venda de produtos em um ambiente distribuído. Ele possui as seguintes funcionalidades principais:</p>

<h3>Realizar Compra</h3>
<p>Um usuário pode buscar produtos, visualizar detalhes, adicionar produtos ao carrinho, escolher a quantidade e realizar o pagamento.</p>

<h3>Visualizar Extrato de Venda</h3>
<p>O proprietário da loja pode visualizar uma lista de pedidos com detalhes dos produtos vendidos, quantidades, compradores e datas.</p>

<h2>Arquitetura</h2>
<p>A arquitetura do sistema é baseada em camadas (boundary, control e entity) e segue os princípios de sistemas distribuídos para garantir que múltiplos usuários possam realizar compras e consultas simultaneamente. A implementação utiliza uma arquitetura Cliente/Servidor e pode ser escalada para uma arquitetura distribuída usando MPI.</p>

## Requisitos
  - [x] 2 clientes comprando aleatoriamente de 2 a 4 produtos dos 5 produtos existentes,sendo que existem apenas 1 item de cada produto disponível para venda;
  - [x] 10 clientes comprando aleatoriamente de 2 a 4 produtos dos 10 produtos existentes,sendo que existem 5 itens de cada produto disponível para venda;
  - [x] 1000 clientes comprando aleatoriamente 1 produto de um total de 10 produtos disponíveis, com 100 itens de cada produto em estoque

 ## Tecnologias Utilizadas
   - [x] Python
   - [x] C#
   - [x] Shell Script

## Autores
| <img src="https://avatars.githubusercontent.com/u/104178669?v=4" alt="Lucas" width="150"/> | <img src="https://avatars.githubusercontent.com/u/57364626?v=4" alt="Gabriel" width="150"/> | <img src="https://avatars.githubusercontent.com/u/102989290?v=4" alt="João" width="150"/> | <img src="https://avatars.githubusercontent.com/u/64386249?v=4" alt="Leonardo" width="150"/> |
|:-------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------:|---------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| [Lucas Podmanicki Fernandes](https://github.com/LucasPodmanicki)                           | [Gabriel Brito Schanz](https://github.com/gbschanz)                                      | [João Pedro Barbieri](https://github.com/vihmar)                                         | [Leonardo Barrionuevo](https://github.com/Leocandido)                                       |
| R.A: 22.121.007-3                                                                        | R.A: 22.119.010-1                                                                        | R.A: 22.120.049-6                                                                         | R.A: 22.121.034-7                                                                         |
***
