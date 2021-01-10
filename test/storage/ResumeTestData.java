package storage;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    private Resume testResume;

    public ResumeTestData(String uuid, String name) {
        this.testResume = createResume(uuid, name);
    }

    public Resume getTestResume() {
        return testResume;
    }

    public static void main(String[] args) {
        Resume RESUME_3 = new ResumeTestData("uuid1", "Bob").getTestResume();
        System.out.println(RESUME_3.getSections(SectionType.EDUCATION));
    }

    static Resume createResume(String uuid, String name) {
        Resume resume = new Resume(uuid, name);

        resume.setContacts(ContactType.PHONE, "+7354857387");
        resume.setContacts(ContactType.SKYPE, "SkypeId");
        resume.setContacts(ContactType.EMAIL, "some@mail.com");
        resume.setContacts(ContactType.LINKEDIN, "LinkedInId");
        resume.setContacts(ContactType.GITHUB, "GitHubId");
        resume.setContacts(ContactType.STACKOVERFLOW, "StackOverflowId");
        resume.setContacts(ContactType.HOMEPAGE, "www.myHomepage.com");

        resume.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSections(SectionType.OBJECTIVE, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achievementsList = new ArrayList<>();
        achievementsList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementsList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementsList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        resume.setSections(SectionType.ACHIEVEMENT, new ListSection(achievementsList));

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
        resume.setSections(SectionType.QUALIFICATION, new ListSection(qualificationList));

        PeriodDescriptor periodDescriptor1 = new PeriodDescriptor(
                "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок.",
                LocalDate.of(2013, 10, 1),
                LocalDate.of(2021, 1, 1));
        List<PeriodDescriptor> periodDescriptorsList1 = new ArrayList<>();
        periodDescriptorsList1.add(periodDescriptor1);
        Organisation jobOrganisation1 = new Organisation("Java Online Projects", "https://javaops.ru/", periodDescriptorsList1);

        PeriodDescriptor periodDescriptor2 = new PeriodDescriptor(
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1));
        List<PeriodDescriptor> periodDescriptorsList2 = new ArrayList<>();
        periodDescriptorsList2.add(periodDescriptor2);
        Organisation jobOrganisation2 = new Organisation("Wrike", "https://www.wrike.com/", periodDescriptorsList2);

        List<Organisation> jobOrganisationsList = new ArrayList<>();
        jobOrganisationsList.add(jobOrganisation1);
        jobOrganisationsList.add(jobOrganisation2);

        OrganisationSection jobOrganisationSection = new OrganisationSection(jobOrganisationsList);

        PeriodDescriptor periodDescriptor3 = new PeriodDescriptor(
                "Аспирантура (программист С, С++)",
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1));
        PeriodDescriptor periodDescriptor3_1 = new PeriodDescriptor(
                "Инженер (программист Fortran, C)",
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1));

        List<PeriodDescriptor> periodDescriptorsList3 = new ArrayList<>();
        periodDescriptorsList3.add(periodDescriptor3);
        periodDescriptorsList3.add(periodDescriptor3_1);
        Organisation studyOrganisation1 = new Organisation("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/", periodDescriptorsList3);

        PeriodDescriptor periodDescriptor4 = new PeriodDescriptor(
                "Аспирантура (программист С, С++)",
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1));
        List<PeriodDescriptor> periodDescriptorsList4 = new ArrayList<>();
        periodDescriptorsList4.add(periodDescriptor4);
        Organisation studyOrganisation2 = new Organisation("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", periodDescriptorsList4);

        List<Organisation> studyOrganisationsList = new ArrayList<>();
        studyOrganisationsList.add(studyOrganisation1);
        studyOrganisationsList.add(studyOrganisation2);

        OrganisationSection studyOrganisationSection = new OrganisationSection(studyOrganisationsList);

        resume.setSections(SectionType.EXPERIENCE, jobOrganisationSection);
        resume.setSections(SectionType.EDUCATION, studyOrganisationSection);

        return resume;
    }
}
