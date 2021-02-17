package storage;

import model.*;

import java.time.Month;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume testResume = ResumeTestData.createResume("uuid1", "Jhon");
        System.out.println("Contacts" + testResume.getContacts());
        System.out.println(SectionType.OBJECTIVE.getTitle() + testResume.getSection(SectionType.OBJECTIVE));
        System.out.println(SectionType.PERSONAL.getTitle() + testResume.getSection(SectionType.PERSONAL));
        System.out.println(SectionType.ACHIEVEMENT.getTitle() + testResume.getSection(SectionType.ACHIEVEMENT));
        System.out.println(SectionType.QUALIFICATION.getTitle() + testResume.getSection(SectionType.QUALIFICATION));

        System.out.println(SectionType.EXPERIENCE.getTitle());
        OrganizationSection organisations = (OrganizationSection) testResume.getSection(SectionType.EXPERIENCE);
        for (Organization organization : organisations.getOrganisations()) {
            System.out.println(organization.toString());
        }

        System.out.println(SectionType.EDUCATION.getTitle());
        OrganizationSection organisations1 = (OrganizationSection) testResume.getSection(SectionType.EDUCATION);
        for (Organization organization : organisations1.getOrganisations()) {
            System.out.println(organization.toString());
        }
    }

    protected static Resume createResume(String uuid, String name) {
        Resume resume = new Resume(uuid, name);
        resume.addContact(ContactType.PHONE, "+7354857387");
        resume.addContact(ContactType.SKYPE, "SkypeId");
        resume.addContact(ContactType.EMAIL, "some@mail.com");
        resume.addContact(ContactType.LINKEDIN, "LinkedInId");
        resume.addContact(ContactType.GITHUB, "GitHubId");
        resume.addContact(ContactType.STACKOVERFLOW, "StackOverflowId");
        resume.addContact(ContactType.HOMEPAGE, "www.myHomepage.com");

        resume.setSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.setSection(SectionType.ACHIEVEMENT, new ListSection(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n"
        ));


        resume.setSection(SectionType.QUALIFICATION, new ListSection(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce\n",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle\n"
        ));


        resume.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects", "https://javaops.ru/",
                            new Organization.Position(2013, Month.OCTOBER, 2021, Month.JANUARY,"Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                        new Organization("Wrike", "https://www.wrike.com/",
                            new Organization.Position(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));

/*        resume.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
                                new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.OCTOBER, "Аспирантура (программист С, С++)", null),
                                new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.OCTOBER, "Инженер (программист Fortran, C))", null)),
                        new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                                new Organization.Position(1984, Month.OCTOBER, 1993, Month.APRIL, "Закончил с отличием", null))));
*/
        return resume;
    }
}
