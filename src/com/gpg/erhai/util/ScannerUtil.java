package com.gpg.erhai.util;

import java.util.Scanner;

public class ScannerUtil {
	public final static Scanner sc = new Scanner(System.in);
	
	public static String getString() {
		return sc.nextLine();
	}
	
	public static char getChar() {
		return sc.next().charAt(0);
	}
	
}
