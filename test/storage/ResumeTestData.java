package storage;

import model.*;

import java.time.Month;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume testResume = ResumeTestData.createResume("uuid1", "Jhon");
        System.out.println(ContactType.PHONE.getTitle() + testResume.getContacts(ContactType.PHONE));
        System.out.println(ContactType.SKYPE.getTitle() + testResume.getContacts(ContactType.SKYPE));
        System.out.println(ContactType.EMAIL.getTitle() + testResume.getContacts(ContactType.EMAIL));
        System.out.println(ContactType.GITHUB.getTitle() + testResume.getContacts(ContactType.GITHUB));
        System.out.println(ContactType.LINKEDIN.getTitle() + testResume.getContacts(ContactType.LINKEDIN));
        System.out.println(ContactType.STACKOVERFLOW.getTitle() + testResume.getContacts(ContactType.STACKOVERFLOW));
        System.out.println(ContactType.HOMEPAGE + testResume.getContacts(ContactType.HOMEPAGE));
        System.out.println(SectionType.OBJECTIVE.getTitle() + testResume.getSections(SectionType.OBJECTIVE));
        System.out.println(SectionType.PERSONAL.getTitle() + testResume.getSections(SectionType.PERSONAL));
        System.out.println(SectionType.ACHIEVEMENT.getTitle() + testResume.getSections(SectionType.ACHIEVEMENT));
        System.out.println(SectionType.QUALIFICATION.getTitle() + testResume.getSections(SectionType.QUALIFICATION));

        System.out.println(SectionType.EXPERIENCE.getTitle());
        OrganisationSection organisations = (OrganisationSection) testResume.getSections(SectionType.EXPERIENCE);
        for (Organisation organisation : organisations.getOrganisations()) {
            System.out.println(organisation.toString());
        }

        System.out.println(SectionType.EDUCATION.getTitle());
        OrganisationSection organisations1 = (OrganisationSection) testResume.getSections(SectionType.EDUCATION);
        for (Organisation organisation : organisations1.getOrganisations()) {
            System.out.println(organisation.toString());
        }
    }

    protected static Resume createResume(String uuid, String name) {
        Resume resume = new Resume(uuid, name);
        resume.setContacts(ContactType.PHONE, "+7354857387");
        resume.setContacts(ContactType.SKYPE, "SkypeId");
        resume.setContacts(ContactType.EMAIL, "some@mail.com");
        resume.setContacts(ContactType.LINKEDIN, "LinkedInId");
        resume.setContacts(ContactType.GITHUB, "GitHubId");
        resume.setContacts(ContactType.STACKOVERFLOW, "StackOverflowId");
        resume.setContacts(ContactType.HOMEPAGE, "www.myHomepage.com");

        resume.setSections(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSections(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.setSections(SectionType.ACHIEVEMENT, new ListSection(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n"
        ));

        resume.setSections(SectionType.QUALIFICATION, new ListSection(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce\n",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle\n"
        ));


        resume.setSections(SectionType.EXPERIENCE,
                new OrganisationSection(
                        new Organisation("Java Online Projects", "https://javaops.ru/",
                                new Organisation.Position(2013, Month.OCTOBER, "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.")), new Organisation("Wrike", "https://www.wrike.com/",
                        new Organisation.Position(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));

        resume.setSections(SectionType.EDUCATION,
                new OrganisationSection(
                        new Organisation("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                                new Organisation.Position(1993, Month.SEPTEMBER, 1996, Month.OCTOBER, "Аспирантура (программист С, С++)", null),
                                new Organisation.Position(1987, Month.SEPTEMBER, 1993, Month.OCTOBER, "Инженер (программист Fortran, C))", null)),
                        new Organisation("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                                new Organisation.Position(1984, Month.OCTOBER, 1993, Month.APRIL, "Закончил с отличием", null))));
        return resume;
    }
}
