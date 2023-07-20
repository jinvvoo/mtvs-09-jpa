package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedid;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_LIKE")
public class Like {

    @EmbeddedId
    private LikeCompositeKey likeInfo;

    protected Like() {}

    @Override
    public String toString() {
        return "Like{" +
                "likeInfo=" + likeInfo +
                '}';
    }

    public LikeCompositeKey getLikeInfo() {
        return likeInfo;
    }

    public Like(LikeCompositeKey likeInfo) {
        this.likeInfo = likeInfo;
    }
}
