import model.Contacts;
import model.Resume;
import model.Sections;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume testResume = new Resume("uuid1", "Bob");

        Contacts contacts = new Contacts();
        Sections sections = new Sections();

        List<String> personal = new ArrayList<>();
        List<String> objectives = new ArrayList<>();
        List<String> achievements = new ArrayList<>();
        List<String> qualification = new ArrayList<>();
        List<String[]> experience = new ArrayList<>();
        List<String[]> education = new ArrayList<>();

        personal.add("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        objectives.add("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");

        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");

        experience.add(new String[]{"Java Online Projects", "10/2013 - Сейчас", "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."});
        experience.add(new String[]{"Wrike", "10/2014 - 01/2016", "04/2012 - 10/2014", "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"});
        experience.add(new String[]{"Luxoft (Deutsche Bank)", "12/2010 - 04/2012", "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5."});

        education.add(new String[]{"Coursera", "03/2013 - 05/2013", "Functional Programming Principles in Scala\" by Martin Odersky"});
        education.add(new String[]{"Luxoft", "03/2011 - 04/2011", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""});
        education.add(new String[]{"Siemens AG", "01/2005 - 04/2005", "3 месяца обучения мобильным IN сетям (Берлин)"});

        sections.setPersonal(personal);
        sections.setObjectives(objectives);
        sections.setAchievement(achievements);
        sections.setQualification(qualification);
        sections.setExperience(experience);
        sections.setEducation(education);

        contacts.setPhone("233-233-111");
        contacts.setSkype("SkypeID");
        contacts.setEmail("user@mail.com");
        contacts.setLinkedIn("LinkedInID");
        contacts.setGitHub("GitHubID");
        contacts.setStackOverflow("OverflowID");
        contacts.setHomepage("www.homepage.com");


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
        System.out.println("==========Personal section========");
        System.out.println(testResume.getSections().getPersonal());
        System.out.println("==========Objectives section========");
        System.out.println(testResume.getSections().getObjectives());
        System.out.println("==========Achievement section========");
        System.out.println(testResume.getSections().getAchievement());
        System.out.println("==========Qualification section========");
        System.out.println(testResume.getSections().getQualification());
        System.out.println("==========Experience section========");
        for (String[] array: testResume.getSections().getExperience()) {
            for (String elem: array) {
                System.out.println(elem);
            }
        }
        System.out.println("==========Education section========");
        for (String[] array: testResume.getSections().getEducation()) {
            for (String elem: array) {
                System.out.println(elem);
            }
        }
    }
}
