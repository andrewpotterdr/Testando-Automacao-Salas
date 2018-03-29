package registradores;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.GregorianCalendar;

/**
 * @author Pablo Bezerra Guedes Lins de Albuquerque Michael Almeida da Franca Monteiro.
 * Classe responsável pelo registro de máquinas.
 *
 */
public class RegistraMaquinas extends Thread
{	
	
	ColecaoInstituicoes colinst;
	ColecaoDispositivos coldis;
	int porta;
	
	/**
	 * Construtor de RegistraMaquinas.
	 * @param colinst
	 * @param coldis
	 * @param porta
	 */
	public RegistraMaquinas(ColecaoInstituicoes colinst, ColecaoDispositivos coldis, int porta)
	{
		this.colinst = colinst;
		this.coldis = coldis;
		this.porta = porta;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@SuppressWarnings("resource")
	public void run()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		Maquina maquina = null;
		ServerSocket servidor = null;
		try
		{
			servidor = new ServerSocket(porta);
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		Socket cliente = null;
		while(true)
		{
			try
			{
				cliente = servidor.accept();
			}
			catch(IOException e)
			{
				System.err.println(e.getMessage());
			}
			DataInputStream entradaBool = null;
			ObjectInputStream entradaObj = null;
			try
			{
				entradaBool = new DataInputStream(cliente.getInputStream());
			}
			catch (IOException e)
			{
				System.err.println(e.getMessage());
			}
			try
			{
				if(entradaBool.readBoolean())
				{
					System.out.println("Teste1 to registra from client:" + calendar.HOUR + ":" + calendar.MINUTE + ":" + calendar.SECOND + ":" + calendar.MILLISECOND);
					entradaObj = new ObjectInputStream(cliente.getInputStream());
					maquina = (Maquina) entradaObj.readObject();
					System.out.println("Teste2 to registra from client:" + calendar.HOUR + ":" + calendar.MINUTE + ":" + calendar.SECOND + ":" + calendar.MILLISECOND);
					coldis.adicionaDispositivo(maquina);
					colinst.gravaArquivo();
				}
				entradaBool.close();
			}
			catch(Exception e)
			{
				System.err.println(e.getMessage());
			}
		}
	}
}
