package br.ufrn.nutrihub.DAO;

import java.util.List;

import org.hibernate.Query;

import br.com.nutrihub.entidade.Refeicao;

public class RefeicaoDAO extends GenericDAO<Refeicao>{

	
	public List<Refeicao> findRefeicoes(long idNutricionista,long idDieta){
		getSession().beginTransaction();
		Query q = getSession().createQuery("from Refeicao where id_nutricionista = :idNutricionista and id_dieta = :idDieta");
		q.setLong("idNutricionista", idNutricionista);
		q.setLong("idDieta", idDieta);
		return q.list();
	}
}
