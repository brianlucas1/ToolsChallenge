package tools.sofware.main.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tools.sofware.main.dto.PagamentoDTO;
import tools.sofware.main.excpetion.NãoExistePagamentoParaEstornoExecption;
import tools.sofware.main.model.DadosTransacaoModel;
import tools.sofware.main.service.PagamentoService;


@RestController
public class PagamentosController {
	
	@Autowired
	PagamentoService pgtoService = new PagamentoService();
	
	ArrayList<DadosTransacaoModel> pgtos = new ArrayList<DadosTransacaoModel>();
	
	@PostMapping("/pagamento")
	@ResponseStatus()
	public  ResponseEntity<DadosTransacaoModel> fazPagamento( @RequestBody  @Valid   PagamentoDTO pgtoDTO){
		
		DadosTransacaoModel respPgto = pgtoService.fazPagamento(pgtoDTO,pgtos);
		
		if(respPgto != null){			
			
			if(pgtos.size() == 0 ){				
				pgtos = pgtoService.insertLista(respPgto,pgtos);
				return new ResponseEntity<>(respPgto, HttpStatus.CREATED);				
			}else{
				pgtos = pgtoService.insertLista(respPgto,pgtos);
				
				if(pgtos.size() >= 1){
					
					for(int i=0; i < pgtos.size(); i++){
						if(pgtos.get(i).getTransacao().getId().equals(pgtoDTO.getTransacao().getId())){
							return new ResponseEntity<>(respPgto, HttpStatus.OK);
						}
					}
				}
				
			}
			
		}else{
			return new ResponseEntity<>(respPgto, HttpStatus.BAD_REQUEST);
		}
		return null;
	
	}
	
	@GetMapping("/getpagamentos")
	@ResponseStatus(HttpStatus.OK)
	public ArrayList<DadosTransacaoModel> getPagamentos(){
		
		return pgtos;
	}
	
	@GetMapping("/getpagamentos/{id}")
	public ResponseEntity<DadosTransacaoModel>  getPagamento(@PathVariable String id){
		
		DadosTransacaoModel respPgto = pgtoService.findPagamento(pgtos,id);
		
		if(respPgto.getTransacao() != null){
			return new ResponseEntity<>(respPgto, HttpStatus.CREATED);	
		}else{
			return new ResponseEntity<>(respPgto, HttpStatus.BAD_REQUEST);
		}		
	}
	@GetMapping("/setestorno/{id}")
	public  ResponseEntity<DadosTransacaoModel> setEstorno(@PathVariable String id) throws NãoExistePagamentoParaEstornoExecption{
		
		DadosTransacaoModel respPgto = pgtoService.fazEstorno(pgtos,id);
		
		return new ResponseEntity<>(respPgto, HttpStatus.OK);	
	}

}
