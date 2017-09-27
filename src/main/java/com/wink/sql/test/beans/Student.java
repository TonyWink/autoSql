package com.wink.sql.test.beans;


import com.wink.sql.annonations.Column;
import com.wink.sql.annonations.ID;
import com.wink.sql.annonations.Table;

@Table("sy_tallooo")
public class Student {

	@ID
	private String name;
	@Column("myAge")
	private Long age;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
}
