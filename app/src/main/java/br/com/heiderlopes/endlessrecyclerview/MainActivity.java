package br.com.heiderlopes.endlessrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.heiderlopes.endlessrecyclerview.adapter.CarroAdapter;
import br.com.heiderlopes.endlessrecyclerview.listener.OnLoadMoreListener;
import br.com.heiderlopes.endlessrecyclerview.model.Carro;

public class MainActivity extends AppCompatActivity {

    private TextView tvEmptyView;
    private RecyclerView mRecyclerView;
    private CarroAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private List<Carro> carroList;


    protected Handler handler;

    private int paginaAtual = 1;
    private final int LIMITE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        carroList = new ArrayList<>();
        handler = new Handler();

        loadData(paginaAtual, LIMITE);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CarroAdapter(carroList, mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);

        if (carroList.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);

        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
        }

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                carroList.add(null);
                mAdapter.notifyItemInserted(carroList.size() - 1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        carroList.remove(carroList.size() - 1);
                        mAdapter.notifyItemRemoved(carroList.size());

                        loadData(paginaAtual, LIMITE);
                        mAdapter.notifyItemInserted(carroList.size());
                        mAdapter.setLoaded();
                    }
                }, 2000);
            }
        });
    }

    private void loadData(int page, int limit) {
        int aux = (page-1) * limit;
        for (int i = 1; i <= limit; i++) {
            carroList.add(new Carro("Carro " + (aux + i), "Fabricante" + (aux + i)));
        }
        paginaAtual++;
    }
}
