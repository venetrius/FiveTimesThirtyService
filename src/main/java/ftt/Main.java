package ftt;

public class Main {
	public static void main(String[] args) {
		String[] arr = "1_2_3_4_5".split("_");
		for(String s : arr) {
			System.out.println(s);
		}
	}
}
