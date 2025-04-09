package com.example.onlinelearning.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String SECRET_KEY = "le_cao_hoang";

    private static final Set<String> BLACK_LIST = new HashSet<>();

    public void addBlackList(String token){
        BLACK_LIST.add(token);
    }
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>() , userDetails);
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*6024))
                .signWith(SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()// để giải mã và xác thực token, sẽ kiểm tra ta dùng thuật toán gì để mã hóa token
                .setSigningKey(getSignInKey())// Đặt khóa bí mật (secret key) để xác thực chữ ký của JWT
                .parseClaimsJws(token) //Giải mã và phân tích JWT (token)
                .getBody(); //Lấy phần payload (body) của JWT
    }

    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
        byte[] keyBytes = Base64.getUrlDecoder().decode(SECRET_KEY);

        // Tạo SecretKey từ mảng byte đã giải mã, sử dụng thuật toán HMACSHA256
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && !BLACK_LIST.contains(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
