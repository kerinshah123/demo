package com.example.demo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demo.PojoCategory;
import com.example.demo.R;
import com.example.demo.product_list;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    //ListView cat_List;
    RecyclerView cat_list;
    FirebaseFirestore CategoryDB;

    FirestoreRecyclerAdapter<PojoCategory, CategoryView> adapterCategory;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        cat_list = root.findViewById(R.id.cat_List);


        //CategoryAdapter categoryAdapter=new CategoryAdapter(getActivity().getApplicationContext());
        //cat_List.setAdapter(categoryAdapter);


        cat_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));


        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


        CategoryDB = FirebaseFirestore.getInstance();

        final Query Cat_query = CategoryDB.collection("category");

        FirestoreRecyclerOptions<PojoCategory> Cat_options = new FirestoreRecyclerOptions.Builder<PojoCategory>()
                .setQuery(Cat_query, PojoCategory.class)
                .build();


        adapterCategory = new FirestoreRecyclerAdapter<PojoCategory, CategoryView>(Cat_options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryView holder, int position, @NonNull PojoCategory model) {
                final String id = getSnapshots().getSnapshot(position).getId();

                Picasso.with(getActivity())
                        .load(model.getCategory_image_url())
                        .into(holder.cat1);
                holder.txtcat1.setText(model.getCategory_name());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), product_list.class);
                        intent.putExtra("id",id);
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public CategoryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylayout,parent,false);
                return new CategoryView(view);
            }
        };

        cat_list.setAdapter(adapterCategory);

        return root;
    }

    private class CategoryView extends RecyclerView.ViewHolder {

        ImageView cat1;
        TextView txtcat1;

        public CategoryView(@NonNull View itemView) {
            super(itemView);

            cat1 = itemView.findViewById(R.id.cat1);
            txtcat1 = itemView.findViewById(R.id.txtcat1);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        adapterCategory.startListening();

    }
    }
