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

public class Arquivo {
	private Path path1 = Paths.get("C:/Users/Tiago/Documents/acidentes.csv");
	private LinkedList<Acidente> acidentes = new LinkedList<Acidente>();
	private Map<Date, Acidente> dictDate = new TreeMap<Date, Acidente>();
	private Map<String, Acidente> dictStreet = new TreeMap<String, Acidente>();

	
	// FORMATAÇÃO DA DATA PARA O FORMATO "dd-MM-yyyy hh:ss"
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
	

	public LinkedList<Acidente> lerArquivo() throws ParseException {
		try (BufferedReader reader = Files.newBufferedReader(path1,
				Charset.defaultCharset())) {
			// INICIALIZAÇÃO DE OBJETOS, LISTAS E VARIÁVEIS NECESSÁRIAS
			int i                 = 0;
			String line           = "", 
				   local          = "", 
				   tipoLocal      = "";
			String[] splittedLine = null, 
					 aux          = null;			
			SimpleDateFormat sdf  = new SimpleDateFormat("dd-MM-yyyy hh:ss");
			Date date             = null;
			
			// LÊ AS LINHAS DO ARQUIVO E INSERE NOS DICIONÁRIOS
			while ((line = reader.readLine()) != null) {
				if(i != 0){
					// QUEBRA A LINHA POR ";"
					splittedLine = line.split(";");
					try{
						date = sdf.parse(dateFormat(splittedLine[2]));
						aux = splittedLine[0].split(" ", 2);
						tipoLocal = aux[0];
						local = aux[1];
						
						// CONSTRÓI O OBJETO acid
						Acidente acid = new Acidente(local,
								splittedLine[1], date, splittedLine[3],
								Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), Integer.parseInt(splittedLine[6]),
								Integer.parseInt(splittedLine[7]), Integer.parseInt(splittedLine[8]), Integer.parseInt(splittedLine[9]),
								Integer.parseInt(splittedLine[10]), Integer.parseInt(splittedLine[11]), Integer.parseInt(splittedLine[12]),
								Integer.parseInt(splittedLine[13]), Integer.parseInt(splittedLine[14]), Integer.parseInt(splittedLine[15]),
								splittedLine[16], splittedLine[17], splittedLine[18], tipoLocal);
						
						// DICIONÁRIO POR DATA
						dictDate.put(date, acid);
						// DICIONÁRIO POR RUA
						dictStreet.put(local, acid);
						
					}catch(Exception e){
						// EXCEPTION
					}
					
					/*System.out.println(splittedLine[0]+ " " +
					splittedLine[1]+ " " +splittedLine[2]+ " " +splittedLine[3]+ " " +
					Integer.parseInt(splittedLine[4])+ " " +Integer.parseInt(splittedLine[5])+ " " +Integer.parseInt(splittedLine[6])+ " " +
					Integer.parseInt(splittedLine[7])+ " " +Integer.parseInt(splittedLine[8])+ " " +Integer.parseInt(splittedLine[9])+ " " +
					Integer.parseInt(splittedLine[10])+ " " +Integer.parseInt(splittedLine[11])+ " " +Integer.parseInt(splittedLine[12])+ " " +
					Integer.parseInt(splittedLine[13])+ " " +Integer.parseInt(splittedLine[14])+ " " +Integer.parseInt(splittedLine[15])+ " " +
					splittedLine[16]+ " " +splittedLine[17]+ " " +splittedLine[18]);*/
					}
				i = 1;
			}

		} catch (IOException x) {
			System.err.format("Erro de E/S %s%n", x);
		}
		
		Set<Entry<Date, Acidente>> setDate = dictDate.entrySet();
		Set<Entry<String, Acidente>> setStreet = dictStreet.entrySet();
		for (Map.Entry<Date, Acidente> acdDate : setDate) { 
			acidentes.addDate(acdDate.getValue());
			acidentes.incrementSize();
		    //System.out.println(acdDate.getKey() + ": " + acdDate.getValue().getLocal()); 
		}
		
/*		for (Map.Entry<String, Acidente> acdStreet : setStreet) { 
			acidentes.addStreet(acdStreet.getValue());
		}*/
		
		return acidentes;
	}
}
