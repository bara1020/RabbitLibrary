package co.com.cadena.rabbitlibrary;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class main {

	public static void main(String[] args) throws IOException {
		Rabbit rabbit = new Rabbit("mdeapp30", 5672, "ccm.appi.usr", "Ccm.4pp1.U5r","com.vh");
		
		rabbit.publicMessage("com.parser.topic","com.parser","{\"test\":\"pruebas ocar\"}","application\\json");

	}

}
