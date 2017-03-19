package brejas.com.br.brejas.api;

import java.util.List;

import brejas.com.br.brejas.model.User;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by rnas on 18/03/17.
 */

public interface UserAPI {

    @GET("/v2/58b9b1740f0000b614f09d2f")
    Call<User> getUser();

}
