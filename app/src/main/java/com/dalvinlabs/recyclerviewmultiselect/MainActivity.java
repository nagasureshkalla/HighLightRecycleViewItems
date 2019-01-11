package com.dalvinlabs.recyclerviewmultiselect;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.dalvinlabs.recyclerviewmultiselect.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
  private final static String API_KEY = "apiKey";
  Call<List<Branches>> call;
  List<Branches> bran;
  private SelectionTracker<Long> selectionTracker;
  ProgressDialog progress;
  Adapter adapter;
  String[] st;
  RecyclerView recyclerView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

     progress = new ProgressDialog(this);
    progress.setMessage("Loading.... ");
    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progress.setCancelable(false);
    progress.setIndeterminate(true);
    progress.show();



    if (API_KEY.isEmpty()) {
      Toast.makeText(getApplicationContext(), "Please obtain your API KEY", Toast.LENGTH_LONG).show();
      return;
    }
     recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    call = apiService.getBran(API_KEY);
    call.enqueue(new Callback<List<Branches>>() {
      @Override
      public void onResponse(Call<List<Branches>> call, Response<List<Branches>> response) {
        bran=response.body();
        progress.setCancelable(true);
        progress.dismiss();
        adapter=new Adapter(bran);
        binding.recyclerView.setAdapter(adapter);
        selectionTracker = new SelectionTracker.Builder<>("my_selection",
                binding.recyclerView,
                new Adapter.KeyProvider(binding.recyclerView.getAdapter()),
                new Adapter.DetailsLookup(binding.recyclerView),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(new Adapter.Predicate())
                .build();
        adapter.setSelectionTracker(selectionTracker);
        Toast.makeText(getApplicationContext(), "Succesfully Loaded", Toast.LENGTH_SHORT).show();

       //Log.i("Branch ",getSelectedBranches)
      }

      @Override
      public void onFailure(Call<List<Branches>> call, Throwable t) {
        // Toast.makeText(Eamcet_Branches.this, "No Internet Connection", Toast.LENGTH_LONG).show();
        progress.setMessage("No Network Connection");
        progress.setCancelable(true);
      }
    });


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_selections) {

      Toast.makeText(this, selectionTracker.getSelection().toString(), Toast.LENGTH_LONG).show();

    }
    return super.onOptionsItemSelected(item);
  }
}
