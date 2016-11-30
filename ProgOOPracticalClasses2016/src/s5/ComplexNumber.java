package s5;

public class ComplexNumber {

	private double re;

	private double im;
	
	
	public ComplexNumber(ComplexNumber cn){
		this(cn.getReal(), cn.getImaginary());
	}
	
	public ComplexNumber(){
		this(0.0, 0.0);
	}
	
	public ComplexNumber(double real, double imaginary){
		re = real;
		im = imaginary;
	}
	
	
	
	public ComplexNumber conjugate(){
		this.im = -(this.im);
		return this;
	}
	
	public ComplexNumber add(ComplexNumber other) {
		return new ComplexNumber(this.getReal() + other.getReal(), this.getImaginary() +other.getImaginary());

	}
	
	
	
	
	
	
	
	
	public double getReal() {
		return re;
	}

	public double getImaginary() {
		return im;	
	}
	
	
}
