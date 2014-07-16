package Algorithm;

public class Power {
	//Fast exponentiation by squaring
	public static long pow(long a, long b, long mod) 
	{
		long res = 1;
		while (b > 0) {
			if ((b & 1) != 0) res = (res * a) % mod;
			a = (a * a) % mod;
			b >>>= 1;
		}
		return res;
	}
	public static void main(String args[])
	{
		for(long j=10000; j<10000+10000; j++)
		{
			if(Prime.isPrimeLong(j))
			{
				long r = 7886;
				long mod = j;
				for(int i=1; i<200; i++)
				{
					long res =pow(r,i,mod);
					if(res<10)
						System.out.println(j+":  "+i+": "+res);
				}
			}
		}
	}
}
