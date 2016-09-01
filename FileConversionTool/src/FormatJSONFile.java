import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class FormatJSONFile {

	public static String outputHeader = "";
	public static Map<String, Object> headerForInVoMap = new LinkedHashMap<String, Object>();

	public Map<String, Object> formatJsonFile(String inputFilePath, String outputFilePath) {
		return formatJsonFileNew(inputFilePath, outputFilePath);
	}

	public Map<String, Object> formatJsonFileNew(String inputFilePath, String outputFilePath) {
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(inputFilePath));
			File file = new File(outputFilePath);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file);

			bw = new BufferedWriter(fw);

			boolean isFirstAttribute = true;
			String firstAttribute = "";
			StringBuilder sbWriteLine = new StringBuilder();
			String searchString = "";
			String replaceString = "";
			boolean isFirstTime = true;
			int i = 299;
			while ((sCurrentLine = br.readLine()) != null) {

				if (isFirstAttribute) {
					int startIndex = sCurrentLine.indexOf("\"");

					int lastIndex = sCurrentLine.indexOf("\"", startIndex + 1);

					firstAttribute = sCurrentLine.substring(startIndex + 1, lastIndex);

					isFirstAttribute = false;
					searchString = "},{\"" + firstAttribute + "\":";

					replaceString = "}\n{\"" + firstAttribute + "\":";
				}

				sbWriteLine.append(sCurrentLine);
				if (i == 300) {
					sbWriteLine = replaceAll(sbWriteLine, "{\"" + firstAttribute + "\":",
							"\n{\"" + firstAttribute + "\":");
				}
				checkHeader(isFirstTime, sbWriteLine);
				i--;

				if (i == 0) {
					sbWriteLine = replaceAll(sbWriteLine, searchString, replaceString);
					bw.write(sbWriteLine.toString());
					// removed header part
					sbWriteLine = new StringBuilder();
					i = 300;
				}
			}
			if (i != 0) {
				sbWriteLine = replaceAll(sbWriteLine, searchString, replaceString);
				bw.write(sbWriteLine.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return headerForInVoMap;
	}

	public static StringBuilder replaceAll(StringBuilder builder, String searchString, String replaceString) {

		int index = builder.indexOf(searchString);

		while (index != -1) {
			builder.replace(index, index + searchString.length(), replaceString);

			index += replaceString.length(); // Move to the end of the
			index = builder.indexOf(searchString, index);
		}
		return builder;
	}

	public static void checkHeader(boolean isFirstTime, StringBuilder sbWriteLine) throws JSONException {

		System.out.println("in checkHeader method");
		if (isFirstTime) {
			System.out.println("in if checkHeader method");
			int lastLength = sbWriteLine.indexOf("\n");

			JSONObject jsonObject = null;
			if (lastLength > 0) {
				System.out.println("in if lastlength checkHeader method");
				jsonObject = new JSONObject(sbWriteLine.substring(0, sbWriteLine.indexOf("\n")));
			} else {
				System.out.println("in else lastlength checkHeader method");
				jsonObject = new JSONObject(sbWriteLine.toString());
			}
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = jsonObject.keys();

			boolean isFirstLine = true;
			while (iterator.hasNext()) {
				String key = (String) iterator.next();

				headerForInVoMap.put(key, "String");

				if (!isFirstLine) {
					outputHeader = outputHeader.concat(",").concat(key);

				} else {
					outputHeader = key;
					isFirstLine = false;
				}
			}
			isFirstTime = false;
		}
	}
}