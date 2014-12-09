package com.kalatag.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;

public class BarcodeUtil {

	public static File barcode(String text) {
		return barcode(text, text + "-barcode.jpg");
	}
	
	public static File barcode(String text, String fileName) {
		BitMatrix bitMatrix;
		try {
			bitMatrix = new Code128Writer().encode(text, BarcodeFormat.CODE_128, 100, 40);
//			bitMatrix = new EAN13Writer().encode(str, BarcodeFormat.EAN_13, 150, 80);
			File f = new File(fileName);
			MatrixToImageWriter.writeToStream(bitMatrix, "jpg", new FileOutputStream(f));
			return f;
			
//			//  Write PDF417
//			writer = new PDF417Writer();
//			bitMatrix = writer.encode("123456789", BarcodeFormat.PDF_417, 80, 150);
//			MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File("pdf417_123456789.png")));
//			System.out.println("PDF417 Code Generated.");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		}
	
	public static File qrCode(String text) {
		return qrCode(text, text + "-qrcode.jpg");
	}
	
	public static File qrCode(String text, String fileName) {
		BitMatrix bitMatrix;
		Writer writer = new QRCodeWriter();
		try {
			bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 125, 125);
			File f = new File(fileName);
			MatrixToImageWriter.writeToStream(bitMatrix, "jpg", new FileOutputStream(f));
			return f;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static byte[] byteArrayBarcode(String text){
		return byteArrayBarcode(text, 100, 40);
	}
	
	public static byte[] byteArrayBarcode(String text, int height, int width){
		try {
			BitMatrix matrix = new Code128Writer().encode(text,	BarcodeFormat.CODE_128, height, width);
			BufferedImage originalImage = MatrixToImageWriter.toBufferedImage(matrix);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static byte[] byteArrayQrCode(String text){
		return byteArrayQrCode(text, 100, 100);
	}
	
	public static byte[] byteArrayQrCode(String text, int height, int width){
		try {
			BitMatrix matrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, height, width);
			BufferedImage originalImage = MatrixToImageWriter.toBufferedImage(matrix);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "jpg", baos);
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			return imageInByte;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
