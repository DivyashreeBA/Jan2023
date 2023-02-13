
public class IsPrime {
	
	public static int isPrime(int num){
		if(num%2==0)
			return 1;
		else {
			for(int i=3;i*i<num;i+=2) {
				if(num%i==0)
					return i;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isPrime(3));
	}

}
