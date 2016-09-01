

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.tools.JavaFileObject;

import org.apache.log4j.Logger;

public class DynamicJsonToCsvLineMapper {
	private static final Logger LOGGER = Logger.getLogger(DynamicJsonToCsvLineMapper.class);

	public JavaFileObject generateJavaLineMapper() {

		LOGGER.info("in DynamicJsonToCsvLineMapper");

		StringBuffer classContent = new StringBuffer(
				" \n import java.util.LinkedHashMap; \n"
						+ " import org.springframework.batch.item.file.LineMapper; "
						+ "\n import org.springframework.batch.item.file.mapping.JsonLineMapper; "
						+ "\n  public class JsonToCsvLineMapper implements LineMapper<DynamicJsonInVO> { \n private JsonLineMapper delegate; \n public JsonLineMapper getDelegate() { \n 	return delegate; \n } \n public void setDelegate(JsonLineMapper delegate) { \n this.delegate = delegate; \n } \n public DynamicJsonInVO mapLine(String line, int lineNumber) throws Exception { \n LinkedHashMap<String, Object> recordAsMap = (LinkedHashMap<String, Object>) delegate.mapLine(line, lineNumber); \n return DynamicJsonInVO.setAllFields(recordAsMap); \n }}");

		JavaFileObject javaFileObj = null;

		try {
			/*String workingHomeDir = System.getProperty("user.home");

			File file = new File(workingHomeDir + "\\target-classpath" + "\\JsonToCsvLineMapper.java");
*/
			File file = new File(FileConversionConstants.LIB_PATH +"\\JsonToCsvLineMapper.java");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(classContent.toString());
			bw.close();
			

			javaFileObj = new InMemoryJavaFileObject("JsonToCsvLineMapper",
					classContent.toString());

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return javaFileObj;
	}

}
