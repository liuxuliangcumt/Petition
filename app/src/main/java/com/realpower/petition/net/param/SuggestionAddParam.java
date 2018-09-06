package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/20.
 */

public class SuggestionAddParam {

    /**
     * suggestionContactInformation : string 手机号
     * suggestionInfo : string
     * suggestionTitle : string
     * suggestionVideoUrl : string
     * suggestionVoiceUrl : string
     * suggestionImageUrl : string
     */

    private String suggestionInfo;
    private String suggestionTitle;
    private String suggestionVideoUrl;
    private String suggestionVoiceUrl;
    private String suggestionContactInformation;
    private String suggestionImageUrl;

    public SuggestionAddParam(String suggestionTitle, String suggestionInfo,
                              String suggestionContactInformation, String suggestionImageUrl,
                              String suggestionVideoUrl, String suggestionVoiceUrl
    ) {
        this.suggestionInfo = suggestionInfo;
        this.suggestionTitle = suggestionTitle;
        this.suggestionVideoUrl = suggestionVideoUrl;
        this.suggestionVoiceUrl = suggestionVoiceUrl;
        this.suggestionContactInformation = suggestionContactInformation;
        this.suggestionImageUrl = suggestionImageUrl;
    }

    public String getSuggestionInfo() {
        return suggestionInfo;
    }

    public void setSuggestionInfo(String suggestionInfo) {
        this.suggestionInfo = suggestionInfo;
    }

    public String getSuggestionTitle() {
        return suggestionTitle;
    }

    public void setSuggestionTitle(String suggestionTitle) {
        this.suggestionTitle = suggestionTitle;
    }

    public String getSuggestionVideoUrl() {
        return suggestionVideoUrl;
    }

    public void setSuggestionVideoUrl(String suggestionVideoUrl) {
        this.suggestionVideoUrl = suggestionVideoUrl;
    }

    public String getSuggestionVoiceUrl() {
        return suggestionVoiceUrl;
    }

    public void setSuggestionVoiceUrl(String suggestionVoiceUrl) {
        this.suggestionVoiceUrl = suggestionVoiceUrl;
    }

    public String getSuggestionContactInformation() {
        return suggestionContactInformation;
    }

    public void setSuggestionContactInformation(String suggestionContactInformation) {
        this.suggestionContactInformation = suggestionContactInformation;
    }

    public String getSuggestionImageUrl() {
        return suggestionImageUrl;
    }

    public void setSuggestionImageUrl(String suggestionImageUrl) {
        this.suggestionImageUrl = suggestionImageUrl;
    }
}
