
import java.io.*;
import java.util.*;

public class Prog02{
	public static void main (String [] args) throws IOException{

		//stuff
		Scanner kb = new Scanner(System.in);
		Random rand = new Random();
		final int GAUSS_STDEV = 10;
		final int GAUSS_MEAN = 75;
		int amountOfRandom;
		int randomNumbers;
		int gaussNums;
		int range;
		int gRange;
		int count = 0;
		double gaussMean = 0;
		int gaussMin = 100;
		int gaussMax = 0;
		int min = 100;
		int max = 0;
		double mean = 0;
		double standardDeviation = 0;
		double gStandardDeviation = 0;

		File file = new File("regNums.txt");
		PrintWriter outputFile = new PrintWriter(file);
   		File inFile = new File("gaussNums.txt");
   		PrintWriter outFile = new PrintWriter(inFile);
   		File barFile = new File("barChartFile.txt");
   		PrintWriter barOut = new PrintWriter(barFile);


		System.out.print("How many random numbers (0...100) should i generate: ");
		amountOfRandom = kb.nextInt();
		System.out.println();

		while (count<amountOfRandom){	//Generates random numbers for regNums.txt
			randomNumbers = rand.nextInt(100);
			gaussNums = (int)(rand.nextGaussian()* GAUSS_STDEV + GAUSS_MEAN);
			outputFile.println(randomNumbers);
			outFile.println(gaussNums);
			count++;
		}
		outputFile.close();
		outFile.close();

		Scanner inputFile = new Scanner(file);
		Scanner iFile = new Scanner(inFile);
		while (inputFile.hasNext()){
			int number = inputFile.nextInt();
			int gaussNumber = iFile.nextInt();
			mean = mean + number;
			gaussMean = gaussMean + gaussNumber;
			if (number < min){
				min = number;
			}
			if (number > max){
				max = number;
			}
			if (gaussNumber < gaussMin){
				gaussMin = gaussNumber;
			}
			if (gaussNumber > gaussMax){
				gaussMax = gaussNumber;
			}
		}
		mean = mean/amountOfRandom;
		gaussMean = gaussMean/amountOfRandom;
		inputFile.close();

		Scanner standardD = new Scanner(file); //Having to re open the file one more time for standard deviation
		Scanner standardD2 = new Scanner(inFile);
		while (standardD.hasNext()){
			int number = standardD.nextInt();
			int gaussNumber = standardD2.nextInt();
			standardDeviation += Math.pow(number - mean, 2);
			gStandardDeviation += Math.pow(gaussNumber - gaussMean, 2);
		}
		gStandardDeviation = Math.sqrt(gStandardDeviation/amountOfRandom);
		standardDeviation = Math.sqrt(standardDeviation/amountOfRandom);
		range = (max - min) + 1;
		gRange = (gaussMax - gaussMin) + 1;
		System.out.println("Measures for the normally distributed integers in file, regNums.txt:");
   		System.out.println("Min: " + min);
   		System.out.println("Max: " + max);
   		System.out.println("Range: " + range);
   		System.out.println("Mean: " + mean);
   		System.out.println("StDev: " + standardDeviation);
   		System.out.println();

   		System.out.println("Bar chart for the normally distributed integers in file, gaussNums.txt (50...100): ");

   		count = 49;
   		while (count<100){
   			count++;
   			System.out.println();
   			barOut.println();
   			System.out.print(count + ":");
   			barOut.print( count + ":");
   			Scanner input2 = new Scanner(inFile);
   			while (input2.hasNext()){
   				int gaussNumber = input2.nextInt();
   				if (gaussNumber == count){
   					System.out.print("*");
   					barOut.print("*");
   				}
   			}
   		}
   		barOut.close();
   		System.out.println();
   		System.out.println();
		  System.out.println("Measures for the normally distributed integers in file, gaussNums.txt:");
   		System.out.println("Min: " + gaussMin);
   		System.out.println("Max: " + gaussMax);
   		System.out.println("Range: " + gRange);
   		System.out.println("Mean: " + gaussMean);
   		System.out.println("StDev: " + gStandardDeviation);
   		System.out.println();   		
	}
}
