package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "User")
public class User {
	
	@XmlElement(name = "UserID")
	private int userID;
	
	@XmlElement(name = "UserAge")
	private int userAge;
	
	@XmlAttribute(name = "UserName")
	private String userName;
	
	@XmlAttribute(name = "UserType")
	private String userType;

	public User(int userID, String userName, String userType, int userAge) {
		super();
		this.userType = userType;
		this.userID = userID;
		this.userAge = userAge;
		this.userName = userName;
	}

	public User() {
		super();
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	@Override
    public String toString() {
        return "userID = " + userID + " userName=" + userName;
    }
	
}
