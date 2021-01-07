package storage;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    private Resume testResume;

    public ResumeTestData(String uuid, String name) {
        this.testResume = createResume(uuid, name);
    }

    public Resume getTestResume() {
        return testResume;
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

        Organisation jobOrganisation1 = new Organisation( // организация которая хранится в в списке мапы OrganisationSection
                "Java Online Projects", "https://javaops.ru/",
                LocalDate.of(2013, 10, 1),
                LocalDate.of(2021, 1, 1),
                "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        List<Organisation> jobOrganisationsList1 = new ArrayList<>(); // список с организациями для мапы OrganisationSection
        jobOrganisationsList1.add(jobOrganisation1); // добавляем организацию в список

        Organisation jobOrganisation2 = new Organisation( // организация которая хранится в в списке мапы OrganisationSection
                "Wrike", "https://www.wrike.com/",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        List<Organisation> jobOrganisationsList2 = new ArrayList<>();
        jobOrganisationsList2.add(jobOrganisation2);

        Map<String, List<Organisation>> jobOrganisationsMap = new HashMap<>();
        jobOrganisationsMap.put(jobOrganisation1.getName(), jobOrganisationsList1);
        jobOrganisationsMap.put(jobOrganisation2.getName(), jobOrganisationsList2);

        OrganisationSection jobSection = new OrganisationSection(jobOrganisationsMap);
        resume.setSections(SectionType.EXPERIENCE, jobSection);

        Organisation studyOrganisation1 = new Organisation(
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "http://www.ifmo.ru/",
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Аспирантура (программист С, С++)", "");

        Organisation studyOrganisation1_1 = new Organisation(
                studyOrganisation1.getName(),
                studyOrganisation1.getUrl(),
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 10, 1),
                "Инженер (программист Fortran, C)", "");

        Organisation studyOrganisation2 = new Organisation(
                "Заочная физико-техническая школа при МФТИ",
                "http://www.school.mipt.ru/",
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1),
                "Закончил с отличием", "");

        List<Organisation> studyOrganisationsList1 = new ArrayList<>();
        studyOrganisationsList1.add(studyOrganisation1);
        studyOrganisationsList1.add(studyOrganisation1_1);

        List<Organisation> studyOrganisationsList2 = new ArrayList<>();
        studyOrganisationsList1.add(studyOrganisation2);

        Map<String, List<Organisation>> studyOrganisationsMap = new HashMap<>();
        studyOrganisationsMap.put(studyOrganisation1.getName(), studyOrganisationsList1);
        studyOrganisationsMap.put(studyOrganisation2.getName(), studyOrganisationsList2);

        OrganisationSection studySection = new OrganisationSection(studyOrganisationsMap);
        resume.setSections(SectionType.EDUCATION, studySection);

        return resume;
        }
}
