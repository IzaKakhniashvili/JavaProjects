/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String NAME;
	private ArrayList<Integer> RANKS;
	private StringTokenizer tokens;
	private int rank;
	

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers giving
	 * the rank of that name for each decade.
	 */
	public NameSurferEntry(String line) {
		RANKS = new ArrayList<Integer>();
		tokens = new StringTokenizer(line);
		NAME = tokens.nextToken();
		for (int i = 0; i < NDECADES; i++) {
			rank = Integer.parseInt(tokens.nextToken());
		    RANKS.add(rank);

		}
		// You fill this in //
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		// You need to turn this stub into a real implementation //
		return NAME;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The decade
	 * value is an integer indicating how many decades have passed since the first
	 * year in the database, which is given by the constant START_DECADE. If a name
	 * does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {
		// You need to turn this stub into a real implementation //
		
		int devider =10; 
		int decadeRank = (decade - START_DECADE) / devider;
		return RANKS.get(decadeRank);
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a NameSurferEntry.
	 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		return NAME + " " + RANKS;
	}
}
