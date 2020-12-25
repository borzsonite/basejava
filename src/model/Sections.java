package model;

import java.util.Map;
import java.util.TreeMap;

public class Sections {
    Map<SectionType, String> sectionStorage = new TreeMap<>();

    public void setPersonal(String description) {
        sectionStorage.put(SectionType.PERSONAL, description);
    }

    public void setObjectives(String description) {
        sectionStorage.put(SectionType.OBJECTIVE, description);
    }

    public void setAchievement(String description) {
        sectionStorage.put(SectionType.ACHIEVEMENT, description);
    }

    public void setQualification(String description) {
        sectionStorage.put(SectionType.QUALIFICATION, description);
    }

    public void setExperience(String description) {
        sectionStorage.put(SectionType.EXPERIENCE, description);
    }

    public void setEducation(String description) {
        sectionStorage.put(SectionType.EDUCATION, description);
    }

    public String getPersonal() {
        return sectionStorage.get(SectionType.PERSONAL);
    }

    public String  getObjectives() {
        return sectionStorage.get(SectionType.OBJECTIVE);
    }

    public String getAchievement() {
        return sectionStorage.get(SectionType.ACHIEVEMENT);
    }

    public String getQualification() {
       return sectionStorage.get(SectionType.QUALIFICATION);
    }

    public String getExperience() {
        return sectionStorage.get(SectionType.EXPERIENCE);
    }

    public String getEducation() {
        return sectionStorage.get(SectionType.EDUCATION);
    }

}
