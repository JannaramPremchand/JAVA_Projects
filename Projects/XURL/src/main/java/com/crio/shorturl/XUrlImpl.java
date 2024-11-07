package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;

public class XUrlImpl implements XUrl {

    private final String SHORT_URL_PREFIX = "http://short.url/";

    private Map<String, String> longToShortUrlMap;
    private Map<String, Integer> hitCountMap;

    public XUrlImpl() {
        this.longToShortUrlMap = new HashMap<>();
        this.hitCountMap = new HashMap<>();
    }

    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_PREFIX);
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 9; i++) {
            int index = (int) (Math.random() * characters.length());
            shortUrl.append(characters.charAt(index));
        }
        return shortUrl.toString();
    }

    @Override
    public String registerNewUrl(String longUrl) {
        if (longToShortUrlMap.containsKey(longUrl)) {
            return longToShortUrlMap.get(longUrl);
        }

        String shortUrl = generateShortUrl();
        longToShortUrlMap.put(longUrl, shortUrl);
        hitCountMap.put(longUrl, 0);
        return shortUrl;
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if (longToShortUrlMap.containsValue(shortUrl)) {
            return null;
        }

        longToShortUrlMap.put(longUrl, shortUrl);
        hitCountMap.put(longUrl, 0);
        return shortUrl;
    }

    @Override
    public String getUrl(String shortUrl) {
        for (Map.Entry<String, String> entry : longToShortUrlMap.entrySet()) {
            if (entry.getValue().equals(shortUrl)) {
                hitCountMap.put(entry.getKey(), hitCountMap.getOrDefault(entry.getKey(), 0) + 1);
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        return hitCountMap.getOrDefault(longUrl, 0);
    }

    @Override
    public String delete(String longUrl) {
        String shortUrl = longToShortUrlMap.remove(longUrl);
        return shortUrl;
    }
}
