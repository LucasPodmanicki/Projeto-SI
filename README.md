## Sistema de Venda de Produtos Online 
Este projeto é uma implementação de um sistema de venda de produtos online, semelhante a Amazon, Magalu e MercadoLivre. O sistema foi desenvolvido para atender aos requisitos de um modelo clássico de e-commerce, 
incluindo a realização de compras e a visualização de extratos de vendas.

    
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
   - [x] Java
   - [x] Shell Script

## Modelo dos Casos de Uso
Ator Principal:
- Usuário.

Casos de Uso:

**1. Realizar Compra**
- Descrição: O usuário procura por um produto, visualiza seus detalhes, adiciona ao carrinho de compras, escolhe a quantidade e realiza o pagamento.
- Fluxo Principal:
  - O usuário busca por um produto.
  - O sistema exibe os detalhes do produto.
  - O usuário adiciona o produto ao carrinho.
  - O usuário escolhe a quantidade desejada.
  - O usuário realiza o pagamento.

- Extensões:
  - Erro de pagamento.
  - Produto não disponível.
 
**2. Visualizar Extrato de Venda**
- Descrição: O proprietário da loja visualiza a lista de pedidos com produtos, quantidades, clientes e datas de compra.
- Fluxo Principal:
  - O proprietário acessa o sistema.
  - O sistema exibe o extrato de vendas.

- Extensões:
  - Nenhum pedido realizado ainda.
 
**3. Gerenciar Pagamentos**
- Descrição: Este caso de uso permite que o administrador da loja gerencie as transações de pagamento realizadas através do sistema.
- Fluxo Principal:
  - Verificar transações realizadas.
  - Realizar reembolsos, se necessário.
  - Visualizar relatórios de pagamentos.

- Extensões:
  - Nenhum pagamento realizado ainda.

 ![image](https://github.com/LucasPodmanicki/Projeto-SI/assets/104178669/244968c9-62ef-44c9-baec-139aceeca55e)

  

## Modelo de Domínio
- Produto
- Carrinho de Compras
- Pedido
- Usuário

![image](https://github.com/LucasPodmanicki/Projeto-SI/assets/104178669/19db7a42-0617-4e74-b889-5b491714c1c6)


## Diagramas de Sequência
Para os fluxos principais dos casos de uso "Realizar Compra" e "Visualizar Extrato de Venda", serão criados diagramas de sequência que representam a interação entre as diferentes camadas da arquitetura (boundary, control e entity).

**Diagrama de Sequência para "Realizar Compra**

1. Boundary: Interface do Usuário
2. Control: Controlador de Compra
3. Entity: Entidades (Usuário, Produto, Pedido, Carrinho, Pagamento)

![image](https://github.com/LucasPodmanicki/Projeto-SI/assets/104178669/5c4da212-ddd3-46fe-a2a8-8fd35a268651)


**Diagrama de Sequência para Visualizar Extrato de Venda**

1. Boundary: Interface do Proprietário
2. Control: Controlador de Venda
3. Entity: Entidades (Pedido, Produto)

![image](https://github.com/LucasPodmanicki/Projeto-SI/assets/104178669/9c445234-14d6-4527-94d9-b5bc33666651)



## Como Executar

   Siga estas etapas para executar este projeto:

1. **Clonar o Repositório**

   Para clonar o reposítorio utilizando o comando ` git clone https://github.com/LucasPodmanicki/Projeto-SI.git`
   
2. **Instalar o Java**

   Primeiro, você precisa ter o Java instalado em sua máquina. Se ainda não o instalou, você pode baixar o Java [aqui](https://www.oracle.com/br/java/technologies/downloads/)

3. **Instalar o Python**

   Em seguida, você precisa ter o Python instalado em sua máquina. Se ainda não o instalou, você pode baixar o Python [aqui](https://www.python.org/downloads/)

5. **Instalar Dependências do Python**

   Certifique-se de instalar todas as bibliotecas necessárias para rodar os scripts em Python.

6. **Rodar os Arquivos**

   Após as instalações, rode os arquivos, primeiro execute o arquivo .bat do Servidor, em sequencia, execute o .bat do Cliente (Certifique-se de que o servidor está em execução antes de iniciar o cliente.)

7. **Funcionamento**

   Se todos os passos foram seguidos corretamente, seu código está pronto para uso! Faça bom proveito :)


   

## Vídeo executando o código (Servidor - computador 1)
[https://youtu.be/BZEi0q3XaTg](https://www.youtube.com/watch?v=BZEi0q3XaTg)

## Vídeo executando o código (Cliente - computador 2)
[https://www.youtube.com/watch?v=gzwTPAw_nP4](https://www.youtube.com/watch?v=gzwTPAw_nP4)

## Conclusão
O desenvolvimento deste sistema de vendas online proporcionou uma abordagem estruturada e modularizada para atender às necessidades dos usuários e proprietários de lojas. A análise detalhada dos requisitos, a modelagem dos casos de uso e a implementação de diagramas de sequência garantiram uma compreensão dos fluxos de interação do sistema. A validação por meio de cenários de teste assegurou a integridade e precisão das transações. Esta solução oferece uma experiência de compra e vendas eficiente.

## Autores
| <img src="https://avatars.githubusercontent.com/u/104178669?v=4" alt="Lucas" width="150"/> | <img src="https://avatars.githubusercontent.com/u/57364626?v=4" alt="Gabriel" width="150"/> | <img src="https://avatars.githubusercontent.com/u/102989290?v=4" alt="João" width="150"/> | <img src="https://avatars.githubusercontent.com/u/64386249?v=4" alt="Leonardo" width="150"/> |
|:-------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------:|---------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| [Lucas Podmanicki Fernandes](https://github.com/LucasPodmanicki)                           | [Gabriel Brito Schanz](https://github.com/gbschanz)                                      | [João Pedro Barbieri](https://github.com/vihmar)                                         | [Leonardo Barrionuevo](https://github.com/Leocandido)                                       |
| R.A: 22.121.007-3                                                                        | R.A: 22.119.010-1                                                                        | R.A: 22.120.049-6                                                                         | R.A: 22.121.034-7                                                                         |
***
