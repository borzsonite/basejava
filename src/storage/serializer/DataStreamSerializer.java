package storage.serializer;


import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static model.SectionType.*;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections

            // TestSection write
            dos.writeUTF(((TextSection) r.getSection(PERSONAL)).getContent());
            dos.writeUTF(((TextSection) r.getSection(OBJECTIVE)).getContent());

            // AchievementsSection write
            ListSection achievements = (ListSection) r.getSection(ACHIEVEMENT);
            dos.writeInt(achievements.getItems().size());
            for (String elem : achievements.getItems()) {
                dos.writeUTF(elem);
            }

            // QualificationSection write
            ListSection qualification = (ListSection) r.getSection(QUALIFICATION);
            dos.writeInt(qualification.getItems().size());
            for (String elem : qualification.getItems()) {
                dos.writeUTF(elem);
            }

            // ExperienceSection write
            OrganizationSection experience = (OrganizationSection) r.getSection(EXPERIENCE);
            dos.writeInt(experience.getOrganisations().size()); // orgs number 1
            for (Organization organization : experience.getOrganisations()) { // итерируем по List<Organizations>
                dos.writeUTF(organization.getLink().getName()); // 2
                dos.writeUTF(organization.getLink().getUrl()); //
                dos.writeInt(organization.getPosition().size()); // pos number // 4

                for (Organization.Position position : organization.getPosition()) {
                    dos.writeInt(position.getStartDate().getYear()); // 5
                    dos.writeInt(position.getStartDate().getMonth().getValue()); // 6
                    dos.writeInt(position.getEndDate().getYear()); // 7
                    dos.writeInt(position.getEndDate().getMonth().getValue()); // 8
                    dos.writeUTF(position.getTitle()); // 9
                    dos.writeUTF(position.getDescription()); // 10
                }
            }

            // EducationSection write
            OrganizationSection education = (OrganizationSection) r.getSection(EDUCATION);
            dos.writeInt(education.getOrganisations().size()); // orgs number 1

            for (Organization organization : education.getOrganisations()) { // итерируем по List<Organizations>
                dos.writeUTF(organization.getLink().getName()); // 2
                dos.writeUTF(organization.getLink().getUrl()); //
                dos.writeInt(organization.getPosition().size()); // pos number // 4
                for (Organization.Position position : organization.getPosition()) {
                    dos.writeInt(position.getStartDate().getYear()); // 5
                    dos.writeInt(position.getStartDate().getMonth().getValue()); // 6
                    dos.writeInt(position.getEndDate().getYear()); // 7
                    dos.writeInt(position.getEndDate().getMonth().getValue()); // 8
                    dos.writeUTF(position.getTitle()); // 9
                    dos.writeUTF(position.getDescription()); // 10
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections

            // TestSection read
            resume.setSection(PERSONAL, new TextSection(dis.readUTF()));
            resume.setSection(OBJECTIVE, new TextSection(dis.readUTF()));

            // AchievementsSection read
            int achievementListSize = dis.readInt();
            ListSection achievementSection = new ListSection(new ArrayList<>());
            for (int i = 0; i < achievementListSize; i++) {
                achievementSection.addItem(dis.readUTF());
            }
            resume.setSection(ACHIEVEMENT, achievementSection);

            // QualificationSection read
            int qualificationListSize = dis.readInt();
            ListSection qualificationSection = new ListSection(new ArrayList<>());
            for (int i = 0; i < qualificationListSize; i++) {
                qualificationSection.addItem(dis.readUTF());
            }
            resume.setSection(QUALIFICATION, qualificationSection);

            resume.setSection(EXPERIENCE, experienceEducationSectionsRead(dis));
            resume.setSection(EDUCATION, experienceEducationSectionsRead(dis));

            return resume;
        }
    }

    protected void experienceEducationSectionWrite(SectionType sectionType, DataOutputStream dos, Resume r) {
        // EducationSection write
        OrganizationSection education = (OrganizationSection) r.getSection(sectionType);

        try {
            dos.writeInt(education.getOrganisations().size()); // orgs number 1
            for (Organization organization : education.getOrganisations()) { // итерируем по List<Organizations>
                dos.writeUTF(organization.getLink().getName()); // 2
                dos.writeUTF(organization.getLink().getUrl()); //
                dos.writeInt(organization.getPosition().size()); // pos number // 4
                for (Organization.Position position : organization.getPosition()) {
                    dos.writeInt(position.getStartDate().getYear()); // 5
                    dos.writeInt(position.getStartDate().getMonth().getValue()); // 6
                    dos.writeInt(position.getEndDate().getYear()); // 7
                    dos.writeInt(position.getEndDate().getMonth().getValue()); // 8
                    dos.writeUTF(position.getTitle()); // 9
                    dos.writeUTF(position.getDescription()); // 10
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    protected OrganizationSection experienceEducationSectionsRead(DataInputStream dis) {
        int experienceSectionSize = 0; // 1
        try {
            experienceSectionSize = dis.readInt();
            List<Organization> experienceList = new ArrayList<>();
            for (int i = 0; i < experienceSectionSize; i++) {
                List<Organization.Position> positionList = new ArrayList<>();
                Link link = new Link((String) dis.readUTF(), (String) dis.readUTF()); // 2, 3
                int positionSectionSize = dis.readInt(); // 4
                for (int k = 0; k < positionSectionSize; k++) {
                    positionList.add(new Organization.Position(LocalDate.of(dis.readInt(), Month.of(dis.readInt()), 1), LocalDate.of(dis.readInt(), Month.of(dis.readInt()), 1), dis.readUTF(), dis.readUTF()));
                }
                Organization organization = new Organization(link, positionList);
                experienceList.add(organization);
            }
            return new OrganizationSection(experienceList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
