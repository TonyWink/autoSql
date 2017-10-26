package com.wink.sql.test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wink.sql.core.ConfigManager;
import com.wink.sql.core.SqlOperater;
import com.wink.sql.core.SqlFactory;
import com.wink.sql.enums.SqlType;
import com.wink.sql.mapper.ParmMapper;
import com.wink.sql.order.OrderSet;
import com.wink.sql.test.beans.Student;
import com.wink.sql.utils.PropertyUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.IntrospectionException;
import java.util.Date;
import java.util.List;

public class CRUDTest {

    @Test
    public void baseTest() throws NoSuchFieldException {
   // Boolean rs=PropertyUtils.isPrimaryKey(Student.class.getDeclaredField("name"));System.out.println(rs);
//        Student st=new Student();
//        st.setAge((long) 11);
//        st.setSex('n');
//        SqlFactory.createTable(Student.class);


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
        DataSource dataSource= new ComboPooledDataSource();
        ConfigManager.setDataSource(dataSource);
        for(int i=0;i<10;i++) {
            String sql = SqlFactory.createBaseSql(SqlType.INSERT, new Student());
            Student st = new Student();
            st.setSex("m");
            st.setAge((long) 10);
            st.setCount(10);
            st.setName("name0"+i);
            st.setTime(new Date());
            SqlOperater.execute(sql, ParmMapper.fieldMapping(st));
        }

    }


    @Test
    public void testCreateTable(){
       OrderSet o=new OrderSet();
       o.createTable(Student.class);
       System.out.println(o);

    }

	@Test
	public void JDBCTest() throws Exception{
        DataSource dataSource= new ComboPooledDataSource();
        ConfigManager.setDataSource(dataSource);

        Student st=new Student();
        st.setCount(10);
		StudentDao dao=new StudentDao();
		Student list=dao.selectById(st);
        System.out.println(list);

	}
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
