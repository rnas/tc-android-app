package brejas.com.br.brejas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import brejas.com.br.brejas.adapter.BeersListAdapter;
import brejas.com.br.brejas.database.BeersDatabase;
import brejas.com.br.brejas.helper.Constants;
import brejas.com.br.brejas.listener.OnClickListener;
import brejas.com.br.brejas.model.Beer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private BeersListAdapter adapter;

    // temp mock data
    List<Beer> beerArrayList = new ArrayList<Beer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        loadList();
    }

    void loadList() {

        getFromDatabase();

        adapter = new BeersListAdapter(this, beerArrayList, onClickListener());
        recyclerView.setAdapter(adapter);

    }

    void getFromDatabase() {
        BeersDatabase db = new BeersDatabase(getApplicationContext());
        beerArrayList = db.getItems();
        db.close();
    }

    private OnClickListener onClickListener () {
        return new OnClickListener() {
            @Override
            public void onClick(View v, int position) {

                Log.i("BR", "clicked");
                Log.i("BR", String.valueOf(position));

//                Intent i = new Intent(getContext(), DetalheActivity.class);
//                i.putExtra("carro",  adapter.getItem(position));
//                startActivity(i);

            }
        };
    }

    void initMock() {
        beerArrayList.add(new Beer("Heineken", "Heineken", "Bottle", 600, 2));
        beerArrayList.add(new Beer("Sol", "Heineken", "Bottle", 600, 10));
        beerArrayList.add(new Beer("Bavária", "Heineken", "Bottle", 1000, 2));
        beerArrayList.add(new Beer("Kaiser", "Heineken", "Pack", 320, 16));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add) {
            Intent intent = new Intent(this, NewItem.class);
            startActivity(intent);
        } else if (id == R.id.list_beers) {


        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, Credits.class);
            startActivity(intent);
        } else if (id == R.id.logout) {

            // Kills the logged user flag
            SharedPreferences sf = PreferenceManager.getDefaultSharedPreferences(this);
            sf.edit().putBoolean(Constants.KEEP_LOGGED, false).commit();

            Intent intent = new Intent(this, SplashScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
