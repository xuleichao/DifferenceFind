
public class JavaSuNumber {
	
	public static void main(String[] args){
		int count = 0;
		for(int i=101; i<=200; i++){
			
			for (int j=2; j<=i; j++){
				if (i%j == 0){
					break;
				}
				if (j == i-1){
					count += 1;
					}
				
			
			}
		}
		System.out.println(count);
	}

}
