package br.com.rafael.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_quadrado")
public class Quadrado {	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator2")
	@SequenceGenerator(name = "generator2", sequenceName = "seq_tab_quadrado", allocationSize = 1)
	@Column(name = "cod_quadrado")
	private int cod_quadrado;
	@Column(name = "nome")
	private String nome;
	@Column(name = "x_inicio")
	private int x_inicio;
	@Column(name = "x_fim")
	private int x_fim;
	@Column(name = "y_inicio")
	private int y_inicio;
	@Column(name = "y_fim")
	private int y_fim;
	
	public Quadrado(){}
	
	public Quadrado(String nome,int x_inicio, int x_fim,int y_inicio, int y_fim){
		this.nome = nome;
		this.x_inicio = x_inicio;
		this.x_fim = x_fim;
		this.y_inicio = y_inicio;
		this.y_fim = y_fim;
	}		

	public int getCod_quadrado() {
		return cod_quadrado;
	}

	public void setCod_quadrado(int cod_quadrado) {
		this.cod_quadrado = cod_quadrado;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getX_inicio() {
		return x_inicio;
	}
	public void setX_inicio(int x_inicio) {
		this.x_inicio = x_inicio;
	}
	public int getX_fim() {
		return x_fim;
	}
	public void setX_fim(int x_fim) {
		this.x_fim = x_fim;
	}
	public int getY_inicio() {
		return y_inicio;
	}
	public void setY_inicio(int y_inicio) {
		this.y_inicio = y_inicio;
	}
	public int getY_fim() {
		return y_fim;
	}
	public void setY_fim(int y_fim) {
		this.y_fim = y_fim;
	}
		
	public boolean between(int num, int a , int b){
		if(num>=a && num<=b){
			return true;
		}else{
			return false;
		}
	}	
	
	public boolean betweenSquare(int p_ini,int p_fim, int a , int b){	
		if(this.between(p_ini, a, b) || this.between(p_fim, a, b)
				||
		   this.between(a, p_ini, p_fim) || this.between(b, p_ini, p_fim)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean ehQuadradoValido(Quadrado q){
		int a = q.getX_fim()-q.getX_inicio();
		int b = q.getY_fim()-q.getY_inicio();
		if(a==b){
			return true;
		}else{
			return false;
		}
	}

	public boolean sobrepoe(Object obj) {
		Quadrado q = (Quadrado) obj;		
		if(this.betweenSquare(q.getX_inicio(),q.getX_fim(), this.getX_inicio(), this.getX_fim()) && this.betweenSquare(q.getY_inicio(),q.getY_fim(), this.getY_inicio(), this.getY_fim())){
			return true;
		}else{
			return false;
		}
	}	
	
	public int getArea(Quadrado q){
		int y = q.getY_fim() - q.getY_inicio();
		int x = q.getX_fim() - q.getX_inicio();
		return x * y;
	}	
}
