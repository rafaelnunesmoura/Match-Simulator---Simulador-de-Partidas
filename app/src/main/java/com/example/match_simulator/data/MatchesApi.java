package com.example.match_simulator.data;

import com.example.match_simulator.domain.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesApi {
    @GET("matches.json")
    Call<List<Match>> getMatches();
}
