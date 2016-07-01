package br.ufrn.nutrihub.DAO;

import org.hibernate.Query;

import br.com.nutrihub.entidade.Nutricionista;

public class NutriDAO extends GenericDAO<Nutricionista>{
	
	public boolean loginNutri(String login,String senha){
		getSession().beginTransaction();
		Query q = getSession().createSQLQuery("select id from nutrihub.nutricionista where login like :login and senha = :senha");
		q.setString("login", login);
		q.setString("senha", senha);
		
		if(q.list().size() > 0)
			return true;
		else
			return false;
					
	}
	
	public Nutricionista findByLoginSenha(String login,String senha){
		getSession().beginTransaction();
		Query q = getSession().createQuery("from Nutricionista where login like :login and senha like :senha");
		q.setString("login", login);
		q.setString("senha", senha);
		return (Nutricionista) q.uniqueResult();
	}
}
