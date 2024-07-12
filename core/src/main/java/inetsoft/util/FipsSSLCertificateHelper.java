package inetsoft.util;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.*;
import org.bouncycastle.openssl.*;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.jcajce.JcePKCSPBEInputDecryptorProviderBuilder;

import java.io.OutputStream;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.*;

public class FipsSSLCertificateHelper implements SSLCertificateHelper {
   @Override
   public PrivateKey loadPrivateKey(Reader in, char[] password) throws Exception {
      try(PEMParser parser = new PEMParser(in)) {
         PrivateKeyInfo pki;
         Object obj = parser.readObject();

         if(obj instanceof PKCS8EncryptedPrivateKeyInfo epki) {
            JcePKCSPBEInputDecryptorProviderBuilder builder =
               new JcePKCSPBEInputDecryptorProviderBuilder().setProvider("BCFIPS");
            InputDecryptorProvider idp = builder.build(password);
            pki = epki.decryptPrivateKeyInfo(idp);
         }
         else if(obj instanceof PEMEncryptedKeyPair pekp) {
            JcePEMDecryptorProviderBuilder builder =
               new JcePEMDecryptorProviderBuilder().setProvider("BCFIPS");
            PEMDecryptorProvider pdp = builder.build(password);
            PEMKeyPair pkp = pekp.decryptKeyPair(pdp);
            pki = pkp.getPrivateKeyInfo();
         }
         else {
            throw new Exception("Invalid encrypted private key class: " + obj.getClass().getName());
         }

         return new JcaPEMKeyConverter().getPrivateKey(pki);
      }
   }

   @Override
   public X509Certificate loadCertificate(Reader in) throws Exception {
      try(PEMParser parser = new PEMParser(in)) {
         Object obj = parser.readObject();

         if(obj instanceof X509CertificateHolder holder) {
            return new JcaX509CertificateConverter().setProvider("BCFIPS").getCertificate(holder);
         }
         else {
            throw new Exception("Invalid certificate class: " + obj.getClass().getName());
         }
      }
   }

   @Override
   public CertificateAndKey generateCertificate(X509Certificate issuerCertificate,
                                                PrivateKey issuerKey, String subjectDN,
                                                char[] password) throws Exception
   {
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BCFIPS");
      keyGen.initialize(2048);
      KeyPair certPair = keyGen.generateKeyPair();

      JcaX509CertificateHolder issuerHolder = new JcaX509CertificateHolder(issuerCertificate);
      X500Name issuerName = issuerHolder.getSubject();
      X500Name certName = new X500Name(getChildDN(issuerCertificate, Tool.getIP()));
      BigInteger serialNumber = BigInteger.valueOf(System.currentTimeMillis());
      Date validFrom = new Date(System.currentTimeMillis());
      Date validUntil = issuerHolder.getNotAfter();

      JcaX509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
         issuerName, serialNumber, validFrom, validUntil, certName, certPair.getPublic());

      ContentSigner signer = new JcaContentSignerBuilder("SHA256WithRSA")
         .setProvider("BCFIPS")
         .build(issuerKey);
      X509CertificateHolder certHolder = certBuilder.build(signer);
      X509Certificate certificate = new JcaX509CertificateConverter()
         .setProvider("BCFIPS")
         .getCertificate(certHolder);
      PrivateKey privateKey = certPair.getPrivate();

      return new CertificateAndKey(certificate, privateKey);
   }

   @Override
   public String getChildDN(X509Certificate issuerCertificate, String cn) throws Exception {
      JcaX509CertificateHolder issuerHolder = new JcaX509CertificateHolder(issuerCertificate);
      X500Name issuerName = issuerHolder.getSubject();
      List<RDN> path = new ArrayList<>();

      for(RDN rdn : issuerName.getRDNs()) {
         if(rdn.getFirst().getType().equals(BCStyle.CN)) {
            break;
         }

         path.add(rdn);
      }

      path.add(new RDN(BCStyle.CN, BCStyle.INSTANCE.stringToValue(BCStyle.CN, cn)));
      return new X500Name(path.toArray(new RDN[0])).toString();
   }

   @Override
   public void saveKeyStore(String filePath, X509Certificate certificate, PrivateKey privateKey,
                            X509Certificate issuerCertificate, char[] password) throws Exception
   {
      try(OutputStream out = Files.newOutputStream(Paths.get(filePath))) {
         KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
         String alias = getAlias(certificate);
         ks.setCertificateEntry(alias, certificate);
         ks.setKeyEntry(
            alias, privateKey, password, new Certificate[] { certificate, issuerCertificate });
         ks.store(out, password);
      }
   }

   @Override
   public void saveKeyStore(String filePath, X509Certificate certificate, char[] password)
      throws Exception
   {
      try(OutputStream out = Files.newOutputStream(Paths.get(filePath))) {
         KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
         String alias = getAlias(certificate);
         ks.setCertificateEntry(alias, certificate);
         ks.store(out, password);
      }
   }

   private String getAlias(X509Certificate certificate) throws Exception {
      X500Name name = new JcaX509CertificateHolder(certificate).getSubject();
      RDN cn = name.getRDNs(BCStyle.CN)[0];
      return IETFUtils.valueToString(cn.getFirst().getValue());
   }
}