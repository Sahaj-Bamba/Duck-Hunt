package DuckHunt.Request;

import java.io.Serializable;

public class GroupDetails implements Serializable {
	
	private String clientName;
	private String password;
	private String groupName;
	private String type;
	
	public GroupDetails(String password, String groupName, String clientName, String type){
		this.password = password;
		this.groupName = groupName;
		this.clientName = clientName;
		this.type = type;
	}
	
	public String get_password() {
		return password;
	}
	
	public String get_group_name() {
		return groupName;
	}
	
	public String get_client_name() {
		return clientName;
	}
	
	@Override
	public String toString() {
		return type;
	}
	
}
