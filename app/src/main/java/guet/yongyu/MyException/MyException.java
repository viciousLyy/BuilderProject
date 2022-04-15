package guet.yongyu.MyException;

public class MyException extends Exception{

    private String message = "";

    public MyException(String msg){
        super(msg);
        this.message = msg;
    }

     public void writeErr2File(){

     }
}
