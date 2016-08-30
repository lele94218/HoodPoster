package com.hood.pojo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author taoranxue on 8/29/16 7:06 PM.
 */
public class UserPost {
    private Long id;

    //    @NotBlank
//    @SafeHtml(message = "html.safe")
    @Size(min = 5, max = 250, message = "{title.size}")
    private String title;

    private Date date;

    private String userName;
    //    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.RELAXED, message = "{html.safe}")
    @Size(min = 5, max = 65535, message = "{content.size}")
    private String content;

    public UserPost() {

    }

    public UserPost(Long id, String title, Date date, String userName, String content) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.userName = userName;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserId(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
