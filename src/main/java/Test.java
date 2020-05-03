import java.util.Base64;

public class Test {
	
	public static void main(String[] args) {
	//System.out.println(System.getProperty("user.home"));
	String a=Base64.getEncoder().encodeToString("asd".getBytes());
	System.out.println(a);
	System.out.println(new String(Base64.getDecoder().decode("cXdlcXdlMQ==")));
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
