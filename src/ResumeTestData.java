import model.Contacts;
import model.Resume;
import model.Sections;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume testResume = new Resume("uuid1", "Bob");

        Contacts contacts = new Contacts();
        Sections sections = new Sections();

        contacts.setPhone("233-233-111");
        contacts.setSkype("SkypeID");
        contacts.setEmail("user@mail.com");
        contacts.setLinkedIn("LinkedInID");
        contacts.setGitHub("GitHubID");
        contacts.setStackOverflow("OverflowID");
        contacts.setHomepage("www.homepage.com");

        sections.setPersonal("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        sections.setObjectives("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        sections.setAchievement("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven.");
        sections.setQualification("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        sections.setExperience("10/2013 - Сейчас\tАвтор проекта.\n" + "Создание, организация и проведение Java онлайн проектов и стажировок.");
        sections.setEducation("Coursera\n" + "03/2013 - 05/2013\t\"Functional Programming Principles in Scala\" by Martin Odersky");

        testResume.setContactsAndSections(contacts, sections);

        System.out.println("//////////Contacts output/////////");
        System.out.println(testResume.getContacts().getPhone());
        System.out.println(testResume.getContacts().getEmail());
        System.out.println(testResume.getContacts().getSkype());
        System.out.println(testResume.getContacts().getGitHub());
        System.out.println(testResume.getContacts().getLinkedIn());
        System.out.println(testResume.getContacts().getStackOverflow());
        System.out.println(testResume.getContacts().getHomepage());

        System.out.println("//////////Sections output/////////");
        System.out.println(testResume.getSections().getPersonal());
        System.out.println(testResume.getSections().getObjectives());
        System.out.println(testResume.getSections().getAchievement());
        System.out.println(testResume.getSections().getQualification());
        System.out.println(testResume.getSections().getExperience());
        System.out.println(testResume.getSections().getEducation());
    }
}
