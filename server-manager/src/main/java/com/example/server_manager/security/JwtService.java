package com.example.server_manager.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;  // Secret key'yi application.properties dosyasından alıyoruz.

    @Value("${jwt.expiration}")
    private long expirationTime; // Token'ın geçerlilik süresi (ms cinsinden)

    // Token üretme
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // Token'ın konu kısmı (subject) olarak kullanıcı adını belirliyoruz
                .setIssuedAt(new Date())  // Token'ın oluşturulma zamanı
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // Token'ın geçerlilik süresi
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Secret key ile imzalıyoruz
                .compact();  // Token'ı kompakt hale getiriyoruz
    }

    // Token doğrulama
    public boolean validateToken(String token) {
        try {
            // JWT token'ı doğrula ve expiration süresine bak
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());  // Token'ın geçerliliğini kontrol et
        } catch (JwtException | IllegalArgumentException e) {
            return false;  // Geçersiz token veya hata durumunda false döndür
        }
    }

    // Token'dan kullanıcı adı alma
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // Secret key ile doğrulama yapıyoruz
                .build()
                .parseClaimsJws(token)  // Token'ı parse ediyoruz
                .getBody()  // Claims (içerik) kısmını alıyoruz
                .getSubject();  // Kullanıcı adı (subject) kısmını çıkarıyoruz
    }

    // Token'ın süresi dolmuş mu kontrol etme
    public boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);  // Token'dan expiration tarihini çıkarıyoruz
        return expiration.before(new Date());  // Eğer expiration tarihi geçmişse token süresi dolmuş demektir
    }

    // Token'dan expiration tarihi alma
    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)  // Secret key ile doğrulama yapıyoruz
                .build()
                .parseClaimsJws(token)  // Token'ı parse ediyoruz
                .getBody()  // Claims (içerik) kısmını alıyoruz
                .getExpiration();  // Expiration tarihini çıkarıyoruz
    }
}
