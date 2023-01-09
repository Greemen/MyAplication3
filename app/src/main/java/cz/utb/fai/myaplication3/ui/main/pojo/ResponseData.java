package cz.utb.fai.myaplication3.ui.main.pojo;

import com.squareup.moshi.Json;

public class ResponseData {
    @Json(name = "translatedText")
    public String translatedText;
}
