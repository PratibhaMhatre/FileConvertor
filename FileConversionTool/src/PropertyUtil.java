import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {



	static void saveProperties(File file, Properties p) throws IOException {
		FileOutputStream fr = new FileOutputStream(file);
		p.store(fr, "Properties");
		fr.close();
		System.out.println("After saving properties:" + p);
	}

	static void loadProperties(File file,  Properties p) throws IOException {
		FileInputStream fi = new FileInputStream(file);
		p.load(fi);
		fi.close();
		System.out.println("After Loading properties:" + p);
	}

	public static void main(String... args) throws IOException {
		File file = new File("property.dat");
		Properties table = new Properties();
		table.setProperty("Shivam", "Bane");
		table.setProperty("CS", "Maverick");
		System.out.println("Properties has been set in HashTable:" + table);
		// saving the properties in file
		saveProperties(file,table);
		// changing the property
		table.setProperty("Shivam", "Swagger");
		System.out.println("After the change in HashTable:" + table);
		// saving the properties in file
		saveProperties(file,table);
		// Loading the saved properties
		loadProperties(file,table);
	}

}
