package top.riversunny.mvp_rxretrofit.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;

import top.riversunny.mvp_rxretrofit.R;
import top.riversunny.mvp_rxretrofit.ui.fragment.TopicsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private TopicsFragment mNewestTopicsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置 toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.navigation_drawer_menu_item0);

        // 设置 navigation drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        /*设定NavigationView菜单的选择事件*/
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (mNewestTopicsFragment == null) {
            mNewestTopicsFragment = new TopicsFragment();
            fragmentTransaction.replace(R.id.container, mNewestTopicsFragment);

        } else {
            fragmentTransaction.show(mNewestTopicsFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        getSupportActionBar().setTitle(item.getTitle());
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (id == R.id.nav_news){
            if (mNewestTopicsFragment == null) {
                mNewestTopicsFragment = new TopicsFragment();
                fragmentTransaction.replace(R.id.container, mNewestTopicsFragment);

            } else {
                fragmentTransaction.show(mNewestTopicsFragment);
            }
            fragmentTransaction.commit();
        }else if (id == R.id.nav_hot){

        }
        /*关闭侧滑菜单*/
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
