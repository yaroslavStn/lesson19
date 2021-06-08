import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.run();

    }

    private void run() {

        try {
            google();
            gitHub();
            tinyUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tinyUrl() throws IOException {
        final TinyUrlService service = new Retrofit.Builder()
                .baseUrl("http://tiny-url.info/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TinyUrlService.class);

        Response<TinyUrlResponse> response = service.random("json", "http://tiny-url.info/open_api.html").execute();
        if (response != null && response.isSuccessful()) {
            System.out.println(response.body().shortUrl);
        } else if (response != null) {
            System.out.println(response.errorBody().string());
        }
    }

    private void gitHub() throws IOException {
        GitHubService service = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GitHubService.class);

        Response<String> response;
        response = service.listRepos("octocat").execute();
        if (response != null && response.isSuccessful()) {
            System.out.println(response.body());
        } else if (response != null) {
            System.out.println(response.errorBody().string());
        }
    }

    private void google() throws IOException {
        GoogleService service = new Retrofit.Builder()
                .baseUrl("https://google.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(GoogleService.class);

        Response<String> response;
        response = service.page().execute();

        if (response != null && response.isSuccessful()) {
            System.out.println(response.body());
        } else if (response != null) {
            System.out.println(response.errorBody().string());
        }
    }

}
