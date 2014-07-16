package Algorithm;

public class Base {
	static String l = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public static String convert(String n, int startbase, int base)
	{
		//2<=base<=62
		long num=0;
		for(int i=0; i<n.length(); i++)
			num+=l.indexOf(n.charAt(n.length()-i-1))*(long)(Math.pow(startbase,i));
		String res="";
		int exp=(int) (Math.log10(num)/Math.log10(base));
		int t;
		long diff;
		for(int i=exp; i>=0; i--)
		{
			t = (int) (num/(long)Math.pow(base,i));
			diff=num-t*((long)Math.pow(base, i));
			res+=l.substring(t,t+1)+"";
			num=diff;
		}
		return res;
	}
}
