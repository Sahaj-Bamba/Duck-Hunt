package DuckHunt.Constant;

public enum Request {
	
	WHOIAM("0"),
	GROUPPASS("1"),
	RESPONSE("2"),
	GROUPLIST("3"),
	
	CREATEGROUP("4"),
	JOINGROUP("5"),
	LISTGROUP("6"),
	
	MESSAGE("7"),
	MEMBERADD("8"),
	MEMBERREMOVE("9"),
	STARTGAME("10"),
	GAMESTATE("11"),
	MOVE("12"),
	GAMEOVER("13"),
	MOVETOSTART("14"),
	
	RANDOM("15"),
	
	SCORE ("16"),
	NEWROUND("17"),
	OPPONENTADDRESS("18"),
	OPPONENTCAMERAFEED("19"),
	;
	
	
	
	Request(String s){
		s.toString();
	}
}
