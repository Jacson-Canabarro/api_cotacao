# Api  de cotacao

# Introdução

API desenvolvida em Java 8 com stack do Spring framework, foram utilizados, Spring boot,String security, Spring data JPA, Spring WEB e o banco de dados relacional MySQL.
Todos os EndPoints tem respostas no formato JSON, para alguns casos é obrigadotório os parâmetros para realizar as consultas.

O depploy foi realizado em uma maquina virtual EC2 na AWS e pode ser acessada pelo 
link http://minhaapi-env.eba-83gccam8.us-east-1.elasticbeanstalk.com

já o banco de dados foi criado em outra instância da AWS, utilizando o serviço de RDS.
## Casos de Uso

Especificamente as consultas resultam em cotação de pares de moedas distintas, relacionadas e disponibilizadas pela https://exchangeratesapi.io/ .
## Autorização

Para facilitar os testes e desenvolvimento, API foi protegida com Spring security, utilizando o padrão Basic Auth. Ex: Login e senha padrão.
O protocolo de transferência utilizado é o HTTP, por motivos adversos, preferi mante-lo, já que o mudar para o HTTPs precisaria de mais tempo.
```http
GET http://minhaapi-env.eba-83gccam8.us-east-1.elasticbeanstalk.com/cotacao
```
```http
GET http://minhaapi-env.eba-83gccam8.us-east-1.elasticbeanstalk.com/cotacao/moedas
```

```http
POST http://minhaapi-env.eba-83gccam8.us-east-1.elasticbeanstalk.com/cotacao
```

| Parametros | Tipo | Descrição |
| :--- | :--- | :--- |
| `username e password`:  | `string` | **Requerido**. Para acessar a doc e a API |

## Resposta

A resposta padrão foi disponibilizado em JSON (application/json) para facilitar a integração com client (Vue.js)
```javascript
{
      "dataCotacao": "string",
      "moedaBase": "string",
      "moedaFinal": "string",
      "quantidade": number,
      "total_convertido": "string",
      "cotacao_dia": "string",
      "dataConsulta": "date"
}
```

A `dataCotacao` atributo que contem a data efetiva da cotação.

A `moedaBase` atributo que contem a moeda base para a consulta.

A `moedaFinal` atributo que contem a moeda que será feita a conversão.

A `total_convertido` atributo que contem o somatório da quantidade * o valor convertido da `moedaFinal`.

A `cotacao_dia` atributo que contem o valor da cotação pela `dataCotacao`.

A `dataConsulta` atributo que contem a data que a consulta foi feita, não necessariamente precisa ser a mesma da `dataCotacao`.


## Status Codes

A API pode retornar os seguintes codigos: 

| Status Code | Description |
| :--- | :--- |
| 200 | `OK` |
| 201 | `CREATED` |
| 400 | `BAD REQUEST` |
| 404 | `NOT FOUND` |
| 500 | `INTERNAL SERVER ERROR` |



