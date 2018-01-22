package com.ns.greg.library.android_utils;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * @author Gregory
 * @since 2017/6/15
 */

public class CertificateUtils {

  /**
   * Load CAs from an InputStream
   * Reference from {@see https://developer.android.com/training/articles/security-ssl.html#UnknownCa}
   */
  public static SSLSocketFactory addCertificates(Context context, String protocol, int... certificates) {
    try {
      // Load certificate resource
      List<InputStream> inputStreams = new ArrayList<>();
      for (int certificate : certificates) {
        inputStreams.add(context.getResources().openRawResource(certificate));
      }

      CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      keyStore.load(null);
      int index = 0;
      for (InputStream inputStream : inputStreams) {
        String certificateAlias = Integer.toString(index++);
        keyStore.setCertificateEntry(certificateAlias,
            certificateFactory.generateCertificate(inputStream));
        try {
          if (inputStream != null) inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      // Create a TrustManager that trusts the CAs in our KeyStore
      String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
      TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
      tmf.init(keyStore);
      // Create an SSLContext that uses our TrustManager
      SSLContext sslContext = SSLContext.getInstance(protocol);
      sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
      return sslContext.getSocketFactory();
    } catch (CertificateException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (KeyStoreException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (KeyManagementException e) {
      e.printStackTrace();
    }

    return null;
  }
}
