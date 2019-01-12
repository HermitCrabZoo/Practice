package com.zoo.practice;

class VeryImportantException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString(){
        return "A very important exception";
    }
}
 
class HoHumException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toString() {
        return "A trivial exception";
    }
}
public class LostMessage {
    void f() throws VeryImportantException{
        throw new VeryImportantException();
    }
 
    void dispose() throws HoHumException{
        throw new HoHumException();
    }
 
    public static void main(String[] args) throws Exception{
    	LostMessage lm = new LostMessage();
        try{
            lm.f();
        }finally {
            lm.dispose();
        }
            
    }
}

