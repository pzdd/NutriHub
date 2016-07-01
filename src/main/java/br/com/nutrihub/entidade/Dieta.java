package br.com.nutrihub.entidade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="dieta",schema="nutrihub")
public class Dieta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private String nomeDieta;
	
	private String nomePaciente;
	
	@ManyToOne
	@JoinColumn(name="id_nutricionista")
	private Nutricionista nutricionista;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="dieta", cascade = CascadeType.ALL)
	private Set<Refeicao> refeicoes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeDieta() {
		return nomeDieta;
	}

	public void setNomeDieta(String nomeDieta) {
		this.nomeDieta = nomeDieta;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public Nutricionista getNutricionista() {
		return nutricionista;
	}

	public void setNutricionista(Nutricionista nutricionista) {
		this.nutricionista = nutricionista;
	}

	public Set<Refeicao> getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(Set<Refeicao> refeicoes) {
		this.refeicoes = refeicoes;
	}
	
	public int getRefeicoesSize(){
		return refeicoes.size();
	}

}
