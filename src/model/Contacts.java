package model;

import java.util.Map;
import java.util.TreeMap;

public class Contacts {
    private Map<ContactType, String> contactsStorage = new TreeMap<>();

    public String getPhone() {
        return contactsStorage.get(ContactType.PHONE);
    }

    public void setPhone(String phone) {
        contactsStorage.put(ContactType.PHONE, phone);
    }

    public String getSkype() {
        return contactsStorage.get(ContactType.SKYPE);
    }

    public void setSkype(String skype) {
        contactsStorage.put(ContactType.SKYPE, skype);
    }

    public String getEmail() {
        return contactsStorage.get(ContactType.EMAIL);
    }

    public void setEmail(String email) {
        contactsStorage.put(ContactType.EMAIL, email);
    }

    public String getLinkedIn() {
        return contactsStorage.get(ContactType.LINKEDIN);
    }

    public void setLinkedIn(String linkedIn) {
        contactsStorage.put(ContactType.LINKEDIN, linkedIn);
    }

    public String getGitHub() {
        return contactsStorage.get(ContactType.GITHUB);
    }

    public void setGitHub(String gitHub) {
        contactsStorage.put(ContactType.GITHUB, gitHub);
    }

    public String getStackOverflow() {
        return contactsStorage.get(ContactType.STACKOVERFLOW);
    }

    public void setStackOverflow(String stackOverflow) {
        contactsStorage.put(ContactType.STACKOVERFLOW, stackOverflow);
    }

    public String getHomepage() {
        return contactsStorage.get(ContactType.HOMEPAGE);
    }

    public void setHomepage(String homepage) {
        contactsStorage.put(ContactType.HOMEPAGE, homepage);
    }
}
