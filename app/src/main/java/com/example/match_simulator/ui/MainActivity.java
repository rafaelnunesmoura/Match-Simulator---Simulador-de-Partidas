package com.example.match_simulator.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.match_simulator.Domain.Match;
import com.example.match_simulator.data.MatchesApi;
import com.example.match_simulator.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MatchesApi matchesApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setupMatchesList();
        setupMatchesRefresh();
        setupFloatingActionButton();

    }

    private void setupHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rafaelnunesmoura.github.io/Matches-Simulator-Api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        matchesApi = retrofit.create(MatchesApi.class);
    }

    private void setupMatchesList() {
       matchesApi.getMatches().enqueue(new Callback<List<Match>>() {
           @Override
           public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
               if (response.isSuccessful()){
                   List<Match> matches = response.body();
                   Log.i("simulador","tudo certo = partidas = " + matches.size());
               }else{
                   showErrorMessage();
               }
           }

           @Override
           public void onFailure(Call<List<Match>> call, Throwable t) {
               showErrorMessage();

           }
       });
    }



    private void setupMatchesRefresh() {
        //TODO atualizar as partidas, na ação de Swipe
    }

    private void setupFloatingActionButton() {
        // TODO: Criar evento de CLick e simulação de partidas
    }


    private void showErrorMessage() {
        Snackbar.make(binding.fabSimulate, "Error API", Snackbar.LENGTH_LONG).show();
    }
}
