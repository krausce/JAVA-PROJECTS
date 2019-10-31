import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Program for generating kanji component dependency order via topological sort.
 * 
 * @author Chris Kraus, Acuna
 * @version 1.0
 */
public class KrausMain {
    
    /**
     * Entry point for testing.
     * @param args the command line arguments
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {

        BufferedReader indexReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-kanji.txt")), "UTF8"));
        BufferedReader mapReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data-components.txt")), "UTF8"));
        HashMap<Integer, String> charMap = new HashMap<>();
        BetterDiGraph diGraph = new BetterDiGraph();
        String lineOfText;
        
        
        try {
			while((lineOfText = indexReader.readLine()) != null) {
				while(lineOfText.contains("#")) {
					lineOfText = indexReader.readLine();
				}
				String[] chars = lineOfText.split("\t");
				charMap.put(Integer.parseInt(chars[0]), chars[1]);
				diGraph.addVertex(Integer.parseInt(chars[0]));
			}
			while((lineOfText = mapReader.readLine()) != null) {
				while(lineOfText.contains("#")) {
					lineOfText = mapReader.readLine();
				}
				String[] nums = lineOfText.split("\t");
				diGraph.addEdge(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String dotFormat = stringFormatter(diGraph, charMap);

        IntuitiveTopological topoSorter = new IntuitiveTopological(diGraph);
        Iterable<Integer> sortedTopo = topoSorter.order();
        for(Integer i: sortedTopo)
        	System.out.print(charMap.get(i));
        
        
        /*
         * String dotFormat="1->2;1->3;1->4;4->5;4->6;6->7;5->7;3->8;3->6;8->7;2->8;2->5;";
        	createDotGraph(dotFormat, "DotGraph");
         * */
    }
    
    private static String stringFormatter(BetterDiGraph diGraph, HashMap<Integer, String> charMap) {
    	String dotFormat = "";
        for(Integer i: diGraph.vertices()) {
        	for(Integer j: diGraph.getAdj(i)) {
        		if(j != null) {
        			dotFormat = dotFormat.concat(charMap.get(i) + "->" + charMap.get(j) + ";");
        		} else {
            		dotFormat = dotFormat.concat(charMap.get(i) + ";");
            	}
        	}
        }
        return dotFormat;
	}
}