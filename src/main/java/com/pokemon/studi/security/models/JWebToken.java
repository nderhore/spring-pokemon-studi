package com.pokemon.studi.security.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.UUID;

public class JWebToken {

    /**
     * Défini les attributs necessaire.
     * SECRET_KEY = secret servant à la signature du token
     * HEX_ARRAY = caractère disponible en hexadecimal
     * ISSUER = nom de l'application pour lequel sert le token
     * JWT_HEADER = contient le header du token, notamment le type de token et l'algorithme de chiffrement
     * signature = chaine contenant la signature du token.
     * encodedHeader = chaine contenant le header encodé
     */
    private final String SECRET_KEY = "$2y$10$awK2GOK7/pAwQrmho.hL7u0Q.vGeaA7.4BVdUBj/ssZKv7.x1XHAC";

    private final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private final String ISSUER = "pokedex App";

    private final String JWT_HEADER = "{\"alg\":\"HS256\",\"type\":\"JWT\"}";

    private String signature;

    private String encodedHeader;

    private JSONObject payload = new JSONObject();

    private JWebToken(){
        encodedHeader = encode(new JSONObject(JWT_HEADER));
    }

    /**
     * permet l'instanciation d'un JWebToken avec le payload (ce que dois contenir un JWT Token)
     * @param payload
     */
    public JWebToken(JSONObject payload){
        this(payload.getString("sub"),payload.getJSONArray("aud"),
                payload.getLong("exp"));
    }


    /**
     * Verifie que le token généré est correct
     *
     * @param token
     * @throws NoSuchAlgorithmException
     */
    public JWebToken(@NotNull String token){ // token = '123' ["header","payload","123"]
        this();
        String[] part = token.split("\\.");
        try{
            if(part.length != 3 || !encodedHeader.equals(part[0])){
                throw new IllegalArgumentException("Invalid Token format");
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        payload = new JSONObject(decode(part[1]));
        if(payload.isEmpty()){
            throw new JSONException("Payload is empty");
        }
        if(!payload.has("exp")){
            throw new JSONException("Payload doesn't contains expiry date");
        }

        signature = part[2];

    }

    /**
     *
     * @param sub , utilisateur demandant le token
     * @param aud , jsonArray contenant les roles de l'utilisateur
     * @param exp date à laquelle le token expire
     */
    private JWebToken(String sub, JSONArray aud, long exp) {
        this();
        payload.put("iss",ISSUER); //application ayant besoin du JWT
        payload.put("sub",sub);
        payload.put("aud",aud);
        payload.put("exp",exp);

        // date à laquelle le token est généré
        payload.put("iat", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

        //Unique identifier
        payload.put("jti", UUID.randomUUID().toString());

        //signature du token
        signature = hmacSha256(encodedHeader + "." + encode(payload),
                Base64.getEncoder().encodeToString(SECRET_KEY.getBytes(StandardCharsets.UTF_8)));

    }

    private String encode(JSONObject object){
        return encode(object.toString().getBytes(StandardCharsets.UTF_8));
    }

    private String encode(byte[] bytes){
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(bytes);
    }

    private String decode(String encodedString){
        return new String(Base64.getDecoder().decode(encodedString));
    }

    public String getUsername() {
        return this.payload.get("sub").toString();
    }

    /**
     * Signe le token avec l'algorithme de chiffrement HS256
     *
     * @param data
     * @return
     * @throws Exception
     */
    private String hmacSha256(String data, String secret)  {
        try {
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
            Mac hMac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash,hMac.getAlgorithm());
            hMac.init(secretKey);

            byte[] signedBytes = hMac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     *  verifie que le token est valide, renvoi true, sinon, false.
     * @return true or false
     */
    public boolean isValid() {
        return payload.getLong("exp") > (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
                && signature.equals(hmacSha256(encodedHeader + "." + encode(payload),
                SECRET_KEY));
    }
}
