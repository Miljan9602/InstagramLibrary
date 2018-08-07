package com.example.instagramlib.response;

import com.example.instagramlib.model.InstagramLoggedUser;

import lombok.Getter;
import lombok.Setter;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramLoginResponse extends StatusResponse {

    @Setter
    @Getter
    private InstagramLoggedUser loggedUser;


    public InstagramLoginResponse(String status, int statusCode) {
        super(status, statusCode);
    }

    public InstagramLoginResponse(String status, int statusCode, String message) {
        super(status, statusCode, message);
    }

    public InstagramLoginResponse(String status, int statusCode, InstagramLoggedUser loggedUser) {
        super(status, statusCode);
        this.loggedUser = loggedUser;
    }
}
