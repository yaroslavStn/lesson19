import retrofit2.Call;
import retrofit2.http.GET;

public interface GoogleService {
    @GET("/")
    Call<String> page();
}
