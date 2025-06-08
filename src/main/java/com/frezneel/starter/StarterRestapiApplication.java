package com.frezneel.starter;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.security.Key;

@SpringBootApplication
public class StarterRestapiApplication {

	public static void main(String[] args) {
		// Kunci untuk HS512 (512 bit) jika ingin lebih aman (ubah SignatureAlgorithm di JwtService)
//		Key key512 = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
//		String base64Key512 = Encoders.BASE64.encode(key512.getEncoded());
//		System.out.println("HS512 Secret Key (Base64): " + base64Key512);

		SpringApplication.run(StarterRestapiApplication.class, args);
	}
}
