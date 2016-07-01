package br.com.nutrihub.mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.nutrihub.entidade.Nutricionista;
import br.com.nutrihub.util.AbstractBean;
import br.com.nutrihub.util.Mensagens;
import br.com.nutrihub.util.ValidatorUtil;
import br.ufrn.nutrihub.DAO.NutriDAO;

@ManagedBean
@SessionScoped
public class CadastroMBean extends AbstractBean{
	
	Nutricionista nutri = new Nutricionista();
	NutriDAO nDAO = new NutriDAO();
	
	public String cadastrarNutricionista(){
		
		if(validate()){
			return null;
		}
		nDAO.save(nutri);
		adicionarMensagem("Cadastro efetuado com sucesso.", Mensagens.INFO);
		
		return "Login.xhtml";
	}
	
	public boolean validate(){
		boolean flag = false;
		if(ValidatorUtil.isEmpty(nutri.getNome())){
			adicionarMensagem("Informe o nome", Mensagens.ERRO);
			flag = true;
		}
		if(ValidatorUtil.isEmpty(nutri.getLogin())){
			adicionarMensagem("Informe o login", Mensagens.ERRO);
			flag = true;
		}
		if(ValidatorUtil.isEmpty(nutri.getSenha())){
			adicionarMensagem("Informe a senha", Mensagens.ERRO);
			flag = true;
		}
		if(ValidatorUtil.isEmpty(nutri.getCRN())){
			adicionarMensagem("Informe o nome", Mensagens.ERRO);
			flag = true;
		}
		if(!ValidatorUtil.isEmpty(nDAO.findByLoginSenha(nutri.getLogin(), nutri.getSenha()))){
			adicionarMensagem("Esse usuário já existe", Mensagens.ERRO);
			flag = true;
		}
		return flag;
	}
	
	public Nutricionista getNutri(){
		return nutri;
	}
	
	public String voltar(){
		return "Login.xhtml";
	}
}
