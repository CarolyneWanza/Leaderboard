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

import ke.co.forth.leaderboard.adapters.RecyclerViewSkillIQLeadersAdapter;
import ke.co.forth.leaderboard.adapters.SkillIQLeadersModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillIQLeadersFragment extends Fragment {

    View v;
    RecyclerView recyclerView;
    List<SkillIQLeadersModel> skillIQLeadersResponseData;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        recyclerView = v.findViewById(R.id.rvSkillIQLeaders);
        getLearningLeadersList();
        return v;
    }

    private void getLearningLeadersList() {
        ApiInterface apiInterface = Api.buildService(ApiInterface.class);
        Call<List<SkillIQLeadersModel>> skillIQLeadersRequest = apiInterface.getSkillIQLeadersList();

        skillIQLeadersRequest.enqueue(new Callback<List<SkillIQLeadersModel>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeadersModel>> call, Response<List<SkillIQLeadersModel>> response) {
                skillIQLeadersResponseData = response.body();
                setDataInRecyclerView();
            }

            @Override
            public void onFailure(Call<List<SkillIQLeadersModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        RecyclerViewSkillIQLeadersAdapter adapter = new RecyclerViewSkillIQLeadersAdapter(getActivity(), skillIQLeadersResponseData);
        recyclerView.setAdapter(adapter); // set the Adapter to RecyclerView
    }
}