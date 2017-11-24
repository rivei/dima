package it.polimi.giovanni.firstapp.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by giovanniquattrocchi on 09/11/17.
 */

public class Rating {

    @SerializedName("Source")
    @Expose
    private String source;

    @SerializedName("Value")
    @Expose
    private String val;

}
