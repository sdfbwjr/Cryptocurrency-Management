
import java.io.*;
import java.util.*;

//import java.util.stream.Collectors;

//import javax.swing.*;

public class Test {
	 public static void main( String[] args ) {
		
		Scanner keyboard;
		String fileName;
		BufferedReader in;
		String line;
		List list = new List();
		
		
		try {
			keyboard = new Scanner(System.in);
			System.out.println("\nEnter the input file name: ");
			fileName = keyboard.nextLine();
			in = new BufferedReader(new FileReader(fileName));
			
			line = in.readLine();
			while(line != null){
				//System.out.println(line);
				processLine(line, list);
				line = in.readLine();
				
			}
			
			in.close();
		}catch(IOException e) {
			System.out.println("error");
		}
	 }
	 
	 public static void processLine(String line, List list) {
		 String[] tokens;
		 String result = "";
		 
		 tokens = line.split(" ");
		 if(tokens[0].equals("#")) {
			 System.out.println();
			 System.out.println(line);
			 System.out.println();
		 }else if(tokens[0].equals("NEW")) {
			 Investors newInv = new Investors(tokens[3], tokens[1], tokens[2], Integer.parseInt(tokens[4]));
			 list.addInvestor(newInv);
		 }else if(tokens[0].equals("CRYPTO")) {
			 Cryptocurrencies newCrypt = new Cryptocurrencies(tokens[2], tokens[1], Integer.parseInt(tokens[3]));
			 list.addCryp(newCrypt);
		 }else if(tokens[0].equals("MINE")) {
			 result = list.mine(tokens[1], tokens[2], Integer.parseInt(tokens[3]));
			 System.out.println(result);
		 }else if(tokens[0].equals("TRADE")) {
				
			 result = list.trade(tokens[1], tokens[2], tokens[3], tokens[5], Integer.parseInt(tokens[4]),Integer.parseInt(tokens[6]));
			 System.out.println(result);
		 }else if(tokens[0].equals("CRYPORT")) {
			 list.currencyReport(tokens[1]);
		 }else if(tokens[0].equals("REPORT")) {
			 list.portfolioReport(tokens[1]);
		 }
		 
	 }
	 
	 
	 
	
}
