package com.eros.soundtrack.enity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by eroschen on 2017/7/20.
 */

public class Track implements Serializable {
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

    private String thumbnailURL;
    private String duration;
    private String viewCount;

    public String getArtistName() {
        return artist.getName();
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnail) {
        this.thumbnailURL = thumbnail;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "YouTubeVideo {" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
