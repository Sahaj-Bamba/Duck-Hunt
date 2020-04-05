/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DuckHunt.Constant;

/**
 *
 * @author Sahaj
 */
public enum MessageType {
	
	UserToUser(),
	UserToGroup(),
	Provoke(),
	;
	
	int Responses(int S){
		return S;
	}
	
}
