package gui;

public class Interest {
	
	public double simpleInterest(double principal, double rate, double years) {
		double simpleAmount = principal + (principal * (rate/100) * years);
		return simpleAmount;
	} 
	
	public double compoundInterest(double principal, double rate, double years) {
		double multiplyByYearExpo = (1 + rate/100);		
		double result = 1.0;
		
		while (years != 0) {
			result *= multiplyByYearExpo;
			years--;
		}
		double compoundAmount = principal * result;
		return compoundAmount;
	}
}