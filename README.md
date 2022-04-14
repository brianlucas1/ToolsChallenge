# ToolsChallenge

MicroServiço Criado para Consumir um JSON com finalidades de Pagamentos.


# CHAMADA PARA EFETUAR O PAGAMENTO.

POST: http://localhost:8080/pagamento
BODY :  {
	"transacao":{
		"cartao": "1",
		"id":  "1",
		"descricao":{
			"valor": "500.50",
			"dataHora": "2",
			"estabelecimento": "teste"
		},
		"formaPagamento":{
			"tipo":"AVISTA",
			"parcelas": "1"
		}
	}
}


O serviço está preparado para realizar um pagamento pelo parametro do ID da requisição.
Caso esse Id já esteja pago é retornado o Json  com o pagamento efetuado e status 200 - OK 
Caso o pagamento ainda esteja pendente e dado baixa e com o retorno created
Caso de algum problema durante o pagamento é lançado um badRequest.

#CHAMDA PARA RETORNAR TODOS PAGAMENTOS 

GET: http://localhost:8080/getpagamentos
É retornado a lista de todos os pagamentos feitos e cancelados tbm


#CHAMDA PARA RETORNAR PAGAMENTO POR ID 

GET: http://localhost:8080/getpagamentos/id
VERIFICA SE EXISTE UM PAGAMENTO COM ESSE ID, CASO EXISTE RETONE CREATED CASO NÃO BAD REQUEST.

#CHAMADA PARA FAZER ESTORNO DE UM PAGAMENTO

GET: http://localhost:8080/setestorno/id
VERIFICA SE EXISTE UM PAGAMENTO PARA ESSE ID CASO NÃO EXISTA LANÇA EXECESSÃO
CASO EXISTA FAZ O UPDATE DAQUELE PAGAMENTO E O RETORNA COMO JSON



