import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass 
{
	public static void main(String[] args) 
	{
		// Carico le righe su un arrayList
		ArrayList<String> righeFile = leggiFile("file.csv");
		
		// Stampo l'arrayList
		for(String s : righeFile)
			System.out.println(s);
		
		// Sostituisco una riga a caso nel file scaricato
		if(righeFile.size() > 3)
			righeFile.set(5,"riga sostituita");
		
		// Riscrivo tutte le righe daccapo
		scriviFile("file.csv", righeFile);
	}	
	
	static void scriviFile(String percorso, ArrayList<String> righe)
	{
		// Prima creo un oggetto che gestisce il file in scrittura
		BufferedWriter w = null;		
		
		// Poi creo un oggetto che ne permette la scrittura (metodo write) 
		FileWriter f = null;
		
		// Per manipolare i file bisogna gestire le eccezioni (possibili errori)
		try
		{
			f = new FileWriter("file.csv");
			w = new BufferedWriter(f);
		}
		// Qui si gestisce l'eccezione qualora accadesse
		catch (FileNotFoundException e)
		{
			System.out.println("File non trovato");
			return;
		}		
		// Qui si gestisce l'eccezione qualora accadesse
		catch (IOException e)
		{
			System.out.println("Errore di scrittura");
			return;
		}
		
		/* 
		 * Scorro tutto l'arrayList e riscrivo tutto il file da zero!!!
		 * È efficiente? NO! 
		 * È più semplice di riscrivere una singola riga? Già....
		 */
		for(String s : righe)
			scriviLinea(w,s);	
		
		// Per manipolare i file bisogna gestire le eccezioni (possibili errori)
		try
		{
			w.close(); // Dopo usato va sempre chiuso (sia il buffer che il file)
		}		
		catch (IOException e)
		// Qui si gestisce l'eccezione qualora accadesse
		{
			System.out.println("Errore di chiusura del buffer scrittura");
		}
		
		// Per manipolare i file bisogna gestire le eccezioni (possibili errori)
		try
		{
			f.close(); // Dopo usato va sempre chiuso (sia il buffer che il file)
		}
		// Qui si gestisce l'eccezione qualora accadesse
		catch (IOException e)
		{
			System.out.println("Errore di chiusura del file scrittura");
		}
	}
	
	static void scriviLinea(BufferedWriter b, String riga)
	{
		// Per manipolare i file bisogna gestire le eccezioni (possibili errori)
		try
		{
			b.write(riga+ "\n");
		}
		// Qui si gestisce l'eccezione qualora accadesse
		catch (IOException e)
		{
			System.out.println("Errore di scrittura");
		}
	}
	
	static ArrayList<String> leggiFile(String percorsoFile)
	{
		// Prima creo un oggetto che gestisce il file in lettura
		FileReader f = null;
		
		// Poi creo un oggetto che ne permette la lettura metodo "read" o "readlLine"
		BufferedReader b = null;
		
		// Per manipolare i file bisogna gestire le eccezioni (possibili errori)
		try
		{
			f = new FileReader(percorsoFile);
			b = new BufferedReader(f);
		}
		// Qui si gestisce l'eccezione qualora accadesse
		catch (FileNotFoundException e)
		{
			System.out.println("File non trovato");
			return null;
		}
		
		// Uso un booleano per capire quando finisce il file
		boolean fineFile = false;
		// Creo l'oggetto da restituire con tutte le righe caricate
		ArrayList<String> retVal = new ArrayList<String>();
		while(!fineFile) 
		{
			String s = leggiLinea(b);
			// Se la riga è null siamo arrivati alla fine del file
			if(s == null)
				fineFile = true;
			// Sennò è da aggiungere all'arraylist da restituire
			else
				retVal.add(s);
		}
		// Per manipolare i file bisogna gestire le eccezioni (possibili errori)
		try
		{
			f.close(); // Dopo usato va sempre chiuso (sia il buffer che il file)
		}
		// Qui si gestisce l'eccezione qualora accadesse
		catch (IOException e)
		{
			System.out.println("Errore chiusura file in lettura");
		}
		
		return retVal;
	}
	
	
	static String leggiLinea(BufferedReader b)
	{
		String s;
		try
		{
			s = b.readLine();
		}
		// Qui si gestisce l'eccezione qualora accadesse
		catch (IOException e)
		{
			System.out.println("Errore di lettura");
			return null;
		}
		return s;
	}
}
