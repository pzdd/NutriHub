package br.com.nutrihub.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.nutrihub.entidade.Dieta;
import br.com.nutrihub.entidade.Refeicao;
import br.com.nutrihub.util.AbstractBean;
import br.com.nutrihub.util.Mensagens;
import br.com.nutrihub.util.ValidatorUtil;
import br.ufrn.nutrihub.DAO.RefeicaoDAO;

@ManagedBean
@SessionScoped
public class RefeicaoMBean extends AbstractBean{

	
	Refeicao refeicao;
	RefeicaoDAO rDAO;
	List<Refeicao> refeicoes;
	
	public RefeicaoMBean(){
		reset();
	}
	
	public void reset(){
		refeicao = new Refeicao();
		rDAO = new RefeicaoDAO();
		refeicoes = new ArrayList<Refeicao>();
	}
	
	public String cadastroRefeicao(){
		if(validate()){
			return null;
		}
		refeicao.setNutricionista(getUsuarioLogado());
		rDAO.save(refeicao);
		refeicoes.add(refeicao);
		refeicao = new Refeicao();
		adicionarMensagem("Cadastrado com sucesso", Mensagens.INFO);
		
		return null;
		
	}
	
	public void editarRefeicao(){
		rDAO.update(refeicao);
		adicionarMensagem("Editado com sucesso", Mensagens.INFO);
	}
	
	private boolean validate(){
		boolean flag = false;
		if(ValidatorUtil.isEmpty(refeicao.getDescricao())){
			adicionarMensagem("Informe a descrição.", Mensagens.ERRO);
			flag = true;
		}
		if(ValidatorUtil.isEmpty(refeicao.getHorario())){
			adicionarMensagem("Informe o horário", Mensagens.ERRO);
			flag = true;
		}
		if(ValidatorUtil.isEmpty(refeicao.getSubstituto())){
			adicionarMensagem("Informe o substituto", Mensagens.ERRO);
			flag = true;
		}
		return flag;
	}
	
	public void remove(){
		refeicoes.remove(refeicao);
		rDAO.delete(refeicao);
		refeicao = new Refeicao();
		adicionarMensagem("Removido com sucesso", Mensagens.INFO);
	}
	
	public String populaRefeicao(){
		refeicoes = rDAO.findRefeicoes(getUsuarioLogado().getId(), refeicao.getDieta().getId());
		return "refeicao.xhtml";
	}
	
	public Refeicao getRefeicao(){
		return refeicao;
	}
	
	public List<Refeicao> getRefeicoes(){
		return refeicoes;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
	
	public String voltar(){
		return "Logado.xhtml";
	}
}
