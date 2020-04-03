package DuckHunt.Request;

import DuckHunt.Constant.Request;

import java.io.Serializable;

public class GroupList implements Serializable {
	
	private String groupName;
	private int number;
	private String[] clients;
	
	public GroupList(String groupName){
		this.groupName = groupName;
	}
	
	public GroupList(int number, String[] clients) {
		this.number = number;
		this.clients = clients;
	}
	
	public GroupList(String[] clients) {
		this.clients = clients;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String[] getClients() {
		return clients;
	}
	
	@Override
	public String toString() {
		return String.valueOf(Request.LISTGROUP);
	}
	
}
