package ke.co.forth.leaderboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ke.co.forth.leaderboard.adapters.LearningLeadersModel;
import ke.co.forth.leaderboard.adapters.RecyclerViewLearningLeadersAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LearningLeadersFragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    List<LearningLeadersModel> learningLeadersResponseData;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        recyclerView = v.findViewById(R.id.rvLearningLeaders);
        getLearningLeadersList();
        return v;
    }

    private void getLearningLeadersList() {
        ApiInterface apiInterface = Api.buildService(ApiInterface.class);
        Call<List<LearningLeadersModel>> learningLeadersRequest = apiInterface.getLearningLeadersList();

        learningLeadersRequest.enqueue(new Callback<List<LearningLeadersModel>>() {
            @Override
            public void onResponse(Call<List<LearningLeadersModel>> call, Response<List<LearningLeadersModel>> response) {
                learningLeadersResponseData = response.body();
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<List<LearningLeadersModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        RecyclerViewLearningLeadersAdapter adapter = new RecyclerViewLearningLeadersAdapter(getActivity(), learningLeadersResponseData);
        recyclerView.setAdapter(adapter); // set the Adapter to RecyclerView
    }
}