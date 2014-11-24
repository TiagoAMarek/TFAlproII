package pucrs.alpro2.br.tf;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
	private int chuvoso = 0,
				nublado = 0,
				bom     = 0,
				noite   = 0,
				dia     = 0,
				segunda = 0,
				terca   = 0,
				quarta  = 0,
				quinta  = 0,
				sexta   = 0,
				sabado  = 0,
				domingo = 0;
	private Date dataMaisAcidentes = null,
				 horaMaisAcidentes = null;
	private SimpleDateFormat dataHoraFormat = new SimpleDateFormat("dd-MM-yyyy hh:ss"),
            dataFormat     = new SimpleDateFormat("dd-MM-yyyy"),
            horaFormat     = new SimpleDateFormat("hh:ss");


	// FORMATACAO DA DATA PARA O FORMATO "dd-MM-yyyy hh:ss"
	public String dataHoraFormat(String date){
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
	
	// FORMATACAO DA DATA PARA O FORMATO "dd-MM-yyyy"
	public String dataFormat(String date){
			String formatted = "";
			String year = "";
			String month = "";
			String day = "";
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
				}
			formatted = day+"-"+month+"-"+year;
			return formatted;
	}
	
	// FORMATACAO DA DATA PARA O FORMATO "dd-MM-yyyy hh:ss"
	public String horaFormat(String date){
			String hour = "";
			char[] n = date.toCharArray();
			
				for(int i = 0; i < n.length; i++){
					if(i > 7){
						hour += n[i];
					}
				}
			return hour;
	}
	
	// RETORNA O CLIMA COM MAIS ACIDENTES
	public String tempoComMaisAcidentes(){
		if(chuvoso > nublado && chuvoso > bom)
			return "Há mais acidentes com tempo chuvoso";
		else if(nublado > chuvoso && nublado > bom)
			return "Há mais acidentes com tempo nublado";
		else
			return "Há mais acidentes com tempo bom";
	}
	
	// RETORNA O MOMENTO DO DIA COM MAIS ACIDENTES
	public String momentoComMaisAcidentes(){
		if(dia > noite)
			return "Há mais acidentes de dia";
		else 
			return "Há mais acidentes de noite";
	}
	
	// RETORNA O MOMENTO DO DIA COM MAIS ACIDENTES
	public String diaSemComMaisAcidentes(){
		int aux = segunda;
		String s = "Segunda";	
		if(aux < terca){
			aux = terca;
			s = "Terça";
		}
		if(aux < quarta){
			aux = quarta;
			s = "Quarta";
		}
		if(aux < quinta){
			aux = quinta;
			s = "Quinta";
		}
		if(aux < sexta){
			aux = sexta;
			s = "Sexta";
		}
		if(aux < sabado){
			aux = sabado;
			s = "Sabado";
		}
		if(aux < domingo){
			aux = domingo;
			s = "Domingo";
		}
		
		return "Há mais acidentes no(a): " + s;
	}
	
	public String dataMaisAcidentes(){
		return "Data com mais Acidentes: " + dataFormat.format(dataMaisAcidentes);
	}
	
	public String horaMaisAcidentes(){
		return "Hora com mais Acidentes: " + horaFormat.format(horaMaisAcidentes);
	}
	
	public ArrayList<Acidente> dataAcidente(String rua, String diaSem){
		Iterator<Acidente> it = acidentes.iterator();
		String ruaLog = null;
		ArrayList<Acidente> listAcd = new ArrayList<Acidente>();
		while(it.hasNext()){
			Acidente acd = it.next();
			ruaLog = acd.getTipoLocal()+" "+acd.getLocal();
			if(acd.getDiaSem().equals(diaSem) && rua.equals(ruaLog)){
				listAcd.add(acd);
			}
		}
		return listAcd;
	}

	// CRIA E RETORNA A LISTA
	public LinkedList<Acidente> createList() throws ParseException {
		// INICIALIZACAO DE OBJETOS, LISTAS E VARIAVEIS NECESSARIAS
		int i                   = 0,
			diaMaisAcidentes    = 0,
			auxHoraMais         = 0;
		String line             = "",
			   local            = "",
			   tipoLocal        = "";
		String[] splittedLine   = null,
				 aux            = null;
		Date dataHora           = null,
			 data               = null,
			 hora               = null;		
		
		try (BufferedReader reader = Files.newBufferedReader(path1,
				Charset.defaultCharset())) {
			// LE AS LINHAS DO ARQUIVO E INSERE NOS DICIONARIOS
			while ((line = reader.readLine()) != null) {
				if(i != 0){
					// QUEBRA A LINHA POR ";"
					splittedLine = line.split(";");
					try{
						dataHora = dataHoraFormat.parse(dataHoraFormat(splittedLine[2]));
						data = dataFormat.parse(dataFormat(splittedLine[2]));
						hora = horaFormat.parse(horaFormat(splittedLine[2]));
						aux = splittedLine[0].split(" ", 2);
						tipoLocal = aux[0];
						local = aux[1];
						
						// CONSTROI O OBJETO acd
						Acidente acd = new Acidente(local,
								splittedLine[1], dataHora, splittedLine[3],
								Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), Integer.parseInt(splittedLine[6]),
								Integer.parseInt(splittedLine[7]), Integer.parseInt(splittedLine[8]), Integer.parseInt(splittedLine[9]),
								Integer.parseInt(splittedLine[10]), Integer.parseInt(splittedLine[11]), Integer.parseInt(splittedLine[12]),
								Integer.parseInt(splittedLine[13]), Integer.parseInt(splittedLine[14]), Integer.parseInt(splittedLine[15]),
								splittedLine[16], splittedLine[17], splittedLine[18], tipoLocal, data, hora);
						
						// CONTA CLIMA TEMPO
						if(acd.getTempo().equals("NUBLADO")){
							nublado++;
						}
						else if(acd.getTempo().equals("CHUVOSO")){
							chuvoso++;
						}
						else if(acd.getTempo().equals("BOM")){
							bom++;
						}
						
						if(acd.getNoiteDia().equals("DIA")){
							dia++;
						} else {
							noite++;
						}
						
						// CONTA DIAS DA SEMANA
						if(acd.getDiaSem().equals("SEGUNDA-FEIRA")){
							segunda++;
						}
						else if(acd.getDiaSem().equals("TERCA-FEIRA")){
							terca++;
						}
						else if(acd.getDiaSem().equals("QUARTA-FEIRA")){
							quarta++;
						}
						else if(acd.getDiaSem().equals("QUINTA-FEIRA")){
							quinta++;
						}
						else if(acd.getDiaSem().equals("SEXTA-FEIRA")){
							sexta++;
						}
						else if(acd.getDiaSem().equals("SABADO")){
							sabado++;
						}
						else if(acd.getDiaSem().equals("DOMINGO")){
							domingo++;
						}
						
						// DICIONARIO POR DATA
						dictDate.put(dataHora, acd);
						// DICIONARIO POR RUA
						dictStreet.put(local, acd);
						
					}catch(Exception e){
						//System.err.println("Erro ao executar linha "+ i +" do arquivo");
					}
					
				}
				i++;
			}

		} catch (IOException x) {
			System.err.format("Erro de E/S %s%n", x);
		}
		
		Set<Entry<Date, Acidente>> setDate = dictDate.entrySet();
		Set<Entry<String, Acidente>> setStreet = dictStreet.entrySet();
		ArrayList<Date> dateTeste = new ArrayList<Date>();
		ArrayList<Date> hourTeste = new ArrayList<Date>();
		// PERCORRE O DICIONARIO DATA E INSERE NA LISTA
		for (Map.Entry<Date, Acidente> acdDate : setDate) {
			if(dateTeste.isEmpty())
				dateTeste.add(acdDate.getValue().getData());
			else if(dateTeste.get(dateTeste.size()-1).equals(acdDate.getValue().getData())){
				dateTeste.add(acdDate.getValue().getData());
			} else {
				if(diaMaisAcidentes < dateTeste.size()){
					diaMaisAcidentes = dateTeste.size();
					dataMaisAcidentes = dateTeste.get(0);
					//System.out.println(diaMaisAcidentes+ " " +dataMaisAcidentes);
				}
				dateTeste.clear();
				dateTeste.add(acdDate.getValue().getData());
			}
			
			if(hourTeste.isEmpty())
				hourTeste.add(acdDate.getValue().getHora());
			else if(hourTeste.get(hourTeste.size()-1).equals(acdDate.getValue().getHora())){
				hourTeste.add(acdDate.getValue().getHora());
			} else {
				if(auxHoraMais < hourTeste.size()){
					auxHoraMais = hourTeste.size();
					horaMaisAcidentes = hourTeste.get(0);
					//System.out.println(diaMaisAcidentes+ " " +dataMaisAcidentes);
				}
				hourTeste.clear();
				hourTeste.add(acdDate.getValue().getData());
			}
			try {
				acidentes.addDate(acdDate.getValue());
			} catch(Exception e) {
				System.err.println("Erro ao inserir objeto do dicionario na lista data");
			}
		}
		
		// PERCORRE O DICIONARIO RUA E INSERE NA LISTA
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