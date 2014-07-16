package Algorithm;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
public class Prime {
	static int pl;
	static long[] modulo;
	static long count=0;
	static long[] p = new long[] {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199};
	//167,772,156 HeapSP
	//15,499,251 TCHeapSP
	public static boolean isPrimeInt(int x)
	{
		//naive algorithm runtime sqrt(x)/4 + 2 for all x>=0;
		if(x==2 || x==3) return true;
		if(x==1 || x%2==0 || x%3==0) return false;
		int z=(int)Math.sqrt(x);
		for(int i=5; i<=z; i+=2+((i-1)%4))
			if(x%i==0) return false;
		return true;
	}
	public static long isPrimeLongrF(long x)
	{
		//returns factor
		//naive algorithm runtime sqrt(x)/4 + 2 for all x>=0;
		if(x==2 || x==3) return -1;
		if(x==1) return 1; 
		if(x%2==0) return 2;
		if(x%3==0) return 3;
		long z=(long)Math.sqrt(x);
		for(long i=5; i<=z; i+=2)//+((i-1)%4))
			if(x%i==0) return i;
		return -1;
	}
	public static boolean isPrimeLong(long x)
	{
		//naive algorithm runtime sqrt(x)/4 + 2 for all x>=0;
		if(x==2 || x==3) return true;
		if(x==1 || x%2==0 || x%3==0) return false;
		long z=(long)Math.sqrt(x);
		for(long i=5; i<=z; i+=2)//+((i-1)%4))
			if(x%i==0) return false;
		return true;
	}
	public static boolean isPrime(int n) {
		return !new String(new char[n]).matches(".?|(..+?)\\1+");
	}
	public static boolean millerRabinPrime(long number, int iterations)
	{
		if (number <= 1 || (number & 1) == 0)
		{
			if (number == 2)
				return true;
			return false;
		}
		else if (number == 3)
			return true;
		long s = 1;
		while ((number - 1 & 1 << s) == 0)
			++s;
		long d = (number - 1) / (1 << s);
		Random generator = new Random();
		for (int i = 1; i <= iterations; ++i)
		{
			double aa = generator.nextDouble();
			long a = (long)(aa*(number-3));
			a+=2;
			long x = safe_pow(a, d, number);
			if (x == 1 || x == number - 1)
				continue;
			boolean gotoLoop = false;
			for (int r = 1; r < s && !gotoLoop; ++r)
			{
				x = x * x % number;
				if (x == 1)
					return false;
				else if (x == number - 1)
				{
					gotoLoop = true;
					break;
				}
			}
			if (!gotoLoop)
				return false;
		}
		return true;
	}
	public static long safe_pow(long base, long power, long mod)
	{
		if (power == 0)
			return 1;
		else if (power == 1)
			return base;
		else if ((power & 1) == 0)
			return safe_pow((base * base % mod), power / 2, mod) % mod;
		else
			return safe_pow((base * base % mod), power / 2, mod) * base % mod;
	}
	public static double gcd(double a, double b)
	{
		if (b==0) return a;
		return gcd(b,a%b);
	}
	public static double quality(double i,double j,int k)
	{
		double res = Math.floor(Math.pow(j/i,k));
		String rr = res+"";
		rr = rr.substring(rr.length()-2);
		BigInteger ii = new BigInteger(i+"");
		BigInteger jj = new BigInteger(j+"");
		BigInteger diff =  jj.pow(k).subtract(ii.pow(k).multiply(new BigInteger(rr)));
		ArrayList<BigInteger> r = new ArrayList<BigInteger>();
		ArrayList<BigInteger> r1 = primeDecomposition(jj);
		ArrayList<BigInteger> r2 = primeDecomposition(new BigInteger(rr).multiply(ii));
		ArrayList<BigInteger> r3 = primeDecomposition(diff);
		for(int a=0; a<r1.size(); a++) 
			if(!r.contains(r1.get(a))) 
				r.add(r1.get(a));
		for(int a=0; a<r2.size(); a++) 
			if(!r.contains(r2.get(a)))
				r.add(r2.get(a));
		for(int a=0; a<r3.size(); a++) if(!r.contains(r3.get(a))) r.add(r3.get(a));
		double sum = 0;
		for(int a=0; a<r.size(); a++)
			sum+=Math.log10(r.get(a).doubleValue());
		return k*Math.log10(j)/sum;
	}
	public static ArrayList<BigInteger> primeDecomposition(BigInteger x)
	{
		ArrayList<BigInteger> p = new ArrayList<BigInteger>();
		while(!x.equals(BigInteger.ONE))
		{
			BigInteger r = factor(x);
			if(!p.contains(r))
				p.add(r);
			x.divide(r);
		}
		return p;
	}
	public static BigInteger factor(BigInteger x)
	{
		for(int i=0; i<p.length; i++)
		{
			System.out.println(i);
			BigInteger pp = new BigInteger(p[i]+"");
			if(x.mod(pp).equals(BigInteger.ZERO)) return pp;
		}

		for(double i=p[p.length-1]; x.compareTo(new BigInteger(i*i+""))==1; i+=2)
		{
			BigInteger ii = new BigInteger(i+"");
			if(x.mod(ii).equals(BigInteger.ZERO)) 
				return ii;
		}
		return x;
	}
	public static void main(String[] args) 
	{
		/*for(double i=5000; i<50000; i++)
			{
				if(i%1000==0) System.out.println(i/1000);
				for(double j=i+1; j<100000; j++)
				{
					if(gcd(j,i)==1)
					{
						for(double k=2; k<20; k++)
						{
							double res = Math.pow(j/i,k);
							double resl = Math.floor(res);
							double diff = Math.pow(j,k)-Math.pow(i,k)*resl;
							if(res-resl<.0001 && gcd(resl*i,diff)==1) 
							{
								double r = quality(i,j,k);
								if(r>1.4)
									System.out.println("("+j+"/"+i+")^"+k+"   "+diff + "  rad:"+r);
							}
						}	
					}
				}
			}*/
		/*
			BigInteger aa = new BigInteger("50");
			ArrayList<BigInteger> x = primeDecomposition(aa);
			for(int i=0; i<x.size(); i++) System.out.println(x.get(i));*/
		//System.out.println(quality(3125,43038,2));
		modulo = new long[20];
		modulo[0]=1l;
		for(int i=1; i<20; i++)
			modulo[i]=10*modulo[i-1];
		
		pl = 14;
		BigInteger xx = new BigInteger("4294967297");
		long ppp = 99999640000243l;
		long c = System.currentTimeMillis();
		System.out.println("result: "+semiPrimeBI(xx,new BigInteger("7"),BigInteger.ONE,1));
		System.out.println("result: "+semiPrime(ppp,3,1,1));
		long cc = System.currentTimeMillis()-c;
		System.out.println("RUNTIME:"+ cc + " iter:"+count);
		long ccc = System.currentTimeMillis();
		System.out.println("result: "+isPrimeLongrF(ppp));
		long cccc = System.currentTimeMillis()-ccc;
		System.out.println("sRUNTIME:"+ cccc + " iter:"+(long)(Math.sqrt(ppp)/2));
	}
	public static long semiPrime(long p, long x, long y, int size)
	{
		if(x==p) return -1; 
		if(p==x*y && x!=1) 
		{
			return x;
		}
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
			{
				count++;
				long mod = modulo[size+1];
				long l = i*mod/10+x;
				long m = j*mod/10+y;
				if(l*m<=p && size<pl)
					if(l*m % mod == p %mod)
					{
						long res = semiPrime(p,l,m,size+1);
						if(res!=-1) return res;
					}
			}
		return -1;
	}
	public static BigInteger semiPrimeBI(BigInteger p, BigInteger x, BigInteger y, int size)
	{
		count++;
		if(x.equals(p)) return BigInteger.ONE.negate(); 
		if(p.equals(x.multiply(y)) && !x.equals(BigInteger.ONE)) 
			return x;
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
			{
				BigInteger mod =  new BigInteger(modulo[size+1]+"");
				BigInteger l = mod.divide(BigInteger.TEN).multiply(new BigInteger(i+"")).add(x);
				BigInteger m = mod.divide(BigInteger.TEN).multiply(new BigInteger(j+"")).add(y);;
				if(!(l.multiply(m).compareTo(p)==1) && size<pl)
					if(l.multiply(m).mod(mod) .equals( p.mod(mod)))
					{
						BigInteger res = semiPrimeBI(p,l,m,size+1);
						if(res!=BigInteger.ONE.negate()) return res;
					}
			}
		return BigInteger.ONE.negate();
	}
}