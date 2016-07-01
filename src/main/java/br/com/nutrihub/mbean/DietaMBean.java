package br.com.nutrihub.mbean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.iterators.EntrySetMapIterator;

import br.com.nutrihub.entidade.Dieta;
import br.com.nutrihub.entidade.Nutricionista;
import br.com.nutrihub.entidade.Refeicao;
import br.com.nutrihub.util.AbstractBean;
import br.com.nutrihub.util.Mensagens;
import br.com.nutrihub.util.SessionUtil;
import br.com.nutrihub.util.ValidatorUtil;
import br.ufrn.nutrihub.DAO.DietaDAO;
import br.ufrn.nutrihub.DAO.NutriDAO;

@ManagedBean
@SessionScoped
public class DietaMBean extends AbstractBean{
	
	
	Dieta dieta = new Dieta();
	DietaDAO dDAO = new DietaDAO();
	NutriDAO nDAO = new NutriDAO();
	List<Dieta> dietas = new ArrayList<Dieta>();
	Map<Dieta,List<Refeicao>> map = new HashMap<Dieta,List<Refeicao>>();
	
	public DietaMBean(){
		map = dDAO.findAll(getUsuarioLogado().getId());
	}
	
	public String cadastroDieta(){
		if(validate()){
			return null;
		}
		dieta.setNutricionista(nDAO.findById(getUsuarioLogado().getId(), Nutricionista.class));
		dDAO.save(dieta);
		dieta = new Dieta();
		//atualiza a lista
		map = dDAO.findAll(getUsuarioLogado().getId());
		
		adicionarMensagem("Cadastro efetuado com sucesso", Mensagens.INFO);
		return null;
	}
	
	public boolean validate(){
		boolean flag = false;
		if(ValidatorUtil.isEmpty(dieta.getNomeDieta())){
			adicionarMensagem("Informe o nome da dieta", Mensagens.ERRO);
			flag = true;
		}
		if(ValidatorUtil.isEmpty(dieta.getNomePaciente())){
			adicionarMensagem("Informe o nome do paciente", Mensagens.ERRO);
			flag = true;
		}
		return flag;
	}
	
	public String remove(){
		dDAO.delete(dieta);
		dieta = new Dieta();
		//atualiza a lista
		map = dDAO.findAll(getUsuarioLogado().getId());
		adicionarMensagem("Removido com sucesso !", Mensagens.INFO);
		return null;
	}
	
	public List<Dieta> getDietas(){
		return dietas;
	}
	
	
	public Dieta getDieta(){
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}
	
	public Set<Entry<Dieta,List<Refeicao>>> getEntrySet(){
		return map.entrySet();
	}

}
