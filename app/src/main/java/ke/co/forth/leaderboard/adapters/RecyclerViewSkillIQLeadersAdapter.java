package ke.co.forth.leaderboard.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ke.co.forth.leaderboard.R;

public class RecyclerViewSkillIQLeadersAdapter extends RecyclerView.Adapter<RecyclerViewSkillIQLeadersAdapter.ViewHolder>{
    public List<SkillIQLeadersModel> items;
    Context context;


    public RecyclerViewSkillIQLeadersAdapter(Context context, List<SkillIQLeadersModel> items){
        this.items = items;
        this.context = context;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public RecyclerViewSkillIQLeadersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_skill_iq_leaders, parent, false);

        return new RecyclerViewSkillIQLeadersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewSkillIQLeadersAdapter.ViewHolder holder, final int position) {
        final SkillIQLeadersModel model = items.get(position);
        holder.name.setText(model.getName());
        holder.score.setText(Integer.toString(model.getScore()).concat(" skill IQ Score, "));
        holder.country.setText(model.getCountry());
        Picasso.with(context)
                .load(model.getBadgeUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .resize(150,80)
                .into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView score;
        TextView country;
        ImageView badge;

        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.tvName);
            score = (TextView) view.findViewById(R.id.tvScore);
            country = (TextView) view.findViewById(R.id.tvCountry);
            badge = (ImageView) view.findViewById(R.id.imgViewBadge);
        }
    }
}
