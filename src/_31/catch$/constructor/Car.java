package _31.catch$.constructor;

public class Car {

	/*
	 * if constructor is not declared ;
	 * 
	 * Default constructor cannot handle exception type Exception thrown by
	 * implicit super constructor. Must define an explicit constructor
	 */
	int hp = getEngineHP();

	private int getEngineHP() throws Exception {
		return 100;

	}
	
	public Car() throws Exception { 
		
	}

}
