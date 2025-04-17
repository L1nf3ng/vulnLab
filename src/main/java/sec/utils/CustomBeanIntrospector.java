package sec.utils;

import org.apache.commons.beanutils.BeanIntrospector;
import org.apache.commons.beanutils.IntrospectionContext;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;


public class CustomBeanIntrospector implements BeanIntrospector {
    @Override
    public void introspect(IntrospectionContext var1)   {
        try{
            // 使用 Java 内省机制获取 Bean 的信息
            BeanInfo beanInfo = Introspector.getBeanInfo(var1.getTargetClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            // 遍历 Bean 的属性
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String propertyName = propertyDescriptor.getName();
                Class<?> propertyType = propertyDescriptor.getPropertyType();
                System.out.println("Property Name: " + propertyName + ", Type: " + propertyType.getName());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
