package com.draaggy.prm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PullRequestEvent {
    private String action;
    private int number;
    private PullRequest pull_request;
    private Repository repository;
    private Sender sender;

    public PullRequestEvent(String action, int number, PullRequest pull_request, Repository repository, Sender sender) {
        this.action = action;
        this.number = number;
        this.pull_request = pull_request;
        this.repository = repository;
        this.sender = sender;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PullRequest getPull_request() {
        return pull_request;
    }

    public void setPull_request(PullRequest pull_request) {
        this.pull_request = pull_request;
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
        return "PullRequestEvent{" +
                "action='" + action + '\'' +
                ", number=" + number +
                ", pull_request=" + pull_request +
                ", repository=" + repository +
                ", sender=" + sender +
                '}';
    }

    public static class PullRequest {
        private String url;
        private long id;
        private String html_url;
        private String state;
        private boolean locked;
        private String title;
        private User user;
        private String body;
        private String created_at;
        private String updated_at;
        private String closed_at;
        private String merged_at;
        private String merge_commit_sha;
        private Object assignee;
        private Object[] assignees;
        private Object[] requested_reviewers;
        private Object[] requested_teams;
        private Object[] labels;
        private Object milestone;
        private boolean draft;
        private String commits_url;
        private String review_comments_url;
        private String review_comment_url;
        private String comments_url;
        private String statuses_url;
        private Head head;
        private Base base;

        public PullRequest(String url, long id, String html_url, String state, boolean locked, String title, User user, String body, String created_at, String updated_at, String closed_at, String merged_at, String merge_commit_sha, Object assignee, Object[] assignees, Object[] requested_reviewers, Object[] requested_teams, Object[] labels, Object milestone, boolean draft, String commits_url, String review_comments_url, String review_comment_url, String comments_url, String statuses_url, Head head, Base base) {
            this.url = url;
            this.id = id;
            this.html_url = html_url;
            this.state = state;
            this.locked = locked;
            this.title = title;
            this.user = user;
            this.body = body;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.closed_at = closed_at;
            this.merged_at = merged_at;
            this.merge_commit_sha = merge_commit_sha;
            this.assignee = assignee;
            this.assignees = assignees;
            this.requested_reviewers = requested_reviewers;
            this.requested_teams = requested_teams;
            this.labels = labels;
            this.milestone = milestone;
            this.draft = draft;
            this.commits_url = commits_url;
            this.review_comments_url = review_comments_url;
            this.review_comment_url = review_comment_url;
            this.comments_url = comments_url;
            this.statuses_url = statuses_url;
            this.head = head;
            this.base = base;
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

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
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

        public String getMerged_at() {
            return merged_at;
        }

        public void setMerged_at(String merged_at) {
            this.merged_at = merged_at;
        }

        public String getMerge_commit_sha() {
            return merge_commit_sha;
        }

        public void setMerge_commit_sha(String merge_commit_sha) {
            this.merge_commit_sha = merge_commit_sha;
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

        public Object[] getRequested_reviewers() {
            return requested_reviewers;
        }

        public void setRequested_reviewers(Object[] requested_reviewers) {
            this.requested_reviewers = requested_reviewers;
        }

        public Object[] getRequested_teams() {
            return requested_teams;
        }

        public void setRequested_teams(Object[] requested_teams) {
            this.requested_teams = requested_teams;
        }

        public Object[] getLabels() {
            return labels;
        }

        public void setLabels(Object[] labels) {
            this.labels = labels;
        }

        public Object getMilestone() {
            return milestone;
        }

        public void setMilestone(Object milestone) {
            this.milestone = milestone;
        }

        public boolean isDraft() {
            return draft;
        }

        public void setDraft(boolean draft) {
            this.draft = draft;
        }

        public String getCommits_url() {
            return commits_url;
        }

        public void setCommits_url(String commits_url) {
            this.commits_url = commits_url;
        }

        public String getReview_comments_url() {
            return review_comments_url;
        }

        public void setReview_comments_url(String review_comments_url) {
            this.review_comments_url = review_comments_url;
        }

        public String getReview_comment_url() {
            return review_comment_url;
        }

        public void setReview_comment_url(String review_comment_url) {
            this.review_comment_url = review_comment_url;
        }

        public String getComments_url() {
            return comments_url;
        }

        public void setComments_url(String comments_url) {
            this.comments_url = comments_url;
        }

        public String getStatuses_url() {
            return statuses_url;
        }

        public void setStatuses_url(String statuses_url) {
            this.statuses_url = statuses_url;
        }

        public Head getHead() {
            return head;
        }

        public void setHead(Head head) {
            this.head = head;
        }

        public Base getBase() {
            return base;
        }

        public void setBase(Base base) {
            this.base = base;
        }

        @Override
        public String toString() {
            return "PullRequest{" +
                    "url='" + url + '\'' +
                    ", id=" + id +
                    ", html_url='" + html_url + '\'' +
                    ", state='" + state + '\'' +
                    ", locked=" + locked +
                    ", title='" + title + '\'' +
                    ", user=" + user +
                    ", body='" + body + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", closed_at='" + closed_at + '\'' +
                    ", merged_at='" + merged_at + '\'' +
                    ", merge_commit_sha='" + merge_commit_sha + '\'' +
                    ", assignee=" + assignee +
                    ", assignees=" + java.util.Arrays.toString(assignees) +
                    ", requested_reviewers=" + java.util.Arrays.toString(requested_reviewers) +
                    ", requested_teams=" + java.util.Arrays.toString(requested_teams) +
                    ", labels=" + java.util.Arrays.toString(labels) +
                    ", milestone=" + milestone +
                    ", draft=" + draft +
                    ", commits_url='" + commits_url + '\'' +
                    ", review_comments_url='" + review_comments_url + '\'' +
                    ", review_comment_url='" + review_comment_url + '\'' +
                    ", comments_url='" + comments_url + '\'' +
                    ", statuses_url='" + statuses_url + '\'' +
                    ", head=" + head +
                    ", base=" + base +
                    '}';
        }

        public static class Head {
            private String label;
            private String ref;
            private String sha;


            public Head(String label, String ref, String sha) {
                this.label = label;
                this.ref = ref;
                this.sha = sha;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }


            @Override
            public String toString() {
                return "Head{" +
                        "label='" + label + '\'' +
                        ", ref='" + ref + '\'' +
                        ", sha='" + sha + '\'' +
                        '}';
            }
        }



        public static class Base {
            private String label;
            private String ref;
            private String sha;

            public Base(String label, String ref, String sha) {
                this.label = label;
                this.ref = ref;
                this.sha = sha;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            @Override
            public String toString() {
                return "Base{" +
                        "label='" + label + '\'' +
                        ", ref='" + ref + '\'' +
                        ", sha='" + sha + '\'' +
                        '}';
            }
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

    public static class Repository {
        private long id;
        private String statuses_url;
        private String name;
        private String full_name;
        @JsonProperty("private")
        private boolean isPrivate;
        private boolean allow_squash_merge;
        private boolean allow_merge_commit;
        private boolean allow_rebase_merge;
        private boolean allow_auto_merge;
        private boolean delete_branch_on_merge;
        private boolean allow_update_branch;
        private boolean use_squash_pr_title_as_default;
        private String squash_merge_commit_message;
        private String squash_merge_commit_title;
        private String merge_commit_message;
        private String merge_commit_title;

        public Repository(long id, String statuses_url, String name, String full_name, boolean isPrivate, boolean allow_squash_merge, boolean allow_merge_commit, boolean allow_rebase_merge, boolean allow_auto_merge, boolean delete_branch_on_merge, boolean allow_update_branch, boolean use_squash_pr_title_as_default, String squash_merge_commit_message, String squash_merge_commit_title, String merge_commit_message, String merge_commit_title) {
            this.id = id;
            this.statuses_url = statuses_url;
            this.name = name;
            this.full_name = full_name;
            this.isPrivate = isPrivate;
            this.allow_squash_merge = allow_squash_merge;
            this.allow_merge_commit = allow_merge_commit;
            this.allow_rebase_merge = allow_rebase_merge;
            this.allow_auto_merge = allow_auto_merge;
            this.delete_branch_on_merge = delete_branch_on_merge;
            this.allow_update_branch = allow_update_branch;
            this.use_squash_pr_title_as_default = use_squash_pr_title_as_default;
            this.squash_merge_commit_message = squash_merge_commit_message;
            this.squash_merge_commit_title = squash_merge_commit_title;
            this.merge_commit_message = merge_commit_message;
            this.merge_commit_title = merge_commit_title;
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

        public boolean isPrivate() {
            return isPrivate;
        }

        public void setPrivate(boolean aPrivate) {
            isPrivate = aPrivate;
        }

        public boolean isAllow_squash_merge() {
            return allow_squash_merge;
        }

        public void setAllow_squash_merge(boolean allow_squash_merge) {
            this.allow_squash_merge = allow_squash_merge;
        }

        public boolean isAllow_merge_commit() {
            return allow_merge_commit;
        }

        public void setAllow_merge_commit(boolean allow_merge_commit) {
            this.allow_merge_commit = allow_merge_commit;
        }

        public boolean isAllow_rebase_merge() {
            return allow_rebase_merge;
        }

        public void setAllow_rebase_merge(boolean allow_rebase_merge) {
            this.allow_rebase_merge = allow_rebase_merge;
        }

        public boolean isAllow_auto_merge() {
            return allow_auto_merge;
        }

        public void setAllow_auto_merge(boolean allow_auto_merge) {
            this.allow_auto_merge = allow_auto_merge;
        }

        public boolean isDelete_branch_on_merge() {
            return delete_branch_on_merge;
        }

        public void setDelete_branch_on_merge(boolean delete_branch_on_merge) {
            this.delete_branch_on_merge = delete_branch_on_merge;
        }

        public boolean isAllow_update_branch() {
            return allow_update_branch;
        }

        public void setAllow_update_branch(boolean allow_update_branch) {
            this.allow_update_branch = allow_update_branch;
        }

        public boolean isUse_squash_pr_title_as_default() {
            return use_squash_pr_title_as_default;
        }

        public void setUse_squash_pr_title_as_default(boolean use_squash_pr_title_as_default) {
            this.use_squash_pr_title_as_default = use_squash_pr_title_as_default;
        }

        public String getSquash_merge_commit_message() {
            return squash_merge_commit_message;
        }

        public void setSquash_merge_commit_message(String squash_merge_commit_message) {
            this.squash_merge_commit_message = squash_merge_commit_message;
        }

        public String getSquash_merge_commit_title() {
            return squash_merge_commit_title;
        }

        public void setSquash_merge_commit_title(String squash_merge_commit_title) {
            this.squash_merge_commit_title = squash_merge_commit_title;
        }

        public String getMerge_commit_message() {
            return merge_commit_message;
        }

        public void setMerge_commit_message(String merge_commit_message) {
            this.merge_commit_message = merge_commit_message;
        }

        public String getMerge_commit_title() {
            return merge_commit_title;
        }

        public void setMerge_commit_title(String merge_commit_title) {
            this.merge_commit_title = merge_commit_title;
        }

        @Override
        public String toString() {
            return "Repository{" +
                    "id=" + id +
                    ", statuses_url='" + statuses_url + '\'' +
                    ", name='" + name + '\'' +
                    ", full_name='" + full_name + '\'' +
                    ", isPrivate=" + isPrivate +
                    ", allow_squash_merge=" + allow_squash_merge +
                    ", allow_merge_commit=" + allow_merge_commit +
                    ", allow_rebase_merge=" + allow_rebase_merge +
                    ", allow_auto_merge=" + allow_auto_merge +
                    ", delete_branch_on_merge=" + delete_branch_on_merge +
                    ", allow_update_branch=" + allow_update_branch +
                    ", use_squash_pr_title_as_default=" + use_squash_pr_title_as_default +
                    ", squash_merge_commit_message='" + squash_merge_commit_message + '\'' +
                    ", squash_merge_commit_title='" + squash_merge_commit_title + '\'' +
                    ", merge_commit_message='" + merge_commit_message + '\'' +
                    ", merge_commit_title='" + merge_commit_title + '\'' +
                    '}';
        }
    }

    public static class Sender {
        private String login;
        private long id;
        private String type;
        private String user_view_type;
        private boolean site_admin;

        public Sender(String login, long id, String type, String user_view_type, boolean site_admin) {
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
            return "Sender{" +
                    "login='" + login + '\'' +
                    ", id=" + id +
                    ", type='" + type + '\'' +
                    ", user_view_type='" + user_view_type + '\'' +
                    ", site_admin=" + site_admin +
                    '}';
        }
    }
}
