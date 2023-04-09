package com.example.hungntph19080_assignment_mob403.Model;

import java.util.ArrayList;

public class Comic {
    private int id;
    private String nameComic;
    private String descriptionComic;
    private String authorComic;
    private String publishYearComic;
    private String coverImageComic;
    private ArrayList listsImageComic;

    public Comic() {
    }

    public Comic(int id, String nameComic, String descriptionComic, String authorComic, String publishYearComic, String coverImageComic, ArrayList listsImageComic) {
        this.id = id;
        this.nameComic = nameComic;
        this.descriptionComic = descriptionComic;
        this.authorComic = authorComic;
        this.publishYearComic = publishYearComic;
        this.coverImageComic = coverImageComic;
        this.listsImageComic = listsImageComic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getDescriptionComic() {
        return descriptionComic;
    }

    public void setDescriptionComic(String descriptionComic) {
        this.descriptionComic = descriptionComic;
    }

    public String getAuthorComic() {
        return authorComic;
    }

    public void setAuthorComic(String authorComic) {
        this.authorComic = authorComic;
    }

    public String getPublishYearComic() {
        return publishYearComic;
    }

    public void setPublishYearComic(String publishYearComic) {
        this.publishYearComic = publishYearComic;
    }

    public String getCoverImageComic() {
        return coverImageComic;
    }

    public void setCoverImageComic(String coverImageComic) {
        this.coverImageComic = coverImageComic;
    }

    public ArrayList getListsImageComic() {
        return listsImageComic;
    }

    public void setListsImageComic(ArrayList listsImageComic) {
        this.listsImageComic = listsImageComic;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", nameComic='" + nameComic + '\'' +
                ", descriptionComic='" + descriptionComic + '\'' +
                ", authorComic='" + authorComic + '\'' +
                ", publishYearComic='" + publishYearComic + '\'' +
                ", coverImageComic='" + coverImageComic + '\'' +
                ", listsImageComic=" + listsImageComic +
                '}';
    }
}
