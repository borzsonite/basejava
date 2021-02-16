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

            // OrganizationSection write
            OrganizationSection experience = (OrganizationSection) r.getSection(EXPERIENCE);
            for (Organization organization : experience.getOrganisations()) {
                dos.writeInt(experience.getOrganisations().size()); // orgs number
                dos.writeUTF(organization.getLink().getName());
                dos.writeUTF(organization.getLink().getUrl());
                for (Organization.Position position : organization.getPosition()) {
                    dos.writeInt(organization.getPosition().size()); // pos number
                    dos.writeInt(position.getStartDate().getYear());
                    dos.writeInt(position.getStartDate().getMonth().getValue());
                    dos.writeInt(position.getEndDate().getYear());
                    dos.writeInt(position.getEndDate().getMonth().getValue());
                    dos.writeUTF(position.getTitle());
                    dos.writeUTF(position.getDescription());
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

            // OrganizationSection read
            int organizationSectionSize = dis.readInt();
            Link link = new Link((String) dis.readUTF(), (String) dis.readUTF());
            int positionSectionSize = dis.readInt();
            List<Organization> organizationList = new ArrayList<>();
            List<Organization.Position> positionList = new ArrayList<>();
            for (int i = 0; i < positionSectionSize; i++) {
                positionList.add(new Organization.Position(LocalDate.of(dis.readInt(), Month.of(dis.readInt()), 1), LocalDate.of(dis.readInt(), Month.of(dis.readInt()), 1), dis.readUTF(), dis.readUTF()));

            }

            return resume;
        }
    }
}
