package co.megusta.moshitest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import co.megusta.moshitest.viewmodels.CollectionViewModel;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CollectionViewModel viewModel;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView)findViewById(R.id.copy);

        viewModel = ViewModelProviders.of(this).get(CollectionViewModel.class);
        observerViewModel();
    }

    private void observerViewModel() {
        viewModel.getCollections().observe(this, collections -> {
            if(collections != null) {
                if(collections.size() > 0) name.setText(collections.get(1).name);
            }
        });

        viewModel.getApiErrorResponse().observe(this, errors -> {
            if(errors != null) {
                name.setText(errors.get(0).message);
            }
        });


        viewModel.getCollections().observe(this, collections -> {
            if(collections != null) {
                if(collections.size() > 0) name.setText(collections.get(1).name);
            }
        });


        viewModel.getError().observe(this, isError -> {
            if(isError) {
                name.setText("ERROR");
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if(isLoading) {
                name.setText("CARREGANDO");
            }
        });
    }
}