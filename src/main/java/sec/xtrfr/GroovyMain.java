package sec.xtrfr;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public class GroovyMain {

    public static void main(String[] args) throws Exception {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("groovy");
        System.out.println(scriptEngine.eval("\"whoami\".execute().text"));
    }

}
