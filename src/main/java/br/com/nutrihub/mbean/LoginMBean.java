package br.com.nutrihub.mbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.nutrihub.entidade.Nutricionista;
import br.com.nutrihub.util.SessionUtil;
import br.ufrn.nutrihub.DAO.NutriDAO;


@ManagedBean
@RequestScoped
public class LoginMBean {
	
	private String login;
	private String senha;
	private NutriDAO nDao = new NutriDAO();
	
	public String validaLogin(){
		if(nDao.loginNutri(getLogin(), getSenha())){
			Nutricionista nutri = new Nutricionista();
			nutri = nDao.findByLoginSenha(getLogin(), getSenha());
			SessionUtil.setParam("USUARIOLogado", nutri);
			
			return "/Logado.xhtml?faces-redirect=true";
		}else{
			adicionarMensagem("INFO", "Login/Senha Inválido", FacesMessage.SEVERITY_ERROR);
			return null;
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void adicionarMensagem(String sumario,String detalhe, FacesMessage.Severity tipoErro){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
		context.addMessage(null, message);
	}

}
