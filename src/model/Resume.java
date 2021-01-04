package model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    private String uuid;
    private String fullName;
    private Map<ContactType, String> contacts = new TreeMap<>();
    private Map<SectionType, AbstractSection> sections = new TreeMap<>();



    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid can't be null");
        Objects.requireNonNull(fullName, "fullName can't be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public List<Experience> getJobExperienceSection() {
      return (List<Experience>) sections.get(SectionType.EXPERIENCE);
    }

    public void setContacts(String phone, String skype, String mail, String linkedin, String github, String stackoverflow, String homepage) {
        contacts.put(ContactType.PHONE, phone);
        contacts.put(ContactType.SKYPE, skype);
        contacts.put(ContactType.EMAIL, mail);
        contacts.put(ContactType.LINKEDIN, linkedin);
        contacts.put(ContactType.GITHUB, github);
        contacts.put(ContactType.STACKOVERFLOW, stackoverflow);
        contacts.put(ContactType.HOMEPAGE, homepage);
   }

    public <T>void setSections(SectionType sectionType, AbstractSection<T> content) {
        sections.put(sectionType, content);
    }


    @Override
    public String toString() {
        return uuid + ":" + fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public int compareTo(Resume o) {
        return uuid.compareTo(o.uuid);
    }
}
