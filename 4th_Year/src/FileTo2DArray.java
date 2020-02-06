import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FileTo2DArray {

	Scanner sc;
	private int[][] map;
	int rows, columns;
	BufferedReader br; 
	String first;
	public FileTo2DArray() {
		int numLines = 0;
		try {
			br = new BufferedReader(new FileReader("Map.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			numLines = countLines("Map.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {		
			first = br.readLine();			
			columns = countLine(first);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			br = new BufferedReader(new FileReader("Map.txt"));
			sc = new Scanner(br);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rows = numLines;
		map = new int[rows][columns];
		
		while(sc.hasNextLine()) {			
			for (int i=0; i<map.length; i++) {
				String[] line = sc.nextLine().trim().split("\\s+"); // \\s+ is regex expression for find all whitespaces inbetween
				for (int j=0; j<line.length; j++) {
					if (line[j].equals("."))
						map[i][j] = 0;
					else if (line[j].equals("x")) {						
						map[i][j] = 10;
					}
					//map[i][j] = Integer.parseInt(line[j]);
					//System.out.print(line[j] + " ");
				}
				//System.out.println();           
			}
		}
		//System.out.println(Arrays.deepToString(myArray));
		//System.out.println(map[1][3]);  
		//System.out.println(map[1][4]);  
		//System.out.println(map[1][5]);  
	}
	public int[][] getMap(){
		return map;
	}
	public int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean endsWithoutNewLine = false;
			while ((readChars = is.read(c)) != -1) {
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n')
						++count;
				}
				endsWithoutNewLine = (c[readChars - 1] != '\n');
			}
			if(endsWithoutNewLine) {
				++count;
			} 
			return count;
		} finally {
			is.close();
		}
	}
	public int countLine(String s) {
		int count = 0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) != ' ') {
				count++;
			}
		}
		
		return count;
	}
	public static void main(String args[]) throws Exception {
		FileTo2DArray convert = new FileTo2DArray();
	}
}