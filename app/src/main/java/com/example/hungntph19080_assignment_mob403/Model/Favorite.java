package com.example.hungntph19080_assignment_mob403.Model;

import java.util.ArrayList;

public class Favorite {
    private int id;
    private String nameComicFavorite;
    private String descriptionComicFavorite;
    private String authorComicFavorite;
    private String publishYearComicFavorite;
    private String coverImageComicFavorite;
    private ArrayList listsImageComicFavorite;

    public Favorite() {
    }

    public Favorite(int id, String nameComicFavorite, String descriptionComicFavorite, String authorComicFavorite, String publishYearComicFavorite, String coverImageComicFavorite, ArrayList listsImageComicFavorite) {
        this.id = id;
        this.nameComicFavorite = nameComicFavorite;
        this.descriptionComicFavorite = descriptionComicFavorite;
        this.authorComicFavorite = authorComicFavorite;
        this.publishYearComicFavorite = publishYearComicFavorite;
        this.coverImageComicFavorite = coverImageComicFavorite;
        this.listsImageComicFavorite = listsImageComicFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameComicFavorite() {
        return nameComicFavorite;
    }

    public void setNameComicFavorite(String nameComicFavorite) {
        this.nameComicFavorite = nameComicFavorite;
    }

    public String getDescriptionComicFavorite() {
        return descriptionComicFavorite;
    }

    public void setDescriptionComicFavorite(String descriptionComicFavorite) {
        this.descriptionComicFavorite = descriptionComicFavorite;
    }

    public String getAuthorComicFavorite() {
        return authorComicFavorite;
    }

    public void setAuthorComicFavorite(String authorComicFavorite) {
        this.authorComicFavorite = authorComicFavorite;
    }

    public String getPublishYearComicFavorite() {
        return publishYearComicFavorite;
    }

    public void setPublishYearComicFavorite(String publishYearComicFavorite) {
        this.publishYearComicFavorite = publishYearComicFavorite;
    }

    public String getCoverImageComicFavorite() {
        return coverImageComicFavorite;
    }

    public void setCoverImageComicFavorite(String coverImageComicFavorite) {
        this.coverImageComicFavorite = coverImageComicFavorite;
    }

    public ArrayList getListsImageComicFavorite() {
        return listsImageComicFavorite;
    }

    public void setListsImageComicFavorite(ArrayList listsImageComicFavorite) {
        this.listsImageComicFavorite = listsImageComicFavorite;
    }
}
