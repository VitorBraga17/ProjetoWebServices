# ProjetoWebServices
Projeto da ultima unidade de Sistemas distribuidos.

# ProjetoFinalSistemasDistribuídos
 
## Projeto DSCalculator Idealizado Por:
- Alexandre de Moraes Furtado
- Daniel Teófilo Alves do Nascimento
- João Vitor de Araújo Braga
- Joe Álefe de Oliveira Almeida
- Vinicius de Souza Silva

## Objetivo
O Projeto Tem como Objetivo a construção de um sistema distribuído com a utilização de uma  api rest para unificar servidores MultiThread, esses servidores tem como função fazer as operações mais simples de uma calculadora, nela será aceito no máximo dois operandos e um operador, somado a isso, o programa vai fazer os seguintes procedimentos:

Servidor A
- Soma;			
- Subtração;		
- Multiplicação;		
- Divisão;

Servidor B
- Porcentagem;
- Raiz Quadrada;
- Potenciação.

## Sevidores e Portas
Os servidores e o cliente são carregados locamente no sistema("localhost)
As portas utilizadas foram: 5056, para as funções de operações básicas(soma,subtração,multiplicação e divisão) e, 5057,para operações complexas(exponenciação,raiz, e porcentagem) e a 8081, para acesso ao webservice.
## Protocolo
O Protocolo é construído em 2 etapas, sendo elas:

-Escolha de Servidor:
 O cliente precisa escolher entre as opções 'somar', 'subtrair', 'multiplicar', 'dividir', 'porcentagem', 'raiz', 'potenciacao', sendo necessário utilizar como endpoints, que é a URL para a qual você envia (POST).
Por exemplo: a URL local seria, http://localhost:8081, os endpoints seriam: 
SERVIDOR A: '/somar', '/subtrair', '/multiplicar', '/dividir', 
SERVIDOR B: '/porcentagem', '/raiz', '/potenciacao'. 
Ficando assim a URL: 
http://localhost:8081/somar
 http://localhost:8081/subtrair
http://localhost:8081/raiz 

- Informando os Operandos:
Para informar os operandos de operações binárias, é necessário criar as chaves "num1" e "num2" no body do request. Em seguida serão escolhidos como valor os números selecionados, respectivamente.
Para a operação Unária de Raiz Quadrada, será necessário informar somente uma chave, que terá nome "num1" , e o valor requisitado.

-Gerar Operação:
 O Cliente deve decidir fazer a entrada de dados, digitando a operação que deseja conforme vai ser explicado no tópico "Como executar o projeto", seguindo um padrão para 2 operandos.
 
 ## Como executar o projeto
Para executar o projeto, é necessário:
 - JAVA SE RUNTIME em versão 15 ou superior.

Para rodar no Windows, deve-se primeiro baixar o projeto e executar o Server, ServerB e o main, logo abra o Postman( você deve possuir o Postman). No Postman vá até o icone '+' e clique nele, ele fica do lado dos nomes ‘new’ e ‘import’, ao clicar, criará um aba, nessa aba, você terá que selecionar o método ‘POST’(por padrão vem a opção ‘GET’), logo após isso digite a url  'localhost:8081', é nessa parte que você seleciona a operação que deseja fazer, basta acrescentar uma '/' e digitar a operação, como foi descrito em “ESCOLHA DE SERVIDOR”. Em seguida vá até a opção 'Body' e selecione a opção chamada "x-www-form-urlencoded", embaixo, você deverá ir em KEY e digitar num1 e na opção VALUE que está a direita, digite o primeiro operando, vá para próxima linha e digite num2 e na opção VALUE que está a direita, digite o segundo operando, ao fazer isso, basta apertar no botão 'SEND', em seguida será apresentado o resultado da operação que desejou fazer. 
