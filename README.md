# Conversor de Moedas (Java)

Aplicação em Java (console) que consulta cotações em uma API externa e realiza conversões entre moedas a partir da taxa mais recente.

## Sobre o projeto
Este projeto consome a ExchangeRate-API, que retorna um JSON contendo as taxas de conversão a partir de uma moeda base (ex.: USD).  
O JSON retornado é desserializado com Gson para um objeto Java (`ExchangeResponse`) que expõe um `Map<String, Double>` com as taxas (`conversion_rates`).

## Funcionalidades
- Menu interativo via console.
- Consulta de moedas disponíveis (com base nas chaves do `conversion_rates`).
- Conversão de valores entre moedas (ex.: `USD -> BRL`).
- Histórico simples das conversões (lista de entradas feitas pelo usuário).

## Tecnologias e conceitos
- Java `HttpClient` + `HttpRequest` + `HttpResponse` para requisições HTTP.
- Gson para transformar JSON em objetos Java (desserialização).
- `Map.containsKey()` / `Map.get()` para validar se o código da moeda existe na resposta e evitar `NullPointerException`.

## Como executar
1. Clone o repositório:
```
git clone https://github.com/AndersonJJR/ConversorDeMoedas.git
```
2. Abra no IntelliJ/Eclipse.
3. Configure sua API key.
4. Execute a classe `Main` e use o menu no console.

## Como a conversão funciona
A conversão segue esta lógica:
1. O usuário informa a **moeda base** (ex.: `USD`).
2. O programa chama `.../latest/USD`, e a API devolve `conversion_rates` (um dicionário de moeda -> taxa).
3. Para converter, o programa busca a taxa no mapa:
- `taxa = conversion_rates.get("BRL")`
- `valorConvertido = quantidade * taxa`

## Melhorias futuras (ideias)
- Guardar a última resposta da API para não chamar a API a cada conversão.
- Separar camadas (UI / Service / Client HTTP / Model).
- Testes unitários para validação de entrada.

## Licença
Projeto educacional para estudo de consumo de APIs e desserialização JSON em Java.

## Autor

<div align="center">
  <img src="https://media.licdn.com/dms/image/v2/D5603AQFaKLpTsMOtAg/profile-displayphoto-scale_200_200/B56ZiUyQ2AHcAY-/0/1754842862730?e=1767830400&v=beta&t=FpElM_3MscAsGDDbFiizuZOlVf5dsD3W42DP4wkFLT0" alt="Foto de Anderson da Silva Chaves Júnior" width="150" style="border-radius: 50%;">
  <br><br>
  <strong>Anderson da Silva Chaves Júnior</strong>
  <br>
  <em>Desenvolvedor Java em evolução, estudando consumo de APIs e boas práticas de orientação a objetos.</em>
</div>

