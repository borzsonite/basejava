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

            dos.writeUTF(((TextSection) r.getSection(PERSONAL)).getContent());
            dos.writeUTF(((TextSection) r.getSection(OBJECTIVE)).getContent());
            listSectionWrite(ACHIEVEMENT, dos, r);
            listSectionWrite(QUALIFICATION, dos, r);
            experienceEducationSectionWrite(EXPERIENCE, dos, r);
            experienceEducationSectionWrite(EDUCATION, dos, r);
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

            resume.setSection(PERSONAL, new TextSection(dis.readUTF()));
            resume.setSection(OBJECTIVE, new TextSection(dis.readUTF()));
            resume.setSection(ACHIEVEMENT, listSectionRead(dis));
            resume.setSection(QUALIFICATION, listSectionRead(dis));
            resume.setSection(EXPERIENCE, experienceEducationSectionsRead(dis));
            resume.setSection(EDUCATION, experienceEducationSectionsRead(dis));

            return resume;
        }
    }

    protected void experienceEducationSectionWrite(SectionType sectionType, DataOutputStream dos, Resume r) {
        OrganizationSection education = (OrganizationSection) r.getSection(sectionType);
        try {
            dos.writeInt(education.getOrganisations().size());
            for (Organization organization : education.getOrganisations()) {
                dos.writeUTF(organization.getLink().getName());
                dos.writeUTF(organization.getLink().getUrl());
                dos.writeInt(organization.getPosition().size());
                for (Organization.Position position : organization.getPosition()) {
                    dos.writeInt(position.getStartDate().getYear());
                    dos.writeInt(position.getStartDate().getMonth().getValue());
                    dos.writeInt(position.getEndDate().getYear());
                    dos.writeInt(position.getEndDate().getMonth().getValue());
                    dos.writeUTF(position.getTitle());
                    dos.writeUTF(position.getDescription());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void listSectionWrite(SectionType sectionType, DataOutputStream dos, Resume r) {
        ListSection achievements = (ListSection) r.getSection(sectionType);
        try {
            dos.writeInt(achievements.getItems().size());
            for (String elem : achievements.getItems()) {
                dos.writeUTF(elem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected OrganizationSection experienceEducationSectionsRead(DataInputStream dis) {
        int experienceSectionSize = 0;
        try {
            experienceSectionSize = dis.readInt();
            List<Organization> experienceList = new ArrayList<>();
            for (int i = 0; i < experienceSectionSize; i++) {
                List<Organization.Position> positionList = new ArrayList<>();
                Link link = new Link(dis.readUTF(), dis.readUTF());
                int positionSectionSize = dis.readInt();
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

    protected ListSection listSectionRead(DataInputStream dis) {
        int achievementListSize = 0;
        try {
            achievementListSize = dis.readInt();
            ListSection achievementSection = new ListSection(new ArrayList<>());
            for (int i = 0; i < achievementListSize; i++) {
                achievementSection.addItem(dis.readUTF());
            }
            return achievementSection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
