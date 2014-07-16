package Algorithm;

public class Roman {
	static final String[] v = {"M", "CM", "D", "CD", "C", "XC", "L",
		"XL", "X", "IX", "V", "IV", "I"};
	static final int[]    bv  = {1000, 900, 500, 400,  100,   90,  50,
		40,   10,    9,   5,   4,    1};
	public static String decimalToRoman(int binary) {
		String roman = "";
		for (int i = 0; i < v.length; i++) {
			while (binary >= bv[i]) {
				binary -= bv[i];
				roman  += v[i];
			}
		}
		return roman;
	}  
	public static int romanToDecimal(String number)
	{
		int res=0;
		for(int i=0; i<v.length; i++)
			while(number.startsWith(v[i]))
			{
				number=number.substring(v[i].length());
				res+=bv[i];
			}
		return res;
	}
}




