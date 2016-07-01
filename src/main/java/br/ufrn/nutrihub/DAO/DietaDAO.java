package br.ufrn.nutrihub.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;

import br.com.nutrihub.entidade.Dieta;
import br.com.nutrihub.entidade.Refeicao;

public class DietaDAO extends GenericDAO<Dieta>{
	
	public Map<Dieta,List<Refeicao>> findAll(long idNutri){
		getSession().beginTransaction();
		criteria = getSession().createCriteria(Dieta.class);
		criteria.add(Restrictions.eq("nutricionista.id", idNutri));
		List<Dieta> result = (List<Dieta>) criteria.list();
		getSession().getTransaction().commit();
		getSession().close();
		Map<Dieta,List<Refeicao>> map = new HashMap<Dieta,List<Refeicao>>();
		for(Dieta d : result){
			Iterator<Refeicao> it = d.getRefeicoes().iterator();
			map.put(d,new ArrayList<Refeicao>());
			while(it.hasNext()){
				map.get(d).add((Refeicao) it.next());
			}
		}
		return map;
	}

}
