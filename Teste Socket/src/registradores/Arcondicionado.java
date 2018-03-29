package registradores;

import java.io.Serializable;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro.
 * Classe que faz a abstração do arcondicionado.
 */
public class Arcondicionado implements Dispositivo, Serializable
{
	private static final long serialVersionUID = -597941876618328951L;
	private String nome;
	private boolean status;
	
	/**
	 * @param nome
	 * @param status
	 * Construtor de Arcondicionado.
	 */
	public Arcondicionado(String nome, boolean status) 
	{
		this.nome = nome;
		this.status = status;
	}

	public String getNome()
	{
		
		return this.nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public boolean getStatus()
	{
		
		return this.status;
	}

	public void setStatus(boolean status) 
	{
		
		this.status = status;
	}

	public String toString() 
	{
		
		return "Arcondicionado\nStatus: " + status;
	}
	
	public boolean equals(Dispositivo arc)
	{
		
		if(this.nome.equals(arc.getNome()))
		{
			return true;
		}
		return false;
	}
}
