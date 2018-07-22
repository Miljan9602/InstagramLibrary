package com.example.instagramlib.model.payload;

import lombok.Builder;
import lombok.Getter;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramSyncFeaturesPayload {
    @Getter
    private String uuid;
    @Getter
    private String uid;
    @Getter
    private String id;
    @Getter
    private String csrftoken;
    @Getter
    private String experiments;

    @Builder
    public InstagramSyncFeaturesPayload(String uuid, String uid, String id, String csrftoken, String experiments) {
        this.uuid = uuid;
        this.uid = uid;
        this.id = id;
        this.csrftoken = csrftoken;
        this.experiments = experiments;
    }
}
