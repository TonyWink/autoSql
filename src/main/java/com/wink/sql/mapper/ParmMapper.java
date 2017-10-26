package com.wink.sql.mapper;

import com.wink.sql.annonations.ID;
import com.wink.sql.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ParmMapper {
    private static final Logger LOGGER= LogManager.getLogger(ParmMapper.class);

    public static <T> HashMap<Integer,Object> fieldMapping(T t){
        HashMap<Integer, Object> parms = new HashMap<>();
        int index=0;
        PropertyDescriptor[] properties = PropertyUtils.getSortProperties(t.getClass());
        try {
            for (PropertyDescriptor property : properties) {
                Method method = property.getReadMethod();
                Object obj = method.invoke(t);
                if(obj!=null) {
                    parms.put(index, obj);
                    LOGGER.info("index:"+(index+1)+"--"+property.getName()+"-->"+obj);
                    index++;
                }
            }
        }catch (Exception e){
            LOGGER.error("fieldMapping error :"+e);
        }
        return parms;
    }

    public static <T> HashMap<Integer,Object> keyMapping(T t){
        Field[] fields=t.getClass().getDeclaredFields();
        HashMap<Integer, Object> parms = new HashMap<>();
        for(Field field:fields){
            ID id=field.getDeclaredAnnotation(ID.class);
            if(id!=null){
                Object obj=PropertyUtils.getPropertyValue(field.getName(),t.getClass());
                parms.put(0,obj);
                LOGGER.info(field.getName()+"-->"+obj);
            }
        }
        return parms;
    }

    public static <T> HashMap<Integer,Object> keyAndFieldMapping(T t){
        HashMap<Integer,Object> parms=fieldMapping(t);
        parms.put(parms.size()+1,keyMapping(t).get(0));
        return parms;
    }
}
