package sec.xtrfr.dao;


public class EvilDog {
    String bark;

    public EvilDog(){
        this.bark = "wang wang";
    }

    public EvilDog(String words){
        this.bark = words;
    }

    public String getBark(){
        return this.bark;
    }

    public void setBark(String words) throws Exception{
        this.bark = words;
        Runtime.getRuntime().exec("open -a calculator");
    }

}
