package com.example.PCS_UAS_0609.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PCS_UAS_0609.R;
import com.example.PCS_UAS_0609.adapter.AdapterTeam;
import com.example.PCS_UAS_0609.httpclient.ApiInterface;
import com.example.PCS_UAS_0609.httpclient.retrofitclient;
import com.example.PCS_UAS_0609.model.ResponseAllTeam;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamFragment extends Fragment {

    String idLeague;

    @BindView(R.id.rvNextEvent)
    RecyclerView rvNextEvent;

    AdapterTeam adapter;
    ApiInterface apiInterface;
    public TeamFragment() {
        // Required empty public constructor
    }


    public static TeamFragment newInstance(String idLeague) {
        TeamFragment fragment = new TeamFragment();
        Bundle args = new Bundle();
        args.putString("id", idLeague);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idLeague = getArguments().getString("id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_next_event, container, false);

        ButterKnife.bind(this,view);

        apiInterface= retrofitclient.getRetrofitClient().create(ApiInterface.class);

        adapter=new AdapterTeam(getContext(),apiInterface);
        rvNextEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNextEvent.setAdapter(adapter);
        rvNextEvent.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        nextEvent();


        return view;
    }

    public void nextEvent(){
        Call<ResponseAllTeam> api=apiInterface.getAllTeam(idLeague);

        api.enqueue(new Callback<ResponseAllTeam>() {
            @Override
            public void onResponse(Call<ResponseAllTeam> call, Response<ResponseAllTeam> response) {
                if (response.isSuccessful()){
                    adapter.setItems(response.body().getTeams());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseAllTeam> call, Throwable t) {

            }
        });

    }
}