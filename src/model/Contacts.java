package model;

import java.util.Map;
import java.util.TreeMap;

public class Contacts {
    private Map<ContactType, String> contactsStorage = new TreeMap<>();

    public void setPhone(String phone) {
        contactsStorage.put(ContactType.PHONE, phone);
    }

    public void setSkype(String skype) {
        contactsStorage.put(ContactType.SKYPE, skype);
    }

    public void setEmail(String email) {
        contactsStorage.put(ContactType.EMAIL, email);
    }

    public void setLinkedIn(String linkedIn) {
        contactsStorage.put(ContactType.LINKEDIN, linkedIn);
    }

    public void setGitHub(String gitHub) {
        contactsStorage.put(ContactType.GITHUB, gitHub);
    }

    public void setStackOverflow(String stackOverflow) {
        contactsStorage.put(ContactType.STACKOVERFLOW, stackOverflow);
    }

    public void setHomepage(String homepage) {
        contactsStorage.put(ContactType.HOMEPAGE, homepage);
    }

    public String getPhone() {
       return contactsStorage.get(ContactType.PHONE);
    }

    public String getSkype() {
        return contactsStorage.get(ContactType.SKYPE);
    }

    public String getEmail() {
        return contactsStorage.get(ContactType.EMAIL);
    }

    public String getLinkedIn() {
        return contactsStorage.get(ContactType.LINKEDIN);
    }

    public String getGitHub() {
        return contactsStorage.get(ContactType.GITHUB);
    }

    public String getStackOverflow() {
        return contactsStorage.get(ContactType.STACKOVERFLOW);
    }

    public String getHomepage() {
        return contactsStorage.get(ContactType.HOMEPAGE);
    }
}
