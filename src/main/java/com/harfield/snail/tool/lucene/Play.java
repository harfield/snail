package com.harfield.snail.tool.lucene;

/**
 * Created by harfield on 2017/2/3.
 */
public class Play {
    private long playId;
    private String playUrl;
    private String playUrlMd5;
    private String albumUrl;
    private String albumUrlMd5;
    private String playTitle;

    public long getPlayId() {
        return playId;
    }

    public void setPlayId(long playId) {
        this.playId = playId;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getPlayUrlMd5() {
        return playUrlMd5;
    }

    public void setPlayUrlMd5(String playUrlMd5) {
        this.playUrlMd5 = playUrlMd5;
    }

    public String getAlbumUrl() {
        return albumUrl;
    }

    public void setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
    }

    public String getAlbumUrlMd5() {
        return albumUrlMd5;
    }

    public void setAlbumUrlMd5(String albumUrlMd5) {
        this.albumUrlMd5 = albumUrlMd5;
    }

    public String getPlayTitle() {
        return playTitle;
    }

    public void setPlayTitle(String playTitle) {
        this.playTitle = playTitle;
    }

    @Override
    public String toString() {
        return "Play{" +
                "playId=" + playId +
                ", playUrl='" + playUrl + '\'' +
                ", playUrlMd5='" + playUrlMd5 + '\'' +
                ", albumUrl='" + albumUrl + '\'' +
                ", albumUrlMd5='" + albumUrlMd5 + '\'' +
                ", playTitle='" + playTitle + '\'' +
                '}';
    }
}
