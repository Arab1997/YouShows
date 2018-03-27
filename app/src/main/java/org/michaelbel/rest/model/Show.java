package org.michaelbel.rest.model;

import com.google.gson.annotations.SerializedName;

import org.michaelbel.rest.TmdbObject;

import java.io.Serializable;

/**
 * Date: 27 MAR 2018
 * Time: 20:53 MSK
 *
 * @author Michael Bel
 */

public class Show extends TmdbObject implements Serializable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("popularity")
    public float popularity;

    @SerializedName("vote_average")
    public float voteAverage;

    @SerializedName("overview")
    public String overview;

    @SerializedName("first_air_date")
    public String airDate;

    //@SerializedName("origin_country")

    //@SerializedName("genre_ids")

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("vote_count")
    public int voteCount;

    @SerializedName("original_name")
    public String originalName;
}