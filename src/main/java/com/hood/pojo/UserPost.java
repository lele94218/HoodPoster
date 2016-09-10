package com.hood.pojo;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author taoranxue on 8/29/16 7:06 PM.
 */
public class UserPost {
    private Long id;

    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE, message = "{html.safe}")
    @Size(min = 5, max = 250, message = "{title.size}")
    private String title;

    private Date date;

    private String userName;
    @SafeHtml(
            whitelistType = SafeHtml.WhiteListType.BASIC,
            additionalTagsWithAttributes = {
                    @SafeHtml.Tag(name = "h1", attributes = {"style"}),
                    @SafeHtml.Tag(name = "h2", attributes = {"style"}),
                    @SafeHtml.Tag(name = "h3", attributes = {"style"}),
                    @SafeHtml.Tag(name = "h4", attributes = {"style"}),
                    @SafeHtml.Tag(name = "h5", attributes = {"style"}),
                    @SafeHtml.Tag(name = "h6", attributes = {"style"}),
                    @SafeHtml.Tag(name = "td", attributes = {"style", "abbr", "axis", "colspan", "rowspan", "width"}),
                    @SafeHtml.Tag(name = "th", attributes = {"style", "abbr", "axis", "colspan", "rowspan", "scope", "width"}),
                    @SafeHtml.Tag(name = "tr", attributes = {"style"}),
                    @SafeHtml.Tag(name = "tfoot", attributes = {"style"}),
                    @SafeHtml.Tag(name = "tbody", attributes = {"style"}),
                    @SafeHtml.Tag(name = "table", attributes = {"style"}),
                    @SafeHtml.Tag(name = "ul", attributes = {"type"}),
                    @SafeHtml.Tag(name = "font", attributes = {"face", "style"}),
                    @SafeHtml.Tag(name = "img", attributes = {"style", "data-filename", "align", "alt", "height", "src", "title", "width"}),
                    @SafeHtml.Tag(name = "b", attributes = {"style"}),
                    @SafeHtml.Tag(name = "u", attributes = {"style"}),
                    @SafeHtml.Tag(name = "p", attributes = {"style"}),
                    @SafeHtml.Tag(name = "span", attributes = {"style"})
            },
            message = "{html.safe}"
    )
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
