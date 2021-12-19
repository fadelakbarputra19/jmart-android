package com.fadelJmartPK.jmart_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.SearchView;

/**
 * Activity untuk di main activity, dapat ke search, invoice personal, create product, dan about me
 * @author Muhammad Fadel Akbar Putra
 */
public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MenuItem search;
    private SearchView searchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_atas, menu);

        search = menu.findItem(R.id.search_button);
        searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("Butuh apa hari ini?");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                fragment1.listViewAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.productTab);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new fragment1(), "Products");
        vpAdapter.addFragment(new fragment2(), "Filter");
        viewPager.setAdapter(vpAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_button) {
            Toast.makeText(this, "Search Selected", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.add_button) {
            Toast.makeText(this, "Search Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, CreateProductActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.account_button) {
            Toast.makeText(this, "Account Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.history_personal_button){
            Toast.makeText(this, "History Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, PersonalHistory.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}