package edu.hit.java.quiz1;

public class HitL170300901  {
	public static void main(String[] args) {
		int a = 1;
		int b = 1;
		int n = 10;
		int c;
		System.out.print("1 1 ");
		for(int i = 3; i <= n; i++) {
			c = a;
			a = b;
			b = f(c,b);
			System.out.print(b + " ");
		}
	}
	public static int f(int a, int b) {
		int c = a + b;
		return c;
	}
}
