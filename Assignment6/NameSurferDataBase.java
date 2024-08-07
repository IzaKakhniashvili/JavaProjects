
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
* File: NameSurferDataBase.java
* -----------------------------
* This class keeps track of the complete database of names.
* The constructor reads in the database from a file, and
* the only public method makes it possible to look up a
* name and get back the corresponding NameSurferEntry.
* Names are matched independent of case, so that "Eric"
* and "ERIC" are the same names.
*/

public class NameSurferDataBase implements NameSurferConstants {

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the requested
	 * file does not exist or if an error occurs as the file is being read.
	 */
	HashMap<String, String> NameConstants = new HashMap<String, String>();
	private ArrayList<String> data = new ArrayList<String>();

	public NameSurferDataBase(String filename) {
		try {
			BufferedReader FileReader = new BufferedReader(new FileReader(filename));
			while (true) {
				String FileLine = FileReader.readLine();
				if (FileLine == null)
					break;
				data.add(FileLine);
			}
			FileReader.close();
		} catch (IOException e) {
			throw new Error();
		}
		for (int i = 0; i < data.size(); i++) {
			StringTokenizer line = new StringTokenizer(data.get(i));
			String key = line.nextToken();
			NameConstants.put(key, data.get(i));
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If the
	 * name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub into a real implementation //
		if(NameConstants.containsKey(name)) {
			NameSurferEntry entry = new NameSurferEntry(NameConstants.get(name).toString());
			return entry;
		}else {
			return null;
		}
	}
}
