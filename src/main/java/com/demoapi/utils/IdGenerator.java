package com.demoapi.utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

/* 
    Based on https://rajivrnair.dev/custom-hibernate-uuid
*/
public class IdGenerator extends UUIDGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        try {
            // System.out.println(createHash(super.generate(session, object).toString()));
            return createHash(super.generate(session, object).toString());
        } catch (NoSuchAlgorithmException e) {
            // System.out.println("Failed to create hash");
            e.printStackTrace();
            return null;
        }
    }
    
    /* 
        Based on https://www.baeldung.com/java-md5#md5-using-messagedigest-class
            and https://github.com/amatmiftakhudin/uuid-md5-library/
     */
    private String createHash(String text) throws NoSuchAlgorithmException {
    	   
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(text.getBytes());
        byte[] digest = md.digest();

        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
    
}