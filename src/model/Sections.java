package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Sections {
    private Map<SectionType, List<String>> sectionStorage = new TreeMap<>();
    private Map<SectionType, List<String[]>> sectionArrayStorage = new TreeMap<>();

    public void setPersonal(List<String> description) {
        sectionStorage.put(SectionType.PERSONAL, description);
    }

    public void setObjectives(List<String> description) {
        sectionStorage.put(SectionType.OBJECTIVE, description);
    }

    public void setAchievement(List<String> description) {
        sectionStorage.put(SectionType.ACHIEVEMENT, description);
    }

    public void setQualification(List<String> description) {
        sectionStorage.put(SectionType.QUALIFICATION, description);
    }

    public void setExperience(List<String[]> description) {
        sectionArrayStorage.put(SectionType.EXPERIENCE, description);
    }

    public void setEducation(List<String[]> description) {
        sectionArrayStorage.put(SectionType.EDUCATION, description);
    }

    public List<String> getPersonal() {
        return sectionStorage.get(SectionType.PERSONAL);
    }

    public List<String>  getObjectives() {
        return sectionStorage.get(SectionType.OBJECTIVE);
    }

    public List<String> getAchievement() {
        return sectionStorage.get(SectionType.ACHIEVEMENT);
    }

    public List<String> getQualification() {
       return sectionStorage.get(SectionType.QUALIFICATION);
    }

    public List<String[]> getExperience() {
        return sectionArrayStorage.get(SectionType.EXPERIENCE);
    }

    public List<String[]> getEducation() {
        return sectionArrayStorage.get(SectionType.EDUCATION);
    }
}
