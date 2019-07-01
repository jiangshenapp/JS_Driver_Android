package com.js.driver.model.request;

/**
 * Created by huyg on 2019-07-01.
 */
public class OrderComment {


    /**
     * commentImage1 : string
     * commentImage2 : string
     * commentImage3 : string
     * id : 0
     */

    private String commentImage1;
    private String commentImage2;
    private String commentImage3;
    private double id;

    public String getCommentImage1() {
        return commentImage1;
    }

    public void setCommentImage1(String commentImage1) {
        this.commentImage1 = commentImage1;
    }

    public String getCommentImage2() {
        return commentImage2;
    }

    public void setCommentImage2(String commentImage2) {
        this.commentImage2 = commentImage2;
    }

    public String getCommentImage3() {
        return commentImage3;
    }

    public void setCommentImage3(String commentImage3) {
        this.commentImage3 = commentImage3;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }
}
