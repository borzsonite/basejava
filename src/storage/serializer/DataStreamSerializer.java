package storage.serializer;


import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


            Map<SectionType, AbstractSection> sections = r.getAllSections();
            dos.writeInt(sections.size());
            System.out.println("Int organisations section size");
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                System.out.println("SectonType key:" + entry.getKey() );

                abstractSectionWrite(entry.getKey(), dos, r);
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

            int sectionsSize = dis.readInt();
            for (int i=0; i<sectionsSize; i++) {
                try {
                    SectionType sectionType = SectionType.valueOf(dis.readUTF());
                    resume.setSection(sectionType, abstractSectionRead(dis, sectionType));
                } catch (IOException e) {
                }
            }

            return resume;
        }
    }

    protected void abstractSectionWrite(SectionType sectionType, DataOutputStream dos, Resume r) {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                try {
                    dos.writeUTF(((TextSection) r.getSection(sectionType)).getContent());
                    System.out.println(sectionType+":value");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case ACHIEVEMENT:
            case QUALIFICATION:
                ListSection listSection = (ListSection) r.getSection(sectionType);
                try {

                    dos.writeInt(listSection.getItems().size());
                    System.out.println("list section size:" + listSection.getItems().size());

                    for (String elem : listSection.getItems()) {
                        dos.writeUTF(elem);
                        System.out.println(sectionType+":value");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case EXPERIENCE:
            case EDUCATION:
                OrganizationSection organizationSection = (OrganizationSection) r.getSection(sectionType);
                try {
                    dos.writeInt(organizationSection.getOrganisations().size());
                    System.out.println("organizationSection size:" + organizationSection.getOrganisations().size());

                    for (Organization organization : organizationSection.getOrganisations()) {
                        dos.writeUTF(organization.getLink().getName());
                        System.out.println("organization name:" + organization.getLink().getName());

                        dos.writeUTF(organization.getLink().getUrl());
                        System.out.println("organization url:" + organization.getLink().getUrl());

                        dos.writeInt(organization.getPosition().size());
                        System.out.println("positions size:" + organization.getPosition().size());

                        for (Organization.Position position : organization.getPosition()) {
                            dos.writeInt(position.getStartDate().getYear());
                            System.out.println("start year: " + position.getStartDate().getYear());

                            dos.writeInt(position.getStartDate().getMonth().getValue());
                            System.out.println("start month: " + (position.getStartDate().getMonth().getValue()));

                            dos.writeInt(position.getEndDate().getYear());
                            System.out.println("end year: " + position.getEndDate().getYear());

                            dos.writeInt(position.getEndDate().getMonth().getValue());
                            System.out.println("end month: " + (position.getEndDate().getMonth().getValue()));

                            dos.writeUTF(position.getTitle());
                            System.out.println("tile: " + position.getTitle());

                            dos.writeUTF(position.getDescription());
                            System.out.println("descr: " + position.getDescription());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    protected AbstractSection abstractSectionRead(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());

            case ACHIEVEMENT:
            case QUALIFICATION:
                int achievementListSize = 0;
                achievementListSize = dis.readInt();
                ListSection achievementSection = new ListSection(new ArrayList<>());
                for (int i = 0; i < achievementListSize; i++) {
                    achievementSection.addItem(dis.readUTF());
                }
                return achievementSection;

            case EXPERIENCE:
            case EDUCATION:
                int experienceSectionSize = 0;
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

        }
        return null;
    }
}
