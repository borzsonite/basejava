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

            writeWithException(dos, contacts.entrySet(), collectionElement -> {
                dos.writeUTF(String.valueOf(collectionElement.getKey()));
                dos.writeUTF(collectionElement.getValue());
            });

            Map<SectionType, AbstractSection> sections = r.getAllSections();
            writeWithException(dos, sections.entrySet(), collectionElement -> {
                dos.writeUTF(String.valueOf(collectionElement.getKey()));
                abstractSectionWrite(collectionElement, dos);
            });
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
                dos.writeUTF(String.valueOf(collection.getValue()));
                break;

            case ACHIEVEMENT:
            case QUALIFICATION:
                List<String> listSectionsItems = ((ListSection) collection.getValue()).getItems();
                writeWithException(dos, listSectionsItems, dos::writeUTF);
                break;

            case EXPERIENCE:
            case EDUCATION:
                OrganizationSection organizationSection = (OrganizationSection) collection.getValue();
                List<Organization> organizations = organizationSection.getOrganisations();
                writeWithException(dos, organizations, collectionElement -> {
                    Link link = collectionElement.getLink();
                    String url = link.getUrl();
                    dos.writeUTF(link.getName());
                    dos.writeUTF(url != null ? url : "");

                    writeWithException(dos, collectionElement.getPosition(), collectionElement1 -> {
                        writeDate(dos, collectionElement1.getStartDate());
                        writeDate(dos, collectionElement1.getEndDate());
                        dos.writeUTF(collectionElement1.getTitle());
                        dos.writeUTF(collectionElement1.getDescription() != null ? collectionElement1.getDescription() : "");
                    });
                });
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

    protected <T> void writeWithException(DataOutputStream dos, Collection<T> collection, Writable<T> action) throws IOException {
        int size = collection.size();
        dos.writeInt(size);
        for (T elem : collection) {
            action.accept(elem);
        }
    }
}
