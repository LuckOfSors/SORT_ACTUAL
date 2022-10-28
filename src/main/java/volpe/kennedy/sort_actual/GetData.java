package volpe.kennedy.sort_actual;

import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class GetData {
    HttpResponse<String> response = Unirest.get("https://movie-details1.p.rapidapi.com/imdb_api/movie?id=tt1375666")
            .header("X-RapidAPI-Key", "0da6c9c507msh27d66d057973f0ep13289ajsnc1cd4d8defc8")
            .header("X-RapidAPI-Host", "movie-details1.p.rapidapi.com")
            .asString();


    public GetData() throws UnirestException {
    }
}
