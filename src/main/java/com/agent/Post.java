package com.agent;

/**
 * This is a pojo class to create post and return after intercepting the save method calling.
*/
public class Post {

    private String postId;
    private String postName;
    private String postContent;

    public Post() {
        super();
    }
    public Post(String postId,String postContent, String postName) {
        this.postContent = postContent;
        this.postId = postId;
        this.postName = postName;
    }
    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

}
