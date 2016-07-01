package br.com.nutrihub.util;

import javax.faces.application.FacesMessage;

public enum Mensagens {
	ERRO(FacesMessage.SEVERITY_ERROR),
	INFO(FacesMessage.SEVERITY_INFO),
	WARNING(FacesMessage.SEVERITY_WARN);
	
	private final FacesMessage.Severity tipoMsg;
	
	Mensagens(FacesMessage.Severity msg){
		tipoMsg = msg;
	}
	
	public FacesMessage.Severity getTipo(){
		return tipoMsg;
	}
}
