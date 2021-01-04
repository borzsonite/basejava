import model.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Bob");

        resume.setContacts(
                "+7354857387",
                "SkypeId",
                "some@mail.com",
                "LinkedInId",
                "GitHubId",
                "StackOverflowId",
                "www.myHomepage.com");

        SingleLineSection objectiveSingleLineSection = new SingleLineSection();
        objectiveSingleLineSection.setContent("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.setSections(SectionType.OBJECTIVE, objectiveSingleLineSection);

        SingleLineSection personalSingleLineSection = new SingleLineSection();
        personalSingleLineSection.setContent("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.setSections(SectionType.PERSONAL, personalSingleLineSection);

        BulletedListSection achievementsBulletedListSection = new BulletedListSection();
        List<String> achievementsArrayList = new ArrayList<>();
        achievementsArrayList.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievementsArrayList.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievementsArrayList.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievementsBulletedListSection.setContent(achievementsArrayList);
        resume.setSections(SectionType.ACHIEVEMENT, achievementsBulletedListSection);

        BulletedListSection qualificationBulletedSection = new BulletedListSection();
        List<String> qualificationArrayList = new ArrayList<>();
        qualificationArrayList.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualificationArrayList.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualificationArrayList.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        qualificationArrayList.add("MySQL, SQLite, MS SQL, HSQLDB");
        qualificationBulletedSection.setContent(qualificationArrayList);
        resume.setSections(SectionType.QUALIFICATION, qualificationBulletedSection);

        Experience jobExperience0 = new Experience();
        jobExperience0.setPlace("Java Online Projects");
        jobExperience0.setDateFrom(2013, 10, 1);
        jobExperience0.setDateTo(2021, 1, 1);
        jobExperience0.setPosition("Автор проекта.");
        jobExperience0.setContent("Создание, организация и проведение Java онлайн проектов и стажировок.");

        Experience jobExperience1 = new Experience();
        jobExperience1.setPlace("Wrike");
        jobExperience1.setDateFrom(2014, 10, 1);
        jobExperience1.setDateTo(2016, 1, 1);
        jobExperience1.setPosition("Старший разработчик (backend)");
        jobExperience1.setContent("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");

        Experience jobExperience2 = new Experience();
        jobExperience2.setPlace("RIT Center");
        jobExperience2.setDateFrom(2012, 4, 1);
        jobExperience2.setDateTo(2014, 12, 1);
        jobExperience2.setPosition("Java архитектор");
        jobExperience2.setContent("Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");

        Organisations jobOrganisations = new Organisations();
        List<Experience> jobExperienceList = new ArrayList<>();
        jobExperienceList.add(jobExperience0);
        jobExperienceList.add(jobExperience1);
        jobExperienceList.add(jobExperience2);
        jobOrganisations.setContent(jobExperienceList);
        resume.setSections(SectionType.EXPERIENCE, jobOrganisations);

        Experience studyExperience0 = new Experience();
        studyExperience0.setPlace("Coursera");
        studyExperience0.setDateFrom(2013, 3,1);
        studyExperience0.setDateTo(2013, 5,1);
        studyExperience0.setContent("\"Functional Programming Principles in Scala\" by Martin Odersky");

        Experience studyExperience1 = new Experience();
        studyExperience1.setPlace("Luxoft");
        studyExperience1.setDateFrom(2011, 3,1);
        studyExperience1.setDateTo(2011, 4,1);
        studyExperience1.setContent("\tКурс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");

        Experience studyExperience2 = new Experience();
        studyExperience2.setPlace("Siemens AG");
        studyExperience2.setDateFrom(2005, 1,1);
        studyExperience2.setDateTo(2005, 4,1);
        studyExperience2.setContent("3 месяца обучения мобильным IN сетям (Берлин)");

        Organisations studyOrganisations = new Organisations();
        List<Experience> studyExperienceList = new ArrayList<>();
        studyExperienceList.add(studyExperience0);
        studyExperienceList.add(studyExperience1);
        studyExperienceList.add(studyExperience2);
        studyOrganisations.setContent(studyExperienceList);
        resume.setSections(SectionType.EDUCATION, studyOrganisations);

        System.out.println("============Contacts output============");
        System.out.println(resume.getContacts());
        System.out.println("============Sections output============");
        for(Map.Entry<SectionType,AbstractSection> entry: resume.getSections().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("============JobExperience output============");
        List<Experience> experienceList = resume.getJobExperienceSection();
        for(Experience elem: experienceList) {
            System.out.println(elem.toString());
        }
    }
}
