package com.aikyam.prm.model;

public class IssueCommentEvent {
    private String action;
    private Comment comment;
    private Issue issue;
    private Repository repository;
    private Sender sender;


    public IssueCommentEvent(String action, Comment comment, Issue issue, Repository repository, Sender sender) {
        this.action = action;
        this.comment = comment;
        this.issue = issue;
        this.repository = repository;
        this.sender = sender;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "IssueCommentEvent{" +
                "action='" + action + '\'' +
                ", comment=" + comment +
                ", issue=" + issue +
                ", repository=" + repository +
                ", sender=" + sender +
                '}';
    }




    public static class Comment {
        private String body;

        public Comment(String body) {
            this.body = body;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public String toString() {
            return "Comment{" +
                    "body='" + body +
                    '}';
        }
    }

    public static class Issue {
        private int number;
        private String title;
        private PullRequest pull_request;

        public Issue(int number, String title, PullRequest pull_request) {
            this.number = number;
            this.title = title;
            this.pull_request = pull_request;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public PullRequest getPull_request() {
            return pull_request;
        }

        public void setPull_request(PullRequest pull_request) {
            this.pull_request = pull_request;
        }

        @Override
        public String toString() {
            return "Issue{" +
                    "number=" + number +
                    ", title='" + title +
                    '}';
        }
    }

    public static class PullRequest {
        private long id;
        private String title;
        private String body;
        private User user;
        private boolean merged;
        private String state;

        public PullRequest(long id, String title, String body, User user, boolean merged, String state) {
            this.id = id;
            this.title = title;
            this.body = body;
            this.user = user;
            this.merged = merged;
            this.state = state;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public boolean isMerged() {
            return merged;
        }

        public void setMerged(boolean merged) {
            this.merged = merged;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    public static class User {
        private String login;


        public User(String login) {
            this.login = login;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        @Override
        public String toString() {
            return "User{" +
                    "login='" + login + '\'' +
                    '}';
        }
    }

    public static class Repository {
        private String name;
        private String full_name;

        public Repository(String name, String full_name) {
            this.name = name;
            this.full_name = full_name;
        }

        public String getName() {
            return name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public void setName(String name) {
            this.name = name;


        }

        @Override
        public String toString() {
            return "Repository{" +
                    "name='" + name + '\'' +
                    ", full_name='" + full_name + '\'' +
                    '}';
        }
    }

    public static class Sender {
        private String login;

        public Sender(String login) {
            this.login = login;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        @Override
        public String toString() {
            return "Sender{" +
                    "login='" + login + '\'' +
                    '}';
        }
    }
}
