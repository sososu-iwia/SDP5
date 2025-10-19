package model;

import java.util.Map;

public interface Certifiable {
    void awardCertificate(String subject);
    boolean hasCertificate(String subject);
    Map<String, Boolean> getCertificates();
    void displayCertificates();
}