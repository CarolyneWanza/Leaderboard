package ke.co.forth.leaderboard;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiPost {

        private static Retrofit retrofit;
        private static final String BASE_URL = "https://docs.google.com/forms/d/e/";

        public static Retrofit getRetrofit() {
            if (retrofit == null) {
                retrofit = new retrofit2.Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
}
