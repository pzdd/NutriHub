package br.com.nutrihub.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.nutrihub.entidade.Nutricionista;

public abstract class AbstractBean {
	
	public void adicionarMensagem(String mensagem, Mensagens tipoErro){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(tipoErro.getTipo(), "INFO", mensagem);
		context.addMessage(null, message);
	}
	
	public Nutricionista getUsuarioLogado(){
		return ((Nutricionista) SessionUtil.getParam("USUARIOLogado"));
	}
}
