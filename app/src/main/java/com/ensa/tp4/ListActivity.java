package com.ensa.tp4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensa.tp4.adapter.StarAdapter;
import com.ensa.tp4.beans.Star;
import com.ensa.tp4.service.StarService;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StarAdapter starAdapter;
    private StarService service;
    private static final String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        service = StarService.getInstance();
        init(); // Initialiser les étoiles

        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll()); // Récupérer les étoiles
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String shareText = "Partagez votre texte ici"; // Remplacez par le texte que vous souhaitez partager
            shareText(shareText);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareText(String textToShare) {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(textToShare)
                .getIntent();
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }


    public void init() {
        service.create(new Star("Lahlyal Ahmed Moubarak", "https://media.licdn.com/dms/image/v2/D4E03AQHSDvmcNequ6A/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1719955668336?e=1734566400&v=beta&t=vVq_wSw4rCVXG0vuEzJ0eo9HI8ZcsvlaRDixDVP6Il0", 5));
        service.create(new Star("Lebron James", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/LeBron_James_%2851959977144%29_%28cropped2%29.jpg/800px-LeBron_James_%2851959977144%29_%28cropped2%29.jpg", 4.5f));
        service.create(new Star("Stephen Curry", "https://cdn-anabb.nitrocdn.com/wKZSIgMUiAtfUfbozDdnDLTudNkeBKcT/assets/images/optimized/rev-e0782b2/terrain-basket.fr/wp-content/uploads/2022/10/Curry.jpg", 3));
        service.create(new Star("Giánnis Antetokoúnmpo", "https://encrypted-tbn3.gstatic.com/licensed-image?q=tbn:ANd9GcRKj4_5DLZdHEqguLw_oPBJ5HzLWAK18GK7BxpvIg-uCXIUMgHwI4H_WRpFgsLFR-aGQKR22D4Kzaoc-KQ", 4));
        service.create(new Star("kevin durant", "https://encrypted-tbn2.gstatic.com/licensed-image?q=tbn:ANd9GcQSJGc2FC90ISgJWLNifHxyikyRcj2r8zb1d8WGSNS7z880FDJVwntx2wftljGLaFBTgZ34WdRqYKC1hFw",4));
        service.create(new Star("Kyrie Irving", "https://fyooyzbm.filerobot.com/v7/12fde2a_afp-32mr88u-hefE3im6.jpg?vh=f0950a&ci_seal=da4f45a435&w=480&h=382&gravity=auto&func=crop", 2));




    }
}
