package com.example.gitcat

import android.util.Base64
import java.nio.charset.Charset
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object ChCrypto {
    @Throws(java.io.UnsupportedEncodingException::class, NoSuchAlgorithmException::class, NoSuchPaddingException::class, InvalidKeyException::class, InvalidAlgorithmParameterException::class, IllegalBlockSizeException::class, BadPaddingException::class)
    fun AES_Decode(str: String): String {
        val ivBytes : ByteArray= "yejithewonderful".toByteArray()

        //val textBytes = Base64.getDecoder().decode(ivBytes)
        //val textBytes = Base64.getDecoder().decode(str.toByteArray(Charsets.UTF_8))
        val textBytes = android.util.Base64.decode(str,0)
        //byte[] textBytes = str.getBytes("UTF-8");
        val ivSpec = IvParameterSpec(ivBytes)
        val newKey = SecretKeySpec(("huandyoonandyoungandheeisthebest").toByteArray(charset("UTF-8")), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)
        return String(cipher.doFinal(textBytes), Charset.forName("UTF-8"))
        //return String(cipher.doFinal(Base64.decode(str,Base64.DEFAULT)),charset("UTF-8"))
    }
}
