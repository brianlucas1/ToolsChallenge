package tools.sofware.main.service;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Service;

import tools.sofware.main.dto.DadosTransacaoDTO;
import tools.sofware.main.dto.DescricaoDTO;
import tools.sofware.main.dto.FormaPagamentoDTO;
import tools.sofware.main.dto.PagamentoDTO;
import tools.sofware.main.excpetion.NãoExistePagamentoParaEstornoExecption;
import tools.sofware.main.excpetion.ValorPagamentoMenorQueZero;
import tools.sofware.main.model.DadosTransacaoModel;
import tools.sofware.main.model.DescricaoModel;
import tools.sofware.main.model.FormaPagamentoModel;
import tools.sofware.main.model.TransacaoModel;
import tools.sofware.main.util.Util;


@Service
public class PagamentoService {
	
	Util utils = new Util();
	
	
	public DadosTransacaoModel fazPagamento(PagamentoDTO pgtoDTO, ArrayList<DadosTransacaoModel> pgtos) {
		
		boolean jaEstaPago = verificaSeJaFoiPago(pgtoDTO,pgtos);
		
		DadosTransacaoModel dadosModel = new DadosTransacaoModel();
		
		if(!jaEstaPago){			
			 	
			dadosModel =  setDadosRetornoPagamento(pgtoDTO);
			
		}else{
			dadosModel = getPagamento(pgtoDTO,pgtos);
		}
		return dadosModel;

	}

	private DadosTransacaoModel getPagamento(PagamentoDTO pgtoDTO, ArrayList<DadosTransacaoModel> pgtos) {
		
		DadosTransacaoModel dadosModel = new DadosTransacaoModel();
		
		
		for(int i=0; i < pgtos.size(); i++){
			
			if(pgtoDTO.getTransacao().getId().equals(pgtos.get(i).getTransacao().getId())){
				dadosModel.setTransacao(pgtos.get(i).getTransacao());
			}
		}
			
		
		return dadosModel;
	}

	private boolean verificaSeJaFoiPago( PagamentoDTO pgtoDTO, ArrayList<DadosTransacaoModel> pgtos) {
		
		boolean jaEstaPago = false;
		
		if(pgtos != null){
			
			for(int i=0; i < pgtos.size(); i++){
				
				if(pgtoDTO.getTransacao().getId().equals(pgtos.get(i).getTransacao().getId())){
					jaEstaPago =  true;
				}
		}		
			
		}		
		return jaEstaPago;
				
	}

	private DadosTransacaoModel setDadosRetornoPagamento(PagamentoDTO pgtoDTO) {
		
		DadosTransacaoModel dadosModel = new DadosTransacaoModel();
		
		TransacaoModel trans = setDadosTransacao(pgtoDTO.getTransacao());
		
		dadosModel.setTransacao(trans);
		
		return dadosModel;
	}

	private TransacaoModel setDadosTransacao(DadosTransacaoDTO transacao) {		
		
		TransacaoModel trans = new TransacaoModel();
		
		trans.setCartao(transacao.getCartao());
		trans.setId(transacao.getId());
		
		DescricaoModel desc = setDadosDesc(transacao.getDescricao());		
		trans.setDescricao(desc);			
		
		FormaPagamentoModel pag = setDatdosPagamento(transacao.getFormaPagamento());
		trans.setFormaPagamento(pag);
		
		return trans;
	}

	private FormaPagamentoModel setDatdosPagamento(	FormaPagamentoDTO formaPagamento) {

		FormaPagamentoModel pgto = new FormaPagamentoModel();
		
		pgto.setParcelas(formaPagamento.getParcelas());
		pgto.setTipo(formaPagamento.getTipo());		
		
		return pgto;
	}

	private DescricaoModel setDadosDesc(DescricaoDTO descricao) {
				
		DescricaoModel desc = new DescricaoModel();
		
		desc.setCodigoAutorizacao(utils.geraCodigoAutorizacao());
		desc.setDataHora(descricao.getDataHora());
		desc.setEstabelecimento(descricao.getEstabelecimento());
		desc.setNsu(utils.geraCodigoAutorizacao());
		desc.setValor(utils.stringToDouble(descricao.getValor()));
		desc.setStatus("AUTORIZADO");
		
		return desc;
	}

	public DadosTransacaoModel findPagamento(ArrayList<DadosTransacaoModel> pgtos, String id) {
		
		DadosTransacaoModel dadosModel = new DadosTransacaoModel();
		
		for(int i=0; i < pgtos.size(); i++){
			
			if(id.equals(pgtos.get(i).getTransacao().getId())){
				dadosModel.setTransacao(pgtos.get(i).getTransacao());
			}
		}
				
		return dadosModel;
	}

	public DadosTransacaoModel fazEstorno(ArrayList<DadosTransacaoModel> pgtos,
			String id) throws NãoExistePagamentoParaEstornoExecption {
		
		DadosTransacaoModel dadosModel = findPagamento(pgtos, id);
		
		if(dadosModel != null){
			dadosModel.getTransacao().getDescricao().setStatus("CANCELADO");;
		}else{
			throw new NãoExistePagamentoParaEstornoExecption();
		}
		
		return dadosModel;
	}

	public ArrayList<DadosTransacaoModel> insertLista(
			DadosTransacaoModel respPgto, ArrayList<DadosTransacaoModel> pgtos) {
		
		boolean jaExiste = false;
		
		if(pgtos.size() == 0){
			
			pgtos.add(respPgto);
			
		}else{
			for(int i =0; i < pgtos.size(); i++){
				if(respPgto.getTransacao().getId().equals(pgtos.get(i).getTransacao().getId())){
					jaExiste = true;
				}
			}
			
			if(!jaExiste){
				pgtos.add(respPgto);
			}
		}
		
		return pgtos;
	}

	public double validaValorPagamento(DadosTransacaoModel pag) throws ValorPagamentoMenorQueZero {
		
		
		double valor = pag.getTransacao().getDescricao().getValor();
		
		if(valor == 0.0){
			throw new ValorPagamentoMenorQueZero();
		}
		
		
		return valor;
	}

	public boolean verificaPagamentoCancelado(String status) {
		
		if(status.equals("CANCELADO")){
			return  true;
		}
		
		return false;
	}


}
