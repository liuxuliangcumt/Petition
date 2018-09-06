package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/26.
 */

public class SugestionCommentParam {

    public SugestionCommentParam(String suggestionCommentContent, int suggestionCommentLevel, int suggestionId) {
        this.suggestionCommentContent = suggestionCommentContent;
        this.suggestionCommentLevel = suggestionCommentLevel;
        this.suggestionId = suggestionId;
    }

    private String suggestionCommentContent;
    private int suggestionCommentLevel;
    private int suggestionId;

    public String getSuggestionCommentContent() {
        return suggestionCommentContent;
    }

    public void setSuggestionCommentContent(String suggestionCommentContent) {
        this.suggestionCommentContent = suggestionCommentContent;
    }

    public int getSuggestionCommentLevel() {
        return suggestionCommentLevel;
    }

    public void setSuggestionCommentLevel(int suggestionCommentLevel) {
        this.suggestionCommentLevel = suggestionCommentLevel;
    }

    public int getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(int suggestionId) {
        this.suggestionId = suggestionId;
    }
}
