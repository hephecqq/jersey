package com.dagong.restful.entity;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * @XmlRootElement注解的作用
 * Maps a class or an enum type to an XML element. 
 * @author DAGONG
 *
 */
@XmlRootElement
public class Student {

	private int id;
    private String name;
    private String dept;
	public Student(int i, String string, String string2) {
		this.id=i;
		this.name=string;
		this.dept=string2;
	}
	public Student(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}
