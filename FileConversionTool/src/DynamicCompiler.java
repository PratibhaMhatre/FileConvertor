import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class DynamicCompiler {

	public static class MyDiagnosticListener implements
			DiagnosticListener<JavaFileObject> {
		public void report(Diagnostic<? extends JavaFileObject> diagnostic) {

			System.out.println("Line Number->" + diagnostic.getLineNumber());
			System.out.println("code->" + diagnostic.getCode());
			System.out.println("Message->"
					+ diagnostic.getMessage(Locale.ENGLISH));
			System.out.println("Source->" + diagnostic.getSource());
			System.out.println(" ");
		}
	}

	public void compile(Iterable<? extends JavaFileObject> files) {
		// get system compiler:
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		System.out.println("COMPILER: "+compiler);

		// for compilation diagnostic message processing on compilation
		// WARNING/ERROR
		MyDiagnosticListener diagnosticListner = new MyDiagnosticListener();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnosticListner, Locale.ENGLISH, null);
		/*String workingHomeDir = System.getProperty("user.home");		
		Iterable<String> options = Arrays.asList("-d", workingHomeDir + "\\target-classpath");*/
		
		Iterable<String> options = Arrays.asList("-d", FileConversionConstants.LIB_PATH);	
		

		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				diagnosticListner, options, null, files);
		Boolean result = task.call();
		if (result == true) {
			//System.out.println("Succeeded");
		}
	}

	/**
	 * java File Object represents an in-memory java source file <br>
	 * so there is no need to put the source file on hard disk
	 **/
	public static class InMemoryJavaFileObject extends SimpleJavaFileObject {
		private String contents = null;

		public InMemoryJavaFileObject(String className, String contents)
				throws Exception {
			super(URI.create("string:///" + className.replace('.', '/')
					+ Kind.SOURCE.extension), Kind.SOURCE);
			this.contents = contents;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors)
				throws IOException {
			return contents;
		}
	}

}
