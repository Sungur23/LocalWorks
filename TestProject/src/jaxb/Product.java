package jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Product {

	@XmlAttribute(name = "ID")
	private int ID;

	@XmlElement(name = "ProductName")
	private String ProductName;

	@XmlElement(name = "Model")
	private String Model;
	
	@XmlElementWrapper(name = "UserList") 
	@XmlElement(name = "User")
	private List<User> userList;

	public Product(int iD, String productName, String model) {
		super();
		ID = iD;
		ProductName = productName;
		Model = model;
		userList = new ArrayList<User>();
	}
	public Product() {
		super();
		userList = new ArrayList<User>();
	}
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}
		
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
    public String toString() {
        return "ID = " + ID + " ProductName=" + ProductName + " Model=" + Model;
    }
	
}
