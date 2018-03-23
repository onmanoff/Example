package annotation;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import model.AnnotationA;
import model.Master;

public class AnnotationMain {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, URISyntaxException {
		Class<?>[] classes = getAllClasses("model");
		Master m = null;
		for (Class<?> clazz : classes) {
			if (clazz.isAnnotationPresent(AnnotationA.class)) {
				m = (Master) clazz.newInstance();
			}
		}
		if (m != null) {
			m.process();
		}

	}

	public static Class<?>[] getAllClasses(String pckgname) {
		Class<?>[] classesA = null;
		try {
			ArrayList<Class<?>> classes = new ArrayList<>();
			// Get a File object for the package
			File directory = null;
			try {
				directory = new File(Thread.currentThread().getContextClassLoader().getResource(pckgname.replace('.', '/')).getFile());
			} catch (NullPointerException x) {
				System.out.println("Nullpointer");
				throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
			}
			if (directory.exists()) {
				// Get the list of the files contained in the package
				String[] files = directory.list();
				for (int i = 0; i < files.length; i++) {
					// we are only interested in .class files
					if (files[i].endsWith(".class")) {
						// removes the .class extension
						System.out.println("==============>" + files[i]);
						classes.add(Class.forName(pckgname + '.' + files[i].substring(0, files[i].length() - 6)));
					}
				}
			} else {
				System.out.println("Directory does not exist");
				throw new ClassNotFoundException(pckgname + " does not appear to be a valid package");
			}

			classesA = new Class[classes.size()];
			classes.toArray(classesA);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return classesA;

	}

}
