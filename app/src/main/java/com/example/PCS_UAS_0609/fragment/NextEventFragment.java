package com.example.PCS_UAS_0609.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.PCS_UAS_0609.R;
import com.example.PCS_UAS_0609.adapter.AdapterNextEvent;
import com.example.PCS_UAS_0609.httpclient.ApiInterface;
import com.example.PCS_UAS_0609.httpclient.retrofitclient;
import com.example.PCS_UAS_0609.model.ResponseNextEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NextEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NextEventFragment extends Fragment {

    String idLeague;

    @BindView(R.id.rvNextEvent)
    RecyclerView rvNextEvent;

    AdapterNextEvent adapter;
    ApiInterface apiInterface;
    public NextEventFragment() {
        // Required empty public constructor
    }


    public static NextEventFragment newInstance(String idLeague) {
        NextEventFragment fragment = new NextEventFragment();
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

        adapter=new AdapterNextEvent(getContext(),apiInterface);
        rvNextEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNextEvent.setAdapter(adapter);
        rvNextEvent.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        nextEvent();


        return view;
    }

    public void nextEvent(){
        Call<ResponseNextEvent> api=apiInterface.getNextEventByLeague(idLeague);

        api.enqueue(new Callback<ResponseNextEvent>() {
            @Override
            public void onResponse(Call<ResponseNextEvent> call, Response<ResponseNextEvent> response) {
                if (response.isSuccessful()){
                    adapter.setItems(response.body().getEvents());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseNextEvent> call, Throwable t) {

            }
        });

    }
}