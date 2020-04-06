package DuckHunt.Request;

import DuckHunt.Constant.Request;

import java.io.Serializable;

public class NewRound implements Serializable {
	
	@Override
	public String toString() {
		return String.valueOf(Request.NEWROUND);
	}

}
