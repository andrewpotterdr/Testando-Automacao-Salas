package registradores;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.GregorianCalendar;

/**
 * @author Pablo e Michael
 * Classe que cria uma thread para atualizar as coleções a partir dos objetos salvos no arquivo.
 *
 */
public class Atualiza extends Thread
{
	ColecaoInstituicoes colinst = new ColecaoInstituicoes();
	public Atualiza() throws Exception
	{
		try
		{
			colinst.recuperaArquivo();
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	public void run()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		ServerSocket updater = null;
		ObjectOutputStream oout = null;
		ObjectInputStream oin = null;
		Socket servidor = null;
		int porta = 51000;
		try
		{
			while(true)
			{
				updater = new ServerSocket(porta); //porta
				servidor = updater.accept();
				oin = new ObjectInputStream(servidor.getInputStream());
				oout = new ObjectOutputStream(servidor.getOutputStream());
				if(((Stringo)oin.readObject()).getStringo().equals("true"))
				{
					System.out.println("Teste1 to atualiza from server:" + calendar.HOUR + ":" + calendar.MINUTE + ":" + calendar.SECOND + ":" + calendar.MILLISECOND);
					colinst.recuperaArquivo();
					Instituicao inst = colinst.procuraInst((InstituicaoEnsino)(oin.readObject()));
					System.out.println("Teste2 to atualiza from server:" + calendar.HOUR + ":" + calendar.MINUTE + ":" + calendar.SECOND + ":" + calendar.MILLISECOND);
					Bloco bloco = inst.getColBlo().pesquisaPeloNome(((Bloco)oin.readObject()).getNome());
					System.out.println("Teste3 to atualiza from server:" + calendar.HOUR + ":" + calendar.MINUTE + ":" + calendar.SECOND + ":" + calendar.MILLISECOND);
					Sala sala = bloco.getColSal().pesquisaPeloNome(((Sala)oin.readObject()).getNome());
					System.out.println("Teste4 to atualiza from server:" + calendar.HOUR + ":" + calendar.MINUTE + ":" + calendar.SECOND + ":" + calendar.MILLISECOND);
					ColecaoDispositivos coldis = sala.getColDis();
					System.out.println("Teste1 from atualiza to server:" + calendar.HOUR + ":" + calendar.MINUTE + ":" + calendar.SECOND + ":" + calendar.MILLISECOND);
					oout.writeObject(coldis);
				}
				servidor.close();
				porta++;
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
