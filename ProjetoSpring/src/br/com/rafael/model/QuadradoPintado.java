package br.com.rafael.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_quadrado_pintado")
public class QuadradoPintado {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "seq_tab_quadrado_pintado", allocationSize = 1)
	@Column(name = "cod_quadrado_pintado")
	private int cod_quadrado_pintado;
	@Column(name = "x")
	private int x;
	@Column(name = "y")
	private int y;	
	@JoinColumn(name = "cod_quadrado", referencedColumnName = "cod_quadrado")
	@ManyToOne
	private Quadrado quadrado;
	
	public int getCod_quadrado_pintado() {
		return cod_quadrado_pintado;
	}
	public void setCod_quadrado_pintado(int cod_quadrado_pintado) {
		this.cod_quadrado_pintado = cod_quadrado_pintado;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Quadrado getQuadrado() {
		return quadrado;
	}
	public void setQuadrado(Quadrado quadrado) {
		this.quadrado = quadrado;
	}	
}
