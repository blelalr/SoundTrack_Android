package com.eros.soundtrack.enity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by eroschen on 2017/7/20.
 */

public class Track {
    @SerializedName("_id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("album")
    private String album;

    @SerializedName("albumId")
    private int albumId;

    @SerializedName("preview_url")
    private String priviewUrl;

    @SerializedName("youtube_id")
    private String youtubeId;

    @SerializedName("artist")
    private Artist artist;

    public String getArtistName() {
        return artist.getName();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getPriviewUrl() {
        return priviewUrl;
    }

    public void setPriviewUrl(String priviewUrl) {
        this.priviewUrl = priviewUrl;
    }
}
