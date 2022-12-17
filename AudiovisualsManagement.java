import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AudiovisualsManagement {
	private static Scanner scan = new Scanner(System.in);
	private static String line = "";
	private static final int length = 100;
	private static Audiovisual llista[] = new Audiovisual[length];

	public static void FileUpload() throws IOException {
		// try, declaración le permite definir un bloque de código para probar errores
		// mientras se ejecuta.
		// catch, instrucción le permite definir un bloque de código que se ejecutará si
		// ocurre un error en el bloque de prueba.
		try {
			BufferedReader fr = new BufferedReader(new FileReader("Netflix.csv"));
			int i = 0;
			fr.readLine(); // Es salta la 1ra linea.
			while ((line = fr.readLine()) != null) {
				String[] str = line.split(";");
				// ha d'estar en ordre de Audiovisual.java
				llista[i] = new Audiovisual(str[2], str[1], str[0], Integer.parseInt(str[3]));// paso a int l'str 3 que
																								// es IMD
				i++;
			}
			fr.close();
		} catch (ArrayIndexOutOfBoundsException | IOException e) { // es una excepción en tiempo de ejecución, sin
																	// marcar y, por lo tanto, no necesita ser llamada
																	// explícitamente desde un método.
			e.printStackTrace();// señalará la línea exacta en la que el método generó la excepción.
		}
		// no es la mateixa i
		for (int i = 0; i < llista.length; i++) {
			if (llista[i] == null) {
				break;
			} else {
				System.out.println("Titol: " + llista[i].getTitol() + "; Génere: " + llista[i].getGender() + "; Tipus: "
						+ llista[i].getType() + ".\nPuntuació: " + llista[i].getIMD() + ".\n");
			}
		}
	}

	public static void SaveByGenre() throws IOException {
		File file = new File("Genres");// Crem un objecte de la clase File i especifiquem el nom del arxiu o el
										// directori
		if (!file.exists())// si no existeix el file creat anteriorment crea un directori
			file.mkdir();
		else
			Deleter("borrgen");// si existeix elimina

		for (int i = 0; i < llista.length; i++) {
			if (llista[i] == null) {// no hi ha res a netflix.csv
				break;
			} else {
				File file2 = new File("Genres/Netflix_" + llista[i].getGender() + ".csv");// vols crear algo amb aquest nom
				file2.createNewFile();// creem un arxiu
				FileWriter fr = new FileWriter((file2));// escriu en l'arxiu creat
				fr.append("Titulo");// nom columna
				fr.append(";");// salt de columna
				fr.append("Genero");
				fr.append(";");
				fr.append("Tipo");
				fr.append(";");
				fr.append("Puntuacion");
				fr.append("\n");// salt de linea

				for (int j = 0; j < llista.length; j++) {
					if (llista[j] == null) {// no hi ha res escrit en el fitxer Netflix.csv
						break;
					} else {
						if (llista[i].getGender() == (llista[j].getGender())) {
							fr.append(llista[j].getTitol());// primera columna agafes el titol del la llista que t'ha
							// donat que esta en la posició del str 2.
							fr.append(";");// cambiem de columna
							fr.append(String.valueOf(llista[j].getGender()));
							fr.append(";");
							fr.append(String.valueOf(llista[j].getType()));
							fr.append(";");
							fr.append(String.valueOf(llista[j].getIMD()));
							fr.append("\n");// cambien de fila
						}
					}
				}
				fr.flush();// tanca el filewriter
				fr.close();// tanca l'arxiu
			}
		}
	}

	
	public static void SaveByType() throws IOException {
		File file = new File("Types");// creem obj
		if (!file.exists()) {
			file.mkdir();// crem el directori del objecte file de la clase File
		} else {
			Deleter("borrtyp");//funcio deleter
		}
		for (int i = 0; i < llista.length; i++) {
			if (llista[i] == null) {//si s'acaba la llista
				break;
			} else {
				File file2 = new File("Types/Netflix_" + llista[i].getType() + ".csv");//vols crear algo amb aquest nom
				file2.createNewFile();//creem un arxiu del obj file2 de la clesse File
				FileWriter fr = new FileWriter((file2));//entrem per escriure en l'arxiu ja creat
				fr.append("Titulo");
				fr.append(";");
				fr.append("Genero");
				fr.append(";");
				fr.append("Tipo");
				fr.append(";");
				fr.append("Puntuacion");
				fr.append("\n");//salt de fila
				
				for (int j = 0; j < llista.length; j++) {
					if (llista[j] == null) {//no hi ha res a la fila
						break;
					} else {
						if (llista[i].getType() == (llista[j].getType())) {
							//si els tipus son iguals el de la llista que em donen i el que hem creat, afegim els valors
							fr.append(llista[j].getTitol());
							fr.append(";");
							fr.append(String.valueOf(llista[j].getGender()));
							fr.append(";");
							fr.append(String.valueOf(llista[j].getType()));
							fr.append(";");
							fr.append(String.valueOf(llista[j].getIMD()));
							fr.append("\n");//salt de fila
						}
					}
				}
				fr.flush();//tanquem el file writer
				fr.close();//tanquem l'arxiu creat amb file2
			}
		}
	}
	private static void Deleter(String str) {
		switch (str) {
		case "borrgen":
			for (int i = 0; i < llista.length; i++) { //recorre l'array
				if (llista[i] == null) {
					break;
				} else {
					File file2 = new File("Genres/Netflix_" + llista[i].getGender() + ".csv");//busques un arxiu amb aquest nom
					if (file2.exists()) {
						file2.delete();//si existeix eliminar-ho i fem el mateix amb type
					}
				}
			}
			break;
		case "borrtyp":
			for (int i = 0; i < llista.length; i++) {
				if (llista[i] == null) {
					break;
				} else {
					File file2 = new File("Types/Netflix_" + llista[i].getType() + ".csv");
					if (file2.exists()) {
						file2.delete();
					}
				}
			}
		}
	}

}
