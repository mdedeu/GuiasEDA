public class Utils {
		static HashMap<String, BinaryOperator<Double>> operators = new HashMap<String, BinaryOperator<Double>>(){{
        		put("+", (x,y)->x+y);
        		put("*", (x,y)->x*y);
        		put("/", (x,y)->x/y);
        		put("-", (x,y)->x-y);
    		}};

	static public boolean isConstant(String data) {
		try {
			Double.valueOf(data);
		}
		catch(NumberFormatException e) 	{
			return false;
		}
		return true;
	}
	
	static public double getDoubleConstant(String data) {
		return Double.valueOf(data);
	}
	
	
	static public boolean isOperator(String data) {
		return data.matches("\\+|-|\\^|\\*|/" );
	}
	
	public static boolean isOpenParenthesis(String data) {
		return data.contentEquals("(");
	}
	
	public static boolean isCloseParenthesis(String data) {
		return data.contentEquals(")");
	}
}
