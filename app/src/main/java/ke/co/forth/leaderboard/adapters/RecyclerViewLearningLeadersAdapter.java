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

public class RecyclerViewLearningLeadersAdapter extends RecyclerView.Adapter<RecyclerViewLearningLeadersAdapter.ViewHolder>{
    public List<LearningLeadersModel> items;
    Context context;

    public RecyclerViewLearningLeadersAdapter(Context context, List<LearningLeadersModel> items){
        this.items = items;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewLearningLeadersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_learning_leaders, parent, false);

        return new RecyclerViewLearningLeadersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final LearningLeadersModel model = items.get(position);
        holder.name.setText(model.getName());
        holder.hours.setText(Integer.toString(model.getHours()).concat(" learning hours, "));
        holder.country.setText(model.getCountry());
        Picasso.with(context)
                .load(model.getBadgeUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .resize(100,100)
                .into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView hours;
        TextView country;
        ImageView badge;

        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.tvName);
            hours = (TextView) view.findViewById(R.id.tvHours);
            country = (TextView) view.findViewById(R.id.tvCountry);
            badge = (ImageView) view.findViewById(R.id.imgViewBadge);
        }
    }
}
