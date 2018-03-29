package registradores;

import java.io.Serializable;
import java.util.Vector;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque e Michael Almeida da Franca Monteiro.
 * Classe referente à coleção de objetos do tipo Sala.
 *
 */
public class ColecaoSalas implements Serializable
{
	private static final long serialVersionUID = -1454612525743607061L;
	private Vector<Sala> salas;
	
	/**
	 * Construtor de ColecaoSalas.
	 */
	public ColecaoSalas()
	{
		
		salas = new Vector<Sala>();
	}
	
	/**
	 * adiciona um objeto do tipo Instituicao, retorna true se a operação der certo e false se houver falha.
	 * @param sala
	 * @return boolean.
	 */
	public boolean adicionaSala(Sala sala)
	{
		
		for(int i = 0; i < salas.size(); i++)
		{
			if(salas.get(i).equals(sala))
			{
				return false;
			}
		}
		salas.add(sala);
		return true;
	}
	
	public Sala getSala(int i)
	{
		return salas.get(i);
	}
	
	public void limparColecao()
	{
		salas = null;
	}
	
	public int listagemSalas()
	{
		int i;
		for(i = 0; i < salas.size(); i++)
		{
			System.out.println(salas.get(i).toString());
		}
		return i;
	}
	
	public boolean removeSala(Sala sala)
	{
		for(int i = 0; i < salas.size(); i++)
		{
			if(!salas.get(i).equals(sala))
			{
				salas.remove(sala);
				return true;
			}
		}
		return false;
	}

	public int size()
	{
		return salas.size();
	}
	
	public Sala pesquisaPeloNome(String nome)
	{
		int flag = pesquisaIndicePeloNome(nome);
		if(flag >= 0)
		{
			return getSala(flag);
		}
		return null;
	}
	
	public int pesquisaIndicePeloNome(String nome)
	{
		int i;
		for(i = 0; i < salas.size(); i++)
		{
			if(salas.get(i).getNome().equals(nome))
			{
				return i;
			}
		}
		return -1;
	}
	
	/*public int atribuirPorta(Sala sala)
	{
		int i = pesquisaIndicePeloNome(sala.getNome());
		return 60050 + i;
	}*/
}
