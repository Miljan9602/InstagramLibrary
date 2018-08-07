package com.example.instagramlib.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/19/18.
 * rakitamiljan@yahoo.com
 */

@Getter
@ToString
public class InstagramLoggedUser {

    private String pk;
    private boolean canSeeOrganicInsights;
    private boolean isBusiness;
    private boolean showInsightsTerms;
    private String username;
    private boolean hasAnonymousProfilePicture;
    private String fullName;
    private boolean isVerified;
    private boolean isPrivate;
    private boolean canBoostPost;
    private String profilePicUrl;
    private boolean allowContactsSync;
    private String fbuid;
    private String phoneNumber;
    private String countryCode;
    private String nationalNumber;

    private InstagramLoggedUser(String username, String pk, String fullName) {
        this.username = username;
        this.pk = pk;
        this.fullName = fullName;
    }

    public static class Builder {

        InstagramLoggedUser loggedUser;

        public Builder(String username, String pk, String fullName) {
            loggedUser = new InstagramLoggedUser(username, pk, fullName);
        }


        public Builder canSeeOrganicInsights(boolean canSeeOrganicInsights) {
            loggedUser.canSeeOrganicInsights = canSeeOrganicInsights;
            return this;
        }

        public Builder isBusiness(boolean isBusiness) {
            loggedUser.isBusiness = isBusiness;
            return this;
        }


        public Builder showInsightsTerms(boolean showInsightsTerms) {
            loggedUser.showInsightsTerms = showInsightsTerms;
            return this;
        }

        public Builder hasAnonymousProfilePicture(boolean hasAnonymousProfilePicture) {
            loggedUser.hasAnonymousProfilePicture = hasAnonymousProfilePicture;
            return this;
        }

        public Builder canBoostPost(boolean canBoostPost) {
            loggedUser.canBoostPost = canBoostPost;
            return this;
        }

        public Builder isVerified(boolean isVerified) {
            loggedUser.isVerified = isVerified;
            return this;
        }

        public Builder isPrivate(boolean isPrivate) {
            loggedUser.isPrivate = isPrivate;
            return this;
        }

        public Builder profilePicUrl(String profilePicUrl) {
            loggedUser.profilePicUrl = profilePicUrl;
            return this;
        }

        public Builder allowContactsSync(boolean allowContactsSync) {
            loggedUser.allowContactsSync = allowContactsSync;
            return this;
        }

        public Builder fbuid(String fbuid) {
            loggedUser.fbuid = fbuid;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            loggedUser.phoneNumber = phoneNumber;
            return this;
        }

        public Builder countryCode(String countryCode) {
            loggedUser.countryCode = countryCode;
            return this;
        }

        public Builder nationalNumber(String nationalNumber) {
            loggedUser.nationalNumber = nationalNumber;
            return this;
        }

        public InstagramLoggedUser build() {
            return loggedUser;
        }
    }
}
