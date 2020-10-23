package com.example.family_tree.Adaptors;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.family_tree.Activities.MainActivity;
import com.example.family_tree.Address;
import com.example.family_tree.DetailDump;
import com.example.family_tree.Person;
import com.example.family_tree.R;
import com.google.android.material.textfield.TextInputEditText;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonAdaptor extends RecyclerView.Adapter<PersonAdaptor.MyViewHolder> implements Filterable {
    private ArrayList<Person> mDataset;
    private MainActivity mainActivity;
    private List<Person> mDatasetFiltered;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mDatasetFiltered = mDataset;
                } else {
                    List<Person> filteredList = new ArrayList<>();
                    for (Person person : mDataset) {
                        if (person.getFirstName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(person);
                        }
                        List<Person> children = person.getChildren();
                        for (Person child : children) {
                            if (child.getFirstName().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(person);
                            }
                        }
                    }
                    mDatasetFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDatasetFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDatasetFiltered = (ArrayList<Person>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView personName;
        private LinearLayout subItem;
        private TextInputEditText streetNumber;
        private TextInputEditText streetName;
        private TextInputEditText town;
        private TextInputEditText state;
        private TextInputEditText zipcode;
        private TextView descendants;
        private Button addRelativeButton;

        private MainActivity mainActivity;

        private PersonAdaptor personAdaptor;

        public MyViewHolder(@NonNull View itemView, MainActivity mainActivity, PersonAdaptor personAdaptor) {
            super(itemView);
            this.itemView = itemView;
            personName = itemView.findViewById(R.id.person_name);
            subItem = itemView.findViewById(R.id.sub_item);
            streetNumber = itemView.findViewById(R.id.person_street_number);
            streetName = itemView.findViewById(R.id.person_street_name);
            town = itemView.findViewById(R.id.person_town);
            state = itemView.findViewById(R.id.person_state);
            zipcode = itemView.findViewById(R.id.person_zipcode);
            descendants = itemView.findViewById(R.id.person_descendants);
            addRelativeButton = itemView.findViewById(R.id.add_relationship);

            this.mainActivity = mainActivity;

            this.personAdaptor = personAdaptor;
        }

        private void bind(Person person) {
            boolean expanded = person.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);

            String name = person.getFirstName() + " " + person.getLastName();
            personName.setText(name);
            Address address = person.getAddress();
            streetNumber.setText(address.getStreetNumber());
            configureTextEdit(streetNumber, "streetNumber");
            streetName.setText(address.getStreetName());
            configureTextEdit(streetName, "streetName");
            town.setText(address.getTownCity());
            configureTextEdit(town, "townCity");
            state.setText(address.getState());
            configureTextEdit(state, "state");
            zipcode.setText(address.getZipcode());
            configureTextEdit(zipcode, "zipcode");

            ArrayList<Person> children = person.getChildren();
            if (children.size() > 0) {
                String descendantsStr = "";
                for (Person p : children) {
                    descendantsStr = descendantsStr + p.toString() + ", ";
                }
                descendantsStr = descendantsStr.substring(0, descendantsStr.length() - 1);
                descendants.setText(descendantsStr);
            } else {
                descendants.setText("No children.");
            }

            addRelativeButton.setOnClickListener(v -> {
                int position = getAdapterPosition();
                mainActivity.toggleHomeFragmentToAddPersonFragment(true, position);
            });
        }

        private void configureTextEdit(TextInputEditText textInputEditText, String field) {
            textInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || event != null
                            && event.getAction() == KeyEvent.ACTION_DOWN
                            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        if (event == null || !event.isShiftPressed()) {
                            // the user is done typing
                            switch (field) {
                                case "streetNumber":
                                    DetailDump.setAddressStreetNumber(getAdapterPosition(), v.getText().toString());
                                    break;
                                case "streetName":
                                    DetailDump.setAddressStreetName(getAdapterPosition(), v.getText().toString());
                                    break;
                                case "townCity":
                                    DetailDump.setAddressTownCity(getAdapterPosition(), v.getText().toString());
                                    break;
                                case "state":
                                    DetailDump.setAddressState(getAdapterPosition(), v.getText().toString());
                                    break;
                                case "country":
                                    DetailDump.setAddressCountry(getAdapterPosition(), v.getText().toString());
                                    break;
                                case "zipcode":
                                    DetailDump.setAddressZipcode(getAdapterPosition(), v.getText().toString());
                                    break;
                            }

                            personAdaptor.notifyItemChanged(getAdapterPosition());
                            return true; // consume
                        }
                    }
                    return false;
                }
            });
        }
    }

    public PersonAdaptor(ArrayList<Person> mDataset, MainActivity mainActivity) {
        this.mDataset = mDataset;
        this.mDatasetFiltered = mDataset;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public PersonAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptor_person_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v, mainActivity, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdaptor.MyViewHolder holder, int position) {
        //Person person = mDataset.get(position);
        Person person = mDatasetFiltered.get(position);
        holder.bind(person);

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = person.isExpanded();
            person.setExpanded(!expanded);
            notifyItemChanged(position);
//            boolean show = toggleLayout(!person.isExpanded(), v, holder.subItem);
//            person.setExpanded(show);
//            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        //return mDataset.size();
        return mDatasetFiltered.size();
    }

    public void add(Person person) {
        int position = person.getFutureRelativePosition();
        if (position != -1) {
            String relationship = person.getFutureRelativeRelationship();
            switch (relationship) {
                case "Parent":
                    person.addChild(mDataset.get(position));
                    break;
                case "Child":
                    mDataset.get(position).addChild(person);
                    break;
            }
            notifyItemChanged(position);
        }
        DetailDump.addData(person);
        //mDataset.add(person);
        //notifyItemInserted(mDataset.size() - 1);
        //notifyItemChanged();

    }


}
