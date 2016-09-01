

public class FileConversionConstants {

	public static final String DOUBLE_QUOTE = "\"";

	public static final String BACK_SLASH = "\\";

	public static final String JSON_TO_CSV = "Json to CSV";

	public static final String INPUT_FILE_PATH = "Enter the Input File path:";

	public static final String OUTPUT_FILE_PATH = "Enter the Output File path:";

	public static final String OUTPUT_OPTIONS = "Output Options:";

	public static final String FIELD_SEPARATOR = "Field Separator:";

	public static final String WRAP_VALUES = "Force wrap values in double quotes";

	public static final String INCLUDE_HEADERS = "Includes Headers in First row";

	public static final String COMMA = "Comma";

	public static final String SEMICOLON = "Semicolon";

	public static final String COLON = "Colon";

	public static final String JSON_INPUT_VO = "DynamicJsonInVO";
	
	// public static String UTILITY_PATH="C:\\Users\\p.nana.ghorpade";
	public static String UTILITY_PATH = getPath();
	
	public static final String LIB_PATH = UTILITY_PATH + "\\FileConversion\\tool\\jdk1.8.0_91\\bin\\FileConvert_lib\\target-classes";
	
	public static final String BATCH_JAR_PATH = UTILITY_PATH + "\\FileConversion\\tool\\jdk1.8.0_91\\bin\\RunBatch.jar";
	
	public static String getPath(){
		String workingHomeDir = System.getProperty("user.home");
		return workingHomeDir;
	}
	
	
}
