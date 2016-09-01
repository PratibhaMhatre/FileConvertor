

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.tools.JavaFileObject;

public class DynamicCreateJsonToCsvInVo {

	public JavaFileObject generateJava(String className, Map<String, Object> map) {

		StringBuffer classContent = new StringBuffer(
				" import java.util.LinkedHashMap; \n import java.util.Set;  \n  public class ")
						.append(className).append(" {\n");

		// first fields
		for (Entry<String, Object> field : map.entrySet()) {
			String name = field.getKey();
			String type = field.getValue().getClass().getName();
			classContent.append("\tprivate ").append(type).append(" ").append(name).append(";\n");
		}

		// then getters and setters
		for (Entry<String, Object> field : map.entrySet()) {
			String name = field.getKey();
			String type = field.getValue().getClass().getName();
			classContent.append("\n\n");

			// getter.

			classContent.append("\tpublic ").append(type).append(" get").append(name.substring(0, 1).toUpperCase())
					.append(name.substring(1)).append("() {\n\t\t");
			classContent.append("return this.").append(name).append(";\n\t}\n");

			// setter.
			classContent.append("\tpublic void").append(" set").append(name.substring(0, 1).toUpperCase())
					.append(name.substring(1)).append("(").append(type).append(" ").append(name).append(") {\n\t\t");

			if (JsonToCsvPage.isForceWrapInDoubleQuotes()) {

				classContent.append("this.").append(name).append(" = ")
						.append("FileConversionConstants.DOUBLE_QUOTE + ").append(name)
						.append("+ FileConversionConstants.DOUBLE_QUOTE").append(";\n\t}");
			} else {
				classContent.append("this.").append(name).append(" = ").append(name).append(";\n\t}");
			}
		}

		// hardcoded method signature

		classContent.append(
				"\n\n\t public static DynamicJsonInVO setAllFields(LinkedHashMap<String, Object> valueMap) { \n\t\t DynamicJsonInVO tempDynamicObj = new DynamicJsonInVO();\n\t\t Set<String> keys = valueMap.keySet(); \n");

		for (Entry<String, Object> field : map.entrySet()) {
			String fieldName = field.getKey();

			classContent
					.append("\t\t if(valueMap.get(\"" + fieldName + "\")== null) { \n\t\t\t keys.remove(\"" + fieldName
							+ "\");\n")
					.append("\t\t\t tempDynamicObj.set").append(fieldName.substring(0, 1).toUpperCase())
					.append(fieldName.substring(1)).append("(\"null\"); \n \t\t } else { \n")
					.append("\t\t\t tempDynamicObj.set").append(fieldName.substring(0, 1).toUpperCase())
					.append(fieldName.substring(1))
					.append("(valueMap.get(\"" + fieldName + "\").toString()); \n \t\t } \n");

		}

		classContent.append(" \t\t return tempDynamicObj; \n } \n }");

		JavaFileObject javaFileObj = null;

		try {

			// EXTERNAL CHANGE
			/*
			String workingHomeDir = System.getProperty("user.home");
			File file = new File(workingHomeDir + "\\target-classpath" + "\\DynamicJsonInVO.java");
			*/
			
			File file = new File(FileConversionConstants.LIB_PATH + "\\DynamicJsonInVO.java");
			

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(classContent.toString());
			bw.close();

			javaFileObj = new InMemoryJavaFileObject("DynamicJsonInVO",
					classContent.toString());

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return javaFileObj;
	}

}
