/**
 * Copyright (C) 2018 Rakita Miljan (rakitamiljan@yahoo.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.instagramlib;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.example.instagramlib.model.InstagramLoginPayload;
import com.example.instagramlib.model.payload.InstagramSyncFeaturesPayload;
import com.example.instagramlib.request.InstagramAutoCompleteUserListRequest;
import com.example.instagramlib.request.InstagramFetchHeadersRequest;
import com.example.instagramlib.request.InstagramGetInboxRequest;
import com.example.instagramlib.request.InstagramGetRecentActivityRequest;
import com.example.instagramlib.request.InstagramLoginRequest;
import com.example.instagramlib.request.InstagramRequest;
import com.example.instagramlib.request.InstagramSyncFeaturesRequest;
import com.example.instagramlib.response.InstagramLoginResponse;
import com.example.instagramlib.util.InstagramUtil;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static com.example.instagramlib.util.Constants.*;
import static com.example.instagramlib.util.InstagramConstants.*;


/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public class Instagram implements Serializable {

    private static final String TAG = "Instagram";

    @Getter
    public String username;

    @Getter
    protected String password;
    @Getter
    protected String uuid;
    @Getter
    protected String advertisingId;
    @Getter
    protected String deviceId;

    @Getter
    @Setter
    protected String userId;

    private final Set<Cookie> cookieStore = new HashSet();

    @Getter
    @Setter
    protected String rankToken;
    @Getter
    protected boolean isLoggedIn;

    @Getter
    @Setter
    protected Response lastResponse;

    @Getter
    protected OkHttpClient client;

    private HttpUrl url = new HttpUrl.Builder().scheme(HTTPS).host(INSTAGRAM_HOST).build();

    private Context context;

    /**
     *
     * @param username
     * @param password
     */
    public Instagram(Context context, String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Builder
    public Instagram(Context context, String username, String password, String uuid, String userId) {
        this.context = context;
        this.username = username;
        this.password = password;
        this.uuid = uuid;
        this.userId = userId;
    }


    /**
     * Setup before sending request. This has the be called everytime before login.
     */
    public void setup() {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException(ERROR_INVALID_CREDENTIALS);
        }

        if (context == null) {
            throw new NullPointerException(ERROR_NULLABLE_CONTEXT);
        }

        deviceId = InstagramUtil.generateDeviceId();

        if (StringUtils.isEmpty(uuid)) uuid = InstagramUtil.generateUuid(true);

        advertisingId = InstagramUtil.generateUuid(true);
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        this.client = (new OkHttpClient.Builder()).cookieJar(cookieJar).build();
    }


    public InstagramLoginResponse login() throws IOException {

        InstagramLoginPayload loginPayload = InstagramLoginPayload.builder().username(getUsername()).password(getPassword()).guid(getUuid()).device_id(this.deviceId).phone_id(InstagramUtil.generateUuid(true))._csrftoken(this.getOrFetchCsrf()).build();
        InstagramLoginResponse loginResponse = this.sendRequest(new InstagramLoginRequest(loginPayload));

        if (loginResponse != null && loginResponse.getStatus().equalsIgnoreCase(STATUS_OK)) {
            setUserId(loginResponse.getLoggedUser().getPk());
            setRankToken(getUserId() + "_" + getUuid());
            this.isLoggedIn = true;
            InstagramSyncFeaturesPayload syncFeatures = InstagramSyncFeaturesPayload.builder().uuid(getUuid()).csrftoken(this.getOrFetchCsrf()).uid(getUserId()).id(getUserId()).experiments("ig_android_progressive_jpeg,ig_creation_growth_holdout,ig_android_report_and_hide,ig_android_new_browser,ig_android_enable_share_to_whatsapp,ig_android_direct_drawing_in_quick_cam_universe,ig_android_huawei_app_badging,ig_android_universe_video_production,ig_android_asus_app_badging,ig_android_direct_plus_button,ig_android_ads_heatmap_overlay_universe,ig_android_http_stack_experiment_2016,ig_android_infinite_scrolling,ig_fbns_blocked,ig_android_white_out_universe,ig_android_full_people_card_in_user_list,ig_android_post_auto_retry_v7_21,ig_fbns_push,ig_android_feed_pill,ig_android_profile_link_iab,ig_explore_v3_us_holdout,ig_android_histogram_reporter,ig_android_anrwatchdog,ig_android_search_client_matching,ig_android_high_res_upload_2,ig_android_new_browser_pre_kitkat,ig_android_2fac,ig_android_grid_video_icon,ig_android_white_camera_universe,ig_android_disable_chroma_subsampling,ig_android_share_spinner,ig_android_explore_people_feed_icon,ig_explore_v3_android_universe,ig_android_media_favorites,ig_android_nux_holdout,ig_android_search_null_state,ig_android_react_native_notification_setting,ig_android_ads_indicator_change_universe,ig_android_video_loading_behavior,ig_android_black_camera_tab,liger_instagram_android_univ,ig_explore_v3_internal,ig_android_direct_emoji_picker,ig_android_prefetch_explore_delay_time,ig_android_business_insights_qe,ig_android_direct_media_size,ig_android_enable_client_share,ig_android_promoted_posts,ig_android_app_badging_holdout,ig_android_ads_cta_universe,ig_android_mini_inbox_2,ig_android_feed_reshare_button_nux,ig_android_boomerang_feed_attribution,ig_android_fbinvite_qe,ig_fbns_shared,ig_android_direct_full_width_media,ig_android_hscroll_profile_chaining,ig_android_feed_unit_footer,ig_android_media_tighten_space,ig_android_private_follow_request,ig_android_inline_gallery_backoff_hours_universe,ig_android_direct_thread_ui_rewrite,ig_android_rendering_controls,ig_android_ads_full_width_cta_universe,ig_video_max_duration_qe_preuniverse,ig_android_prefetch_explore_expire_time,ig_timestamp_public_test,ig_android_profile,ig_android_dv2_consistent_http_realtime_response,ig_android_enable_share_to_messenger,ig_explore_v3,ig_ranking_following,ig_android_pending_request_search_bar,ig_android_feed_ufi_redesign,ig_android_video_pause_logging_fix,ig_android_default_folder_to_camera,ig_android_video_stitching_7_23,ig_android_profanity_filter,ig_android_business_profile_qe,ig_android_search,ig_android_boomerang_entry,ig_android_inline_gallery_universe,ig_android_ads_overlay_design_universe,ig_android_options_app_invite,ig_android_view_count_decouple_likes_universe,ig_android_periodic_analytics_upload_v2,ig_android_feed_unit_hscroll_auto_advance,ig_peek_profile_photo_universe,ig_android_ads_holdout_universe,ig_android_prefetch_explore,ig_android_direct_bubble_icon,ig_video_use_sve_universe,ig_android_inline_gallery_no_backoff_on_launch_universe,ig_android_image_cache_multi_queue,ig_android_camera_nux,ig_android_immersive_viewer,ig_android_dense_feed_unit_cards,ig_android_sqlite_dev,ig_android_exoplayer,ig_android_add_to_last_post,ig_android_direct_public_threads,ig_android_prefetch_venue_in_composer,ig_android_bigger_share_button,ig_android_dv2_realtime_private_share,ig_android_non_square_first,ig_android_video_interleaved_v2,ig_android_follow_search_bar,ig_android_last_edits,ig_android_video_download_logging,ig_android_ads_loop_count_universe,ig_android_swipeable_filters_blacklist,ig_android_boomerang_layout_white_out_universe,ig_android_ads_carousel_multi_row_universe,ig_android_mentions_invite_v2,ig_android_direct_mention_qe,ig_android_following_follower_social_context").build();
            this.sendRequest(new InstagramSyncFeaturesRequest(syncFeatures));
            this.sendRequest(new InstagramAutoCompleteUserListRequest());
            this.sendRequest(new InstagramGetInboxRequest());
            this.sendRequest(new InstagramGetRecentActivityRequest());

        }

        return loginResponse;
    }

    /**
     * @return csrf token.
     * @throws IOException
     */
    public String getOrFetchCsrf() throws IOException {
        Cookie cookie = this.getCookie(CSRF_TOKEN);
        if (cookie == null) {
            this.sendRequest(new InstagramFetchHeadersRequest());
            cookie = this.getCookie(CSRF_TOKEN);
        }
        return cookie.value();
    }


    /**
     * Pulls out cookie when we hold in our cookiejar.
     *
     * @param cookieName name of the cookie we need to pull.
     * @return value for the cookieName.
     */
    public Cookie getCookie(String cookieName) {
        Iterator var2 = this.client.cookieJar().loadForRequest(url).iterator();

        Cookie cookie;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            cookie = (Cookie) var2.next();
        } while (!cookie.name().equalsIgnoreCase(cookieName));

        return cookie;
    }

    /**
     *
     * @param request which request our instagram client needs to send.
     * @param <T> Type of response which will depend on a request.
     * @return Request response.
     * @throws IOException
     */
    public <T> T sendRequest(InstagramRequest<T> request) throws IOException {

        if (!(request instanceof IInstagramVerification) && !this.isLoggedIn && request.requiresLogin()) {
            throw new IllegalStateException(ERROR_REQUIRES_LOGIN);
        } else {
            request.setInstagram(this);
            return request.execute();
        }
    }

}
