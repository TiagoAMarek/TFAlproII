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

public class Arquivo {
	private Path path1 = Paths.get("C:/Users/Tiago/Documents/acidentes.csv");
	private LinkedList<Acidente> acidentes = new LinkedList<Acidente>();
	
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
			String line = null;
			String[] splittedLine = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:ss");
			int i = 0;
			while ((line = reader.readLine()) != null) {
				if(i != 0){
					splittedLine = line.split(";");
					Date date = sdf.parse(dateFormat(splittedLine[2])); 
					Acidente acid = new Acidente(splittedLine[0],
							splittedLine[1], date, splittedLine[3],
							Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[5]), Integer.parseInt(splittedLine[6]),
							Integer.parseInt(splittedLine[7]), Integer.parseInt(splittedLine[8]), Integer.parseInt(splittedLine[9]),
							Integer.parseInt(splittedLine[10]), Integer.parseInt(splittedLine[11]), Integer.parseInt(splittedLine[12]),
							Integer.parseInt(splittedLine[13]), Integer.parseInt(splittedLine[14]), Integer.parseInt(splittedLine[15]),
							splittedLine[16], splittedLine[17], splittedLine[18]);
					acidentes.add(acid);
				}
				i = 1;
			}

		} catch (IOException x) {
			System.err.format("Erro de E/S %s%n", x);
		}
		return acidentes;
	}
}
