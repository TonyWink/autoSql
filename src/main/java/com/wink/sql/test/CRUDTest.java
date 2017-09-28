package com.wink.sql.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wink.sql.core.Manager;
import com.wink.sql.enums.Constraint;
import com.wink.sql.order.ColumnConfig;
import com.wink.sql.order.OrderSet;
import com.wink.sql.test.beans.Student;
import com.wink.sql.utils.PropertyUtils;
import org.junit.Test;

import java.beans.IntrospectionException;

public class CRUDTest {

    @Test
    public void baseTest() throws NoSuchFieldException {
   // Boolean rs=PropertyUtils.isPrimaryKey(Student.class.getDeclaredField("name"));System.out.println(rs);
    }

	@Test
	public void Protest() throws IntrospectionException {
		System.out.println(PropertyUtils.getProperties(Student.class));
	}

    @Test
    public void testFIledNames(){
        System.out.println(PropertyUtils.getFieldNames(Student.class));
    }

    @Test
    public void testDataSource(){
        Manager manager=new Manager(new ComboPooledDataSource());

    }


    @Test
    public void testCreateTable(){
       OrderSet o=new OrderSet();
       Constraint[] cons={Constraint.UNIQUE,Constraint.NOTNULL.setParm(50)};
       o.createTable(Student.class,new ColumnConfig(1,30,cons));
       System.out.println(o);

    }

//	@Test
//	public void JDBCTest() throws Exception{Exception
//		System.out.println(new Student());
//		List<Student> list=dao.selectAll(new Student());
//		System.out.println(list);
//
//		Student s=new Student();
//		s.setName("11");
//		s.setAge((long) 100);
//		s.setSex("nv");
//		System.out.println(s);
//		System.out.println(dao.insert(s));
//
//		TeacherDao dao=new TeacherDao();
//		Teacher t=new Teacher();
//		t.setId(2);
//		t.setName("www");
//		System.out.println(dao.update(t));
//	}
//
//	@Test
//	public void orderSeTTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
//		Student s=new Student();
//		s.setAge((long) 1);
//		s.setName("11");
//		order order=new order();
//		order.update("student").set(s).byId(s);
//		System.out.println(order);
//	}

	
}
