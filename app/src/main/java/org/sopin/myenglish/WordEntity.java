package org.sopin.myenglish;

import java.util.Date;

/**
 * Created by konstantin on 7/20/15.
 */
public class WordEntity {

    private Integer id;
    private String word;
    private String translate;
    private Boolean isPhrase;
    private Integer level;
    private Boolean learnt;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public Boolean getIsPhrase() {
        return isPhrase;
    }

    public void setIsPhrase(Boolean isPhrase) {
        this.isPhrase = isPhrase;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getLearnt() {
        return learnt;
    }

    public void setLearnt(Boolean learnt) {
        this.learnt = learnt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
