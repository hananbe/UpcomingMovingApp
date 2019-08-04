package com.example.MoviesRateApp.Model;

import com.orm.SugarRecord;

public class MovieObj extends SugarRecord {
    private String cloudId;
    private String title;
    private String posterPath;
    private String releaseDate;
    private String voteAverage;
    private String overview;


    public MovieObj() {
    }

    public MovieObj(String cloudId, String title, String posterPath, String releaseDate,
                    String voteAverage, String overview) {

        this.cloudId = cloudId;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public String getCloudId() {
        return cloudId;
    }

    public void setCloudId(String id) {
        this.cloudId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String postar_path) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String release_date) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
