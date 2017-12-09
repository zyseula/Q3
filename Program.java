import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		String[] rawArray = constructRawArray();
		System.out.println("Data input finished. Total number of records: "+rawArray.length);
		Tree BPlusTree = new Tree(5,6,rawArray);
		//BPlusTree.print();
		String [] list = {"Azevedo, Ana","Silva, Rui","Boussebough, Imane","Terracina, Giorgio",
				"Lefebvre, Peter","Houghten, Sher","Revesz, Peter"};
		for(int i = 6;i<7;i++){
			System.out.println("Query is "+list[i]);
			BPlusTree.searchForName(list[i],BPlusTree.root);
		}
	}
	
	
	public static String[] constructRawArray(){
		// TODO Auto-generated method stub
				String[] name;
				File rawFile = new File("ds17s-asg2-data.txt");
				Scanner inputStream = null;
				try{
					inputStream = new Scanner(new FileInputStream(rawFile));//ds17s-asg2-data
				}
				catch(FileNotFoundException e){
					System.out.println("File was not found or could not be opened.");
					System.exit(0);
				}
				
				int index = 0;
				int number = 0;
				while (inputStream.hasNextLine()) {
				    String line = inputStream.nextLine(); 
				    number++;
				}
				inputStream.close();
				name = new String[number];
				Scanner inputStream_2 = null;
				try{
					inputStream_2 = new Scanner(rawFile);//ds17s-asg2-data
				}
				catch(FileNotFoundException e){
					System.out.println("File was not found or could not be opened.");
					System.exit(0);
				}
				
				while (inputStream_2.hasNextLine()) {
					String temp_String = inputStream_2.nextLine().trim();
					temp_String = Normalizer.normalize(temp_String, Normalizer.Form.NFD);
					temp_String = temp_String.replaceAll("[^\\p{ASCII}]", "");
					name[index] = temp_String;
					index++;
				}
				SelectionSort(name);
				inputStream_2.close();
				return name;
	}
	
	
	public static void SelectionSort(String[] name){
		
		for(int i=0;i<name.length;i++){
			for(int j=i+1;j<name.length;j++){
				if(name[i].trim().compareToIgnoreCase(name[j].trim())>0){
					String temp = name[i];
					name[i] = name[j];
					name[j] = temp;
				}
			}	
		}
	}
}
