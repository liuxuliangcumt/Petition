package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/25.
 */

public class PetitionCommentParam {
    public PetitionCommentParam(String appealCommentContent, int appealCommentLevel, int appealId) {
        this.appealCommentContent = appealCommentContent;
        this.appealCommentLevel = appealCommentLevel;
        this.appealId = appealId;
    }

    private String appealCommentContent;
    private int appealCommentLevel;
    private int appealId;

    public String getAppealCommentContent() {
        return appealCommentContent;
    }

    public void setAppealCommentContent(String appealCommentContent) {
        this.appealCommentContent = appealCommentContent;
    }

    public int getAppealCommentLevel() {
        return appealCommentLevel;
    }

    public void setAppealCommentLevel(int appealCommentLevel) {
        this.appealCommentLevel = appealCommentLevel;
    }

    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
    }
}
