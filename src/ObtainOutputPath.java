import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;


public class ObtainOutputPath {
	
	public static File Path(File Source,String type,String keyused){
		
		String OutputFile = Source.toString();
		
		String add = null;
		
		System.out.println(OutputFile);
		
    	List<String> Destination = Arrays.asList(OutputFile.split("\\."));
    	
    	if (type.equals("encypt")){
    	
    		add = Destination.get(0)+"-Encypted"+keyused+".";
    	
    	}
    	
    	else {
    		
             add = Destination.get(0)+"-Decypted.";
            	            	
    	}
    	
    	OutputFile=add+Destination.get(1);
    	
    	File Output = new File(OutputFile);
    	
    	int counter=1;
    	
    	while(Output.exists() && !Output.isDirectory()) { 
    		
    		OutputFile = Output.toString();
    		
    		List<String> Destination2 = Arrays.asList(OutputFile.split("\\."));
    		
    		if (type.equals("encypt")){
    		
    			add = Destination.get(0)+"-Encrypted"+keyused+"("+counter+").";
    		
    		}
    		
    		else {
    			
    			add = Destination.get(0)+"-Decrypted"+keyused+"("+counter+").";
    		}
    		
    		OutputFile=add+Destination.get(1);
    		
    		Output = new File(OutputFile);
    		
    		counter++;
    		
    		
    	}
		System.out.println(counter);
		return Output;
		
	}
	
	

}
