package dynamic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import model.Master;

public class DynamicMain {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream("test.properties"));
		Master m;
		m = (Master) Class.forName(p.getProperty("class")).newInstance();
		m.process();

	}

}
