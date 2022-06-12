package com.example.tiktalk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktalk.Contact;
import com.example.tiktalk.R;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>{

class ContactViewHolder extends RecyclerView.ViewHolder {
    private final TextView contactNickname;
    private final TextView lastMsg;
    private final TextView date;
    //private final ImageView profilePic;


    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        this.contactNickname = itemView.findViewById(R.id.contactNickname);
        this.lastMsg = itemView.findViewById(R.id.lastMsg);
        this.date = itemView.findViewById(R.id.lastMsgTime);

        //this.profilePic = profilePic;
    }
}

    private final LayoutInflater mInflater;
    private List<Contact> contacts;

    public ContactListAdapter(Context context){ mInflater = LayoutInflater.from(context); }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if(contacts != null) {
            final Contact current = contacts.get(position);
            holder.contactNickname.setText(current.getName());
            holder.lastMsg.setText(current.getLast());
            holder.date.setText(current.getLastDate());
        }
    }

    public void setContacts(List<Contact> s) {
        contacts = s;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(contacts != null) {
            return contacts.size();
        }
        return 0;
    }

    public List<Contact> setContacts() {
        return contacts;
    }
}