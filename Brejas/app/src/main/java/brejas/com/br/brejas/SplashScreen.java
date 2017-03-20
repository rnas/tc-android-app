package brejas.com.br.brejas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import brejas.com.br.brejas.api.UserAPI;
import brejas.com.br.brejas.helper.Constants;
import brejas.com.br.brejas.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static brejas.com.br.brejas.helper.Constants.*;

public class SplashScreen extends AppCompatActivity implements Callback<User>, Animation.AnimationListener {

    Animation animacao;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        icon = (ImageView) findViewById(R.id.icon);

        animacao = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.splash);

        icon.startAnimation(animacao);
        animacao.setAnimationListener(this);

        loadAPIUsers();
    }

    void loadAPIUsers() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAPI api = retrofit.create(UserAPI.class);
        Call<User> call = api.getUser();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {

        Log.i("BR", "server response");
        Log.i("BR", response.body().getUser());

        if (isLogged()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("master_user", response.body());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public boolean isLogged() {
        SharedPreferences sf = PreferenceManager.getDefaultSharedPreferences(this);
        return sf.getBoolean(Constants.KEEP_LOGGED, false);
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Toast.makeText(this, R.string.err_server_connection, Toast.LENGTH_SHORT).show();
    }
}
