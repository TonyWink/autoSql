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
	private String sex;

	private Date time;

	private Integer count;

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", age=" + age +
				", sex='" + sex + '\'' +
				", time=" + time +
				", count=" + count +
				'}';
	}
}
