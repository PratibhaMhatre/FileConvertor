import org.springframework.batch.core.launch.support.CommandLineJobRunner;

public class RunBatch {

	public static void main(String[] args) {
		try {
			
			/*
			 * inputPath=C:\Users\pratibha.ghorpade\FileConversion\file.json.
			 * formatted
			 * outputPath=C:\Users\pratibha.ghorpade\FileConversion\file.
			 * csvoutputHeaders=modified1,identifier1,brandNew,
			 * testoutputFieldSeperator=,
			 */
			
			CommandLineJobRunner.main(new String[] { "applicationContext.xml", "jsonToCsvBatchJob",
					"inputPath=" + args[0],
					"outputPath=" + args[1],
					"outputHeaders=" +args[2],
					"outputFieldSeperator=" + args[3]});
		} catch (Exception ex) {
			ex.printStackTrace();			
		}

	}

}
