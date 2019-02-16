import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PeppersPillMill {

	public static void main(String[] args) throws IOException {
		Files.list(Paths.get(".")).forEach(System.out::println);
		TrialGroup[] trialGroupsArray = new TrialGroup[3];
		
		for(int i = 0; i < 3; i++) {
			System.out.print("Enter the data file name you would like to analyze to include the file extension: ");
			String dataFileName = getUserInput();
			trialGroupsArray[i] = new TrialGroup(dataFileName);			
		}
		
		System.out.print("Enter the file name you would like to export the results to including the file extension: ");
		String outputFileName = getUserInput();
		boolean results_1 = compareTrialGroups(trialGroupsArray[0], trialGroupsArray[1]);
		boolean results_2 = compareTrialGroups(trialGroupsArray[0], trialGroupsArray[2]);
		boolean results_3 = compareTrialGroups(trialGroupsArray[1], trialGroupsArray[2]);
		
		FileWriter output_file = new FileWriter(outputFileName);
		BufferedWriter output_buffer = new BufferedWriter(output_file);
		output_buffer.write("Statistics: \n");
		
		for(int i = 0; i < 3; i++) {
			output_buffer.write(trialGroupsArray[i].getFileName() + ": average = " + trialGroupsArray[i].getAverage() + ", standard deviation = " + trialGroupsArray[i].getStandardDeviation() + "\n");
		}
		
		output_buffer.write("Results: \n");
		output_buffer.write(trialGroupsArray[0].getFileName() + " vs. " + trialGroupsArray[1].getFileName() + ": " + results_1 + "\n");
		output_buffer.write(trialGroupsArray[0].getFileName() + " vs. " + trialGroupsArray[2].getFileName() + ": " + results_2 + "\n");
		output_buffer.write(trialGroupsArray[1].getFileName() + " vs. " + trialGroupsArray[2].getFileName() + ": " + results_3 + "\n");
		output_buffer.close();
		
	}
	
	private static String getUserInput() {
		Scanner scanner = new Scanner(System.in);
		String file = scanner.nextLine();
		return file;
	}
	
	private static boolean compareTrialGroups(TrialGroup results_1, TrialGroup results_2) {
		double differenceOfAverages = results_1.getAverage() - results_2.getAverage();
		double maxStandardDeviation = Math.max(results_1.getStandardDeviation(), results_2.getStandardDeviation());
		return differenceOfAverages > maxStandardDeviation;
	}
}
