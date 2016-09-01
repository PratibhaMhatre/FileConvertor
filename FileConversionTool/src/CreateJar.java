import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateJar {
	final static int BUFFER = 2048;

	public static void main(String[] ar) {
		String workingHomeDir = System.getProperty("user.home");
		//System.out.println("Working directory is :: "+workingHomeDir);
		createZipArchive("C:\\Users\\p.nana.ghorpade\\FileConversion\\tool\\jdk1.8.0_91\\bin\\FileConvert_lib\\target-classes");
		
	}
	
	
	public static void createZipFile(){

		
		String workingHomeDir = System.getProperty("user.home");
		System.out.println("Working directory is :: "+workingHomeDir);

		// These are the files to include in the ZIP file
		String[] source = new String[] { "C:\\test.txt"};
		
		//String[] source = new String[] { workingHomeDir + "\\target-classpath\\JsonToCsvLineMapper.class",workingHomeDir + "\\target-classpath\\JsonToCsvLineMapper.java" };

		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
			// Create the ZIP file
			String target = workingHomeDir + "\\target.zip";
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));
			
			System.out.println(source.length);

			// Compress the files
			for (int i = 0; i < source.length; i++) {
				
				
				System.out.println("file is: "+ source[i]);
				FileInputStream in = new FileInputStream(source[i]);

				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(source[i]));

				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				// Complete the entry
				out.closeEntry();
				in.close();
			}

			// Complete the ZIP file
			out.close();
		} catch (IOException e) {
		}

	
	}
	
	public static boolean createZipArchive(String srcFolder) {

	    try {
	        BufferedInputStream origin = null;



	        FileOutputStream    dest = new FileOutputStream(new File(srcFolder+ ".jar"));

	        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
	        byte data[] = new byte[BUFFER];

	        File subDir = new File(srcFolder);
	        String subdirList[] = subDir.list();
	        for(String sd:subdirList)
	        {
	                // get a list of files from current directory
	                File f = new File(srcFolder+"/"+sd);
	                if(f.isDirectory())
	                {
	                    String files[] = f.list();

	                    for (int i = 0; i < files.length; i++) {
	                        System.out.println("Adding: " + files[i]);
	                        FileInputStream fi = new FileInputStream(srcFolder  + "/"+sd+"/" + files[i]);
	                        origin = new BufferedInputStream(fi, BUFFER);
	                        ZipEntry entry = new ZipEntry(sd +"/"+files[i]);
	                        out.putNextEntry(entry);
	                        int count;
	                        while ((count = origin.read(data, 0, BUFFER)) != -1) {
	                            out.write(data, 0, count);
	                            out.flush();
	                        }

	                    }
	                }
	                else //it is just a file
	                {
	                    FileInputStream fi = new FileInputStream(f);
	                    origin = new BufferedInputStream(fi, BUFFER);
	                    ZipEntry entry = new ZipEntry(sd);
	                    out.putNextEntry(entry);
	                    int count;
	                    while ((count = origin.read(data, 0, BUFFER)) != -1) {
	                        out.write(data, 0, count);
	                        out.flush();
	                    }

	                }
	        }
	        origin.close();
	        out.flush();
	        out.close();
	    } catch (Exception e) {
	        System.out.println("createZipArchive threw exception: " + e.getMessage());        
	        return false;

	    }


	    return true;
	}   

}
