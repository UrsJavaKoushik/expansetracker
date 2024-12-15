package com.i3t.expanse.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class ConvertImageToBase64 {
	public static void main(String[] args) {
		String imagePath = "src/main/resources/images/image05.jpg";
		String base64Image = convertImageToBase64(imagePath);
		System.out.println(base64Image);
	}

	public static String convertImageToBase64(String imagePath) {
		String base64Image = "";
		try {
			File file = new File(imagePath);
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.getEncoder().encodeToString(imageData);
			imageInFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return base64Image;
	}
}
