package com.wink.sql.test.beans;


import com.wink.sql.annonations.Column;
import com.wink.sql.annonations.ID;
import com.wink.sql.annonations.Table;

import java.util.Date;

@Table("sy_stu")
public class Student {

	@ID
	@Column("MY_NAME")
	private String name;
	private Long age;
	@Column("gender")
	private Character sex;

	private Date time;

	private int count;

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

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
