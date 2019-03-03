package com.alewo;

import com.alewo.aes.AESDecrypt;
import com.alewo.aes.AESEncrypt;
import com.alewo.sha.SHAEncrypt;
import org.junit.Assert;
import org.junit.Test;

public class DataEncryptionTest {

    private final String username = "pah1230";
    private final String password = "pahpah";

    private final String pw_hash = "696fc516953fdd5918f07a41ba8899d74e628f5f13ce7c7c1cce1ebdbf5716e7";

    private final String username_encrypted = "UkMSiJRLZ8Ruyvw1tcF6rw==";

    @Test
    public void testSHAEncrypt() {
        Assert.assertEquals(pw_hash, SHAEncrypt.encodeString(password + SHAEncrypt.encodeString(username)));
    }

    @Test
    public void testAESEncrypt() {
        Assert.assertEquals(username_encrypted, AESEncrypt.encrypt(username, pw_hash));
    }

    @Test
    public void testAESDecrypt() {
        Assert.assertEquals(username, AESDecrypt.decrypt(username_encrypted, pw_hash));
    }
}
