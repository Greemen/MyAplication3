package cz.utb.fai.myaplication3.ui.main.pojo;

import com.squareup.moshi.Json;

public class ApiResponse {
    @Json(name = "responseData")
    public ResponseData responseData;
}
