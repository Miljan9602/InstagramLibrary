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

package com.example.instagramlib.util;

import org.apache.commons.codec.binary.Hex;

import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.SneakyThrows;

/**
 * InstagramLib API
 *
 * @author Miljan Rakita on 7/18/18.
 * rakitamiljan@yahoo.com
 */
public class InstagramUtil {


    /**
     * Generate a Hmac SHA-256 hash
     * @param key key
     * @param string value
     * @return hashed value of string with key
     */
    public static String generateHash(String key, String string) {
        SecretKeySpec object = new SecretKeySpec(key.getBytes(), Constants.HASH_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(Constants.HASH_ALGORITHM);
            mac.init((Key) object);
            byte[] byteArray = mac.doFinal(string.getBytes(Constants.CHARSET_NAME));
            return new String(new Hex().encode(byteArray), Constants.STRING_CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static String generateSignature(String payload) {
        String parsedData = URLEncoder.encode(payload, Constants.ENCODING);
        String signedBody = generateHash(InstagramConstants.API_KEY, payload);
        return "ig_sig_key_version=" + InstagramConstants.API_KEY_VERSION + "&signed_body=" + signedBody + '.'
                + parsedData;
    }

    public static String generateUuid(boolean dash) {
        String uuid;
        int random[] = new int[8];

        random[0] = random_int(0, 0xffff);
        random[1] = random_int(0, 0xffff);
        random[2] = random_int(0, 0xffff);
        random[3] = random_int(0, 0xffff) | 0x4000;
        random[4] = random_int(0, 0xffff) | 0x8000;
        random[5] = random_int(0, 0xffff);
        random[6] = random_int(0, 0xffff);
        random[7] = random_int(0, 0xffff);


        uuid = Integer.toHexString(random[0]) + "" + Integer.toHexString(random[1]) + "-"
                + Integer.toHexString(random[2]) + "-" + Integer.toHexString(random[3]) + "-"
                + Integer.toHexString(random[4]) + "-" + Integer.toHexString(random[5]) + ""
                + Integer.toHexString(random[6]) + "" + Integer.toHexString(random[7]);
        return dash ? uuid : uuid.replaceAll("-", "");
    }

    public static int random_int(int Min, int Max) {
        return (int) (Math.random() * (Max - Min)) + Min;
    }
}
