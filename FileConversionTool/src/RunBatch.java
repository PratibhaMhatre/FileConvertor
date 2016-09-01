import org.springframework.batch.core.launch.support.CommandLineJobRunner;

public class RunBatch {

	public static void main(String[] args) {
		try {
			
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
