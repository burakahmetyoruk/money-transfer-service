package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BaseRequest;
import spark.Request;

public interface BaseController {

    default <T extends BaseRequest> T retrieveRequest(Request request, Class<T> clazz) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson.fromJson(request.body(), clazz);
    }
}