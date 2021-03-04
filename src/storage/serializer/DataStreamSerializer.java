package storage.serializer;


import model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();

            writeWithException(contacts.entrySet(), collectionElement -> {
                dos.writeUTF(String.valueOf(collectionElement.getKey()));
                dos.writeUTF(collectionElement.getValue());
            }, dos);

            Map<SectionType, AbstractSection> sections = r.getAllSections();
           // dos.writeInt(sections.size());
//            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
//                dos.writeUTF(entry.getKey().name());
//                abstractSectionWrite(entry.getKey(), dos, r);
//            }
            // вместо цикла вызывается метод в который передается entrySet map'ы, лямбда, dos
            writeWithException(sections.entrySet(), new Writable<Map.Entry<SectionType, AbstractSection>>() {
                @Override
                public void accept(Map.Entry<SectionType, AbstractSection> collectionElement) throws IOException {
                    abstractSectionWrite(collectionElement, dos);
                }
            }, dos);

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
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, abstractSectionRead(dis, sectionType));
            }
            return resume;
        }
    }

    protected <T> void abstractSectionWrite(Map.Entry<SectionType, AbstractSection> collection, DataOutputStream dos) throws IOException {
        switch (collection.getKey()) {
            case PERSONAL:
            case OBJECTIVE:
                //dos.writeUTF(((TextSection) r.getSection(sectionType)).getContent());
                dos.writeUTF(String.valueOf(collection.getValue()));
                break;

            case ACHIEVEMENT:
            case QUALIFICATION:
                ListSection listSection = (ListSection) collection.getValue();
                List<String> listSectionsItems = listSection.getItems();
                dos.writeInt(listSectionsItems.size());
                for (String elem : listSectionsItems) {
                    dos.writeUTF(elem);
                }
                break;

            case EXPERIENCE:
            case EDUCATION:
                OrganizationSection organizationSection = (OrganizationSection) collection.getValue();
                List<Organization> organizations = organizationSection.getOrganisations();
                dos.writeInt(organizations.size());
                for (Organization organization : organizations) {
                    Link link = organization.getLink();
                    String url = link.getUrl();
                    dos.writeUTF(link.getName());
                    dos.writeUTF(url != null ? url : "");
                    dos.writeInt(organization.getPosition().size());

                    for (Organization.Position position : organization.getPosition()) {
                        writeDate(dos, position.getStartDate());
                        writeDate(dos, position.getEndDate());
                        dos.writeUTF(position.getTitle());
                        dos.writeUTF(position.getDescription() != null ? position.getDescription() : "");
                    }
                }
        }
    }

    protected void writeDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }

    protected AbstractSection abstractSectionRead(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());

            case ACHIEVEMENT:
            case QUALIFICATION:
                int achievementListSize = dis.readInt();
                ListSection achievementSection = new ListSection(new ArrayList<>());
                for (int i = 0; i < achievementListSize; i++) {
                    achievementSection.addItem(dis.readUTF());
                }
                return achievementSection;

            case EXPERIENCE:
            case EDUCATION:
                int experienceSectionSize = dis.readInt();
                List<Organization> experienceList = new ArrayList<>();
                for (int i = 0; i < experienceSectionSize; i++) {
                    List<Organization.Position> positionList = new ArrayList<>();
                    String name = dis.readUTF();
                    String url = dis.readUTF();
                    Link link = new Link(name, url.equals("") ? null : url);
                    int positionSectionSize = dis.readInt();
                    for (int k = 0; k < positionSectionSize; k++) {
                        LocalDate startDate = readDate(dis);
                        LocalDate endDate = readDate(dis);
                        String title = dis.readUTF();
                        String description = dis.readUTF();
                        positionList.add(new Organization.Position(startDate, endDate, title, description.equals("") ? null : description));
                    }
                    Organization organization = new Organization(link, positionList);
                    experienceList.add(organization);
                }
                return new OrganizationSection(experienceList);
        }
        return null;
    }

    protected LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.of(dis.readInt()), 1);
    }

    protected <T> void writeWithException(Collection<T> collection, Writable<T> action, DataOutputStream dos) throws IOException {
        int size = collection.size();
        dos.writeInt(size);
        for(T elem: collection) {
            action.accept(elem);
        }
    }
}
