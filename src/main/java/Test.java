
public class Test {
	
	public static void main(String[] args) {
	System.out.println(asd());	
	}
	
	
	public static String  asd()
	{
		
		try {
			int a=10/10;
			return "a";
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("final");
		}
		return "b";
	}

}
