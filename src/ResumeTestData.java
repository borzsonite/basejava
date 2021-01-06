import model.*;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Bob");

        resume.setContacts(ContactType.PHONE, "+7354857387");
        resume.setContacts(ContactType.SKYPE, "SkypeId");
        resume.setContacts(ContactType.EMAIL, "some@mail.com");
        resume.setContacts(ContactType.LINKEDIN, "LinkedInId");
        resume.setContacts(ContactType.GITHUB, "GitHubId");
        resume.setContacts(ContactType.STACKOVERFLOW, "StackOverflowId");
        resume.setContacts(ContactType.HOMEPAGE, "www.myHomepage.com");
    }
}
