package com.example.hungntph19080_assignment_mob403.Model;

public class Comment {
    private int id;
    private int idUser;
    private int idComic;
    private String nameUserComment;
    private String contentComment;
    private String dateComment;

    public Comment() {
    }

    public Comment(int id, int idUser, int idComic, String nameUserComment, String contentComment, String dateComment) {
        this.id = id;
        this.idUser = idUser;
        this.idComic = idComic;
        this.nameUserComment = nameUserComment;
        this.contentComment = contentComment;
        this.dateComment = dateComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public String getNameUserComment() {
        return nameUserComment;
    }

    public void setNameUserComment(String nameUserComment) {
        this.nameUserComment = nameUserComment;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public String getDateComment() {
        return dateComment;
    }

    public void setDateComment(String dateComment) {
        this.dateComment = dateComment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idComic=" + idComic +
                ", nameUserComment='" + nameUserComment + '\'' +
                ", contentComment='" + contentComment + '\'' +
                ", dateComment='" + dateComment + '\'' +
                '}';
    }
}
