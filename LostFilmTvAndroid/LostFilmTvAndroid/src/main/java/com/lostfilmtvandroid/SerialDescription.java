package com.lostfilmtvandroid;

/**
 * Created by veinhorn on 26.4.14.
 */
public class SerialDescription extends Serial {
    private String posterUrl = "";
    private String year = "";
    private String genres = "";
    private String numberOfSeasons = "";
    private String description = "";
    private String country = "";
    private String status = "";
    private String officialPage = "";

    public SerialDescription() {

    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOfficialPage() {
        return officialPage;
    }

    public void setOfficialPage(String officialPage) {
        this.officialPage = officialPage;
    }

    @Override
    public String toString() {
        return title + "\n" + originalTitle + "\n" + pageUrl + "\n" +
                posterUrl + "\n" + year + "\n" + genres + "\n" + numberOfSeasons + "\n" +
                description + "\n" + country + "\n" + status + "\n" + officialPage;
    }
}
