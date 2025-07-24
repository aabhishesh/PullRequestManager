package com.draaggy.prm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueCommentEvent {
    private String action;
    private Issue issue;
    private Comment comment;
    private Repository repository;
    private Sender sender;

    public IssueCommentEvent(String action, Issue issue, Comment comment, Repository repository, Sender sender) {
        this.action = action;
        this.issue = issue;
        this.comment = comment;
        this.repository = repository;
        this.sender = sender;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
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
                ", issue=" + issue +
                ", comment=" + comment +
                ", repository=" + repository +
                ", sender=" + sender +
                '}';
    }

    public static class Issue {
        private String url;
        private String repository_url;
        private String labels_url;
        private String comments_url;
        private String events_url;
        private int number;
        private String title;
        private User user;
        private Object[] labels;
        private String state;
        private boolean locked;
        private Object assignee;
        private Object[] assignees;
        private Object milestone;
        private int comments;
        private String created_at;
        private String updated_at;
        private String closed_at;
        private String author_association;
        private Object active_lock_reason;
        private boolean draft;
        private PullRequest pull_request;
        private String body;
        private Object state_reason;

        public Issue(String url, String repository_url, String labels_url, String comments_url, String events_url, int number, String title, User user, Object[] labels, String state, boolean locked, Object assignee, Object[] assignees, Object milestone, int comments, String created_at, String updated_at, String closed_at, String author_association, Object active_lock_reason, boolean draft, PullRequest pull_request, String body, Object state_reason) {
            this.url = url;
            this.repository_url = repository_url;
            this.labels_url = labels_url;
            this.comments_url = comments_url;
            this.events_url = events_url;
            this.number = number;
            this.title = title;
            this.user = user;
            this.labels = labels;
            this.state = state;
            this.locked = locked;
            this.assignee = assignee;
            this.assignees = assignees;
            this.milestone = milestone;
            this.comments = comments;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.closed_at = closed_at;
            this.author_association = author_association;
            this.active_lock_reason = active_lock_reason;
            this.draft = draft;
            this.pull_request = pull_request;
            this.body = body;
            this.state_reason = state_reason;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRepository_url() {
            return repository_url;
        }

        public void setRepository_url(String repository_url) {
            this.repository_url = repository_url;
        }

        public String getLabels_url() {
            return labels_url;
        }

        public void setLabels_url(String labels_url) {
            this.labels_url = labels_url;
        }

        public String getComments_url() {
            return comments_url;
        }

        public void setComments_url(String comments_url) {
            this.comments_url = comments_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
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

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Object[] getLabels() {
            return labels;
        }

        public void setLabels(Object[] labels) {
            this.labels = labels;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public Object getAssignee() {
            return assignee;
        }

        public void setAssignee(Object assignee) {
            this.assignee = assignee;
        }

        public Object[] getAssignees() {
            return assignees;
        }

        public void setAssignees(Object[] assignees) {
            this.assignees = assignees;
        }

        public Object getMilestone() {
            return milestone;
        }

        public void setMilestone(Object milestone) {
            this.milestone = milestone;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getClosed_at() {
            return closed_at;
        }

        public void setClosed_at(String closed_at) {
            this.closed_at = closed_at;
        }

        public String getAuthor_association() {
            return author_association;
        }

        public void setAuthor_association(String author_association) {
            this.author_association = author_association;
        }

        public Object getActive_lock_reason() {
            return active_lock_reason;
        }

        public void setActive_lock_reason(Object active_lock_reason) {
            this.active_lock_reason = active_lock_reason;
        }

        public boolean isDraft() {
            return draft;
        }

        public void setDraft(boolean draft) {
            this.draft = draft;
        }

        public PullRequest getPull_request() {
            return pull_request;
        }

        public void setPull_request(PullRequest pull_request) {
            this.pull_request = pull_request;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Object getState_reason() {
            return state_reason;
        }

        public void setState_reason(Object state_reason) {
            this.state_reason = state_reason;
        }

        @Override
        public String toString() {
            return "Issue{" +
                    "url='" + url + '\'' +
                    ", repository_url='" + repository_url + '\'' +
                    ", labels_url='" + labels_url + '\'' +
                    ", comments_url='" + comments_url + '\'' +
                    ", events_url='" + events_url + '\'' +
                    ", number=" + number +
                    ", title='" + title + '\'' +
                    ", user=" + user +
                    ", labels=" + labels +
                    ", state='" + state + '\'' +
                    ", locked=" + locked +
                    ", assignee=" + assignee +
                    ", assignees=" + assignees +
                    ", milestone=" + milestone +
                    ", comments=" + comments +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", closed_at='" + closed_at + '\'' +
                    ", author_association='" + author_association + '\'' +
                    ", active_lock_reason=" + active_lock_reason +
                    ", draft=" + draft +
                    ", pull_request=" + pull_request +
                    ", body='" + body + '\'' +
                    ", state_reason=" + state_reason +
                    '}';
        }

        public static class PullRequest {
            private String url;
            private String html_url;
            private String diff_url;
            private String patch_url;
            private String merged_at;

            public PullRequest(String url, String html_url, String diff_url, String patch_url, String merged_at) {
                this.url = url;
                this.html_url = html_url;
                this.diff_url = diff_url;
                this.patch_url = patch_url;
                this.merged_at = merged_at;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getHtml_url() {
                return html_url;
            }

            public void setHtml_url(String html_url) {
                this.html_url = html_url;
            }

            public String getDiff_url() {
                return diff_url;
            }

            public void setDiff_url(String diff_url) {
                this.diff_url = diff_url;
            }

            public String getPatch_url() {
                return patch_url;
            }

            public void setPatch_url(String patch_url) {
                this.patch_url = patch_url;
            }

            public String getMerged_at() {
                return merged_at;
            }

            public void setMerged_at(String merged_at) {
                this.merged_at = merged_at;
            }

            @Override
            public String toString() {
                return "PullRequest{" +
                        "url='" + url + '\'' +
                        ", html_url='" + html_url + '\'' +
                        ", diff_url='" + diff_url + '\'' +
                        ", patch_url='" + patch_url + '\'' +
                        ", merged_at='" + merged_at + '\'' +
                        '}';
            }
        }

        public static class User {
            private String login;
            private long id;
            private String type;
            private String user_view_type;
            private boolean site_admin;

            public User(String login, long id, String type, String user_view_type, boolean site_admin) {
                this.login = login;
                this.id = id;
                this.type = type;
                this.user_view_type = user_view_type;
                this.site_admin = site_admin;
            }

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUser_view_type() {
                return user_view_type;
            }

            public void setUser_view_type(String user_view_type) {
                this.user_view_type = user_view_type;
            }

            public boolean isSite_admin() {
                return site_admin;
            }

            public void setSite_admin(boolean site_admin) {
                this.site_admin = site_admin;
            }

            @Override
            public String toString() {
                return "User{" +
                        "login='" + login + '\'' +
                        ", id=" + id +
                        ", type='" + type + '\'' +
                        ", user_view_type='" + user_view_type + '\'' +
                        ", site_admin=" + site_admin +
                        '}';
            }
        }
    }

    public static class Comment {
        private String url;
        private long id;
        private User user;
        private String created_at;
        private String updated_at;
        private String author_association;
        private String body;
        private Object performed_via_github_app;

        public Comment(String url, long id, User user, String created_at, String updated_at, String author_association, String body, Object performed_via_github_app) {
            this.url = url;
            this.id = id;
            this.user = user;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.author_association = author_association;
            this.body = body;
            this.performed_via_github_app = performed_via_github_app;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getAuthor_association() {
            return author_association;
        }

        public void setAuthor_association(String author_association) {
            this.author_association = author_association;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Object getPerformed_via_github_app() {
            return performed_via_github_app;
        }

        public void setPerformed_via_github_app(Object performed_via_github_app) {
            this.performed_via_github_app = performed_via_github_app;
        }

        @Override
        public String toString() {
            return "Comment{" +
                    "url='" + url + '\'' +
                    ", id=" + id +
                    ", user=" + user +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", author_association='" + author_association + '\'' +
                    ", body='" + body + '\'' +
                    ", performed_via_github_app=" + performed_via_github_app +
                    '}';
        }

        public static class User {
            private String login;
            private long id;
            private String type;
            private String user_view_type;
            private boolean site_admin;

            public User(String login, long id, String type, String user_view_type, boolean site_admin) {
                this.login = login;
                this.id = id;
                this.type = type;
                this.user_view_type = user_view_type;
                this.site_admin = site_admin;
            }

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUser_view_type() {
                return user_view_type;
            }

            public void setUser_view_type(String user_view_type) {
                this.user_view_type = user_view_type;
            }

            public boolean isSite_admin() {
                return site_admin;
            }

            public void setSite_admin(boolean site_admin) {
                this.site_admin = site_admin;
            }

            @Override
            public String toString() {
                return "User{" +
                        "login='" + login + '\'' +
                        ", id=" + id +
                        ", type='" + type + '\'' +
                        ", user_view_type='" + user_view_type + '\'' +
                        ", site_admin=" + site_admin +
                        '}';
            }
        }
    }

    public static class Repository {
        private long id;
        private String statuses_url;
        private String name;
        private String full_name;

        public Repository(long id, String statuses_url, String name, String full_name) {
            this.id = id;
            this.statuses_url = statuses_url;
            this.name = name;
            this.full_name = full_name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getStatuses_url() {
            return statuses_url;
        }

        public void setStatuses_url(String statuses_url) {
            this.statuses_url = statuses_url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        @Override
        public String toString() {
            return "Repository{" +
                    "id=" + id +
                    ", statuses_url='" + statuses_url + '\'' +
                    ", name='" + name + '\'' +
                    ", full_name='" + full_name + '\'' +
                    '}';
        }
    }

    public static class Sender {
        private String login;
        private String type;
        private String user_view_type;
        private boolean site_admin;

        public Sender(String login, String type, String user_view_type, boolean site_admin) {
            this.login = login;
            this.type = type;
            this.user_view_type = user_view_type;
            this.site_admin = site_admin;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUser_view_type() {
            return user_view_type;
        }

        public void setUser_view_type(String user_view_type) {
            this.user_view_type = user_view_type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }

        @Override
        public String toString() {
            return "Sender{" +
                    "login='" + login + '\'' +
                    ", type='" + type + '\'' +
                    ", user_view_type='" + user_view_type + '\'' +
                    ", site_admin=" + site_admin +
                    '}';
        }
    }
}
