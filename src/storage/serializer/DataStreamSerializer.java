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
                dos.writeUTF(collectionElement.getKey().name());
                dos.writeUTF(collectionElement.getValue());
            });

            Map<SectionType, AbstractSection> sections = r.getAllSections();
            writeWithException(dos, sections.entrySet(), collectionElement -> {
                dos.writeUTF(collectionElement.getKey().name());
                switch (collectionElement.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(String.valueOf(collectionElement.getValue()));
                        break;

                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        List<String> listSectionsItems = ((ListSection) collectionElement.getValue()).getItems();
                        writeWithException(dos, listSectionsItems, dos::writeUTF);
                        break;

                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection organizationSection = (OrganizationSection) collectionElement.getValue();
                        List<Organization> organizations = organizationSection.getOrganisations();
                        writeWithException(dos, organizations, element -> {
                            Link link = element.getLink();
                            String url = link.getUrl();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(url != null ? url : "");

                            writeWithException(dos, element.getPosition(), collectionElement1 -> {
                                writeDate(dos, collectionElement1.getStartDate());
                                writeDate(dos, collectionElement1.getEndDate());
                                dos.writeUTF(collectionElement1.getTitle());
                                dos.writeUTF(collectionElement1.getDescription() != null ? collectionElement1.getDescription() : "");
                            });
                        });
                }

            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSection(sectionType, abstractSectionRead(dis, sectionType));
            });

            return resume;
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
                ListSection achievementSection = new ListSection(new ArrayList<>());

                readWithException(dis, () -> achievementSection.addItem(dis.readUTF()));
                return achievementSection;

            case EXPERIENCE:
            case EDUCATION:
                List<Organization> experienceList = new ArrayList<>();
                readWithException(dis, () -> {
                    List<Organization.Position> positionList = new ArrayList<>();
                    String name = dis.readUTF();
                    String url = dis.readUTF();
                    Link link = new Link(name, url.equals("") ? null : url);
                    readWithException(dis, () -> {
                        LocalDate startDate = readDate(dis);
                        LocalDate endDate = readDate(dis);
                        String title = dis.readUTF();
                        String description = dis.readUTF();
                        positionList.add(new Organization.Position(startDate, endDate, title, description.equals("") ? null : description));
                    });
                    Organization organization = new Organization(link, positionList);
                    experienceList.add(organization);
                });

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

    protected void readWithException(DataInputStream dis, Readable action) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            action.accept();
        }
    }
}
