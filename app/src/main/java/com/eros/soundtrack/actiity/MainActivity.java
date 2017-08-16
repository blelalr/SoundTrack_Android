package com.eros.soundtrack.actiity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.eros.soundtrack.R;
import com.eros.soundtrack.adapter.ViewPagerAdapter;
import com.eros.soundtrack.fragment.SpannedGridViewFragment;
import com.eros.soundtrack.helper.SoundTrackInfo;
import com.eros.soundtrack.retrofit.APIHelper;
import com.eros.soundtrack.retrofit.APIResponse;

public class MainActivity extends AppCompatActivity implements APIResponse {

    private DrawerLayout mDrawerLayout;
    private APIHelper apiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        setUpHandler();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        callAPI();




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    private void callAPI() {

        apiHelper = new APIHelper(this);
        apiHelper.getPopularMovies(100, 0);
        apiHelper.getRecentMovies(100, 0);



//        GetPopularTask getPopularTask = new GetPopularTask(this);
//        getPopularTask.listener = this;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//            getPopularTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        else
//            getPopularTask.execute();
//
//        GetRecentTask getRecentTask = new GetRecentTask(this);
//        getRecentTask.listener = this;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//            getRecentTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        else
//            getRecentTask.execute();
//
//        GetAllTask getAllTask = new GetAllTask(this);
//        getAllTask.listener = this;
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//            getAllTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        else
//            getAllTask.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_night_mode_system:
                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case R.id.menu_night_mode_day:
                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.menu_night_mode_night:
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case R.id.menu_night_mode_auto:
                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SpannedGridViewFragment(SoundTrackInfo.getInstance().getPopularMovies()), "Popular");
        adapter.addFragment(new SpannedGridViewFragment(SoundTrackInfo.getInstance().getRecentMovies()), "Recent");
//        adapter.addFragment(new SpannedGridViewFragment(), "All");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void checkAllTaskisFinish() {
        if(SoundTrackInfo.getInstance().getPopularMovies().size() != 0 && SoundTrackInfo.getInstance().getRecentMovies().size() != 0 ) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }

    }

    @Override
    public void Success(int from) {
        Log.d("eros", "success call back");
        if(SoundTrackInfo.getInstance().getPopularMovies().size()!= 0 && SoundTrackInfo.getInstance().getRecentMovies().size()!= 0){
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void Failure(String message) {

    }
}
