package pucrs.alpro2.br.tf;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 * @authors Tiago A. Marek, Joao Garcia
 *
 */

public class ListaAcidentes {
	private Path path1 = Paths.get("C:/Users/Tiago/Documents/acidentes.csv");
	private LinkedList<Acidente> acidentes = new LinkedList<Acidente>();
	private Map<Date, Acidente> dictDate = new TreeMap<Date, Acidente>();
	private Map<String, Acidente> dictStreet = new TreeMap<String, Acidente>();

	
	// FORMATACAO DA DATA PARA O FORMATO "dd-MM-yyyy hh:ss"
	public String dateFormat(String date){
			String formatted = "";
			String year = "";
			String month = "";
			String day = "";
			String hour = "";
			char[] n = date.toCharArray();
			
				for(int i = 0; i < n.length; i++){
					if(i <= 3){
						year += n[i];
					}
					else if(i <= 5){
						month += n[i];
					}
					else if(i <= 7){
						day += n[i];
					}
					else if(i > 7){
						hour += n[i];
					}
				}
			formatted = day+"-"+month+"-"+year+" "+hour;
			return formatted;
	}
	

	public LinkedList<Acidente> createList() throws ParseException {
		try (BufferedReader reader = Files.newBufferedReader(path1,
				Charset.defaultCharset())) {
			// INICIALIZACAO DE OBJETOS, LISTAS E VARIAVEIS NECESSARIAS
			int i                 = 0;
			String line           = "", 
				   local          = "", 
				   tipoLocal      = "";
			String[] splittedLine = null, 
					 aux          = null;			
			SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy hh:ss");
			Date date             = null;
			
			// LE AS LINHAS DO ARQUIVO E INSERE NOS DICIONARIOS
			while ((line = reader.readLine()) != null) {
				if(i != 0){
					// QUEBRA A LINHA POR ";"
					splittedLine = line.split(";");
					try{
						date = sdf.parse(dateFormat(splittedLine[2]));
						aux = splittedLine[0].split(" ", 2);
						tipoLocal = aux[0];
						local = aux[1];
						
						// CONSTROI O OBJETO acid
						Acidente acid = new Acidente(local,
								splittedLine[1], date, splittedLine[3],
								Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), Integer.parseInt(splittedLine[6]),
								Integer.parseInt(splittedLine[7]), Integer.parseInt(splittedLine[8]), Integer.parseInt(splittedLine[9]),
								Integer.parseInt(splittedLine[10]), Integer.parseInt(splittedLine[11]), Integer.parseInt(splittedLine[12]),
								Integer.parseInt(splittedLine[13]), Integer.parseInt(splittedLine[14]), Integer.parseInt(splittedLine[15]),
								splittedLine[16], splittedLine[17], splittedLine[18], tipoLocal);
						
						// DICIONARIO POR DATA
						dictDate.put(date, acid);
						// DICIONARIO POR RUA
						dictStreet.put(local, acid);
						
					}catch(Exception e){
						System.err.println("Erro ao executar linha "+ i +" do arquivo");
					}
					
					/*System.out.println(splittedLine[0]+ " " +
					splittedLine[1]+ " " +splittedLine[2]+ " " +splittedLine[3]+ " " +
					Integer.parseInt(splittedLine[4])+ " " +Integer.parseInt(splittedLine[5])+ " " +Integer.parseInt(splittedLine[6])+ " " +
					Integer.parseInt(splittedLine[7])+ " " +Integer.parseInt(splittedLine[8])+ " " +Integer.parseInt(splittedLine[9])+ " " +
					Integer.parseInt(splittedLine[10])+ " " +Integer.parseInt(splittedLine[11])+ " " +Integer.parseInt(splittedLine[12])+ " " +
					Integer.parseInt(splittedLine[13])+ " " +Integer.parseInt(splittedLine[14])+ " " +Integer.parseInt(splittedLine[15])+ " " +
					splittedLine[16]+ " " +splittedLine[17]+ " " +splittedLine[18]);*/
					}
				i++;
			}

		} catch (IOException x) {
			System.err.format("Erro de E/S %s%n", x);
		}
		
		Set<Entry<Date, Acidente>> setDate = dictDate.entrySet();
		Set<Entry<String, Acidente>> setStreet = dictStreet.entrySet();
		
		for (Map.Entry<Date, Acidente> acdDate : setDate) { 
			try {
				acidentes.addDate(acdDate.getValue());
			} catch(Exception e) {
				System.err.println("Erro ao inserir objeto do dicionario na lista data");
			}
		}
		
		for (Map.Entry<String, Acidente> acdStreet : setStreet) { 
			try {
				acidentes.addStreet(acdStreet.getValue());
			} catch(Exception e) {
				System.err.println("Erro ao inserir objeto do dicionario na lista rua");
			}
		}
		
		return acidentes;
	}
}
