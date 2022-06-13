package com.example.tiktalk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktalk.Message;
import com.example.tiktalk.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    public static final int SENT_BY_ME = 1;
    public static final int SENT_BY_OTHER = 2;


    class ChatViewHolder extends RecyclerView.ViewHolder {
        private final TextView content;
        private final TextView created;
        private boolean sent;


        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.content = itemView.findViewById(R.id.);
            this.lastMsg = itemView.findViewById(R.id.lastMsg);
            this.date = itemView.findViewById(R.id.lastMsgTime);
            this.sent = true;
        }
    }

    private final LayoutInflater mInflater;
    private List<Message> chat;

    public ChatAdapter(Context context){ mInflater = LayoutInflater.from(context); }

    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == SENT_BY_ME) {
            itemView = mInflater.inflate(R.layout.activity_chat, parent, false); //todo sentByME view
        }
        else {
            itemView = mInflater.inflate(R.layout.activity_chat, parent, false); //todo sentByOTHER view
        }
        return new ChatAdapter.ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ChatViewHolder holder, int position) {
        if(chat != null) {
            final Message current = chat.get(position);
            holder.contactNickname.setText(current.getContent());
            holder.lastMsg.setText(current.getCreated());
        }
    }

    public void setContacts(List<Message> s) {
        chat = s;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(chat != null) {
            return chat.size();
        }
        return 0;
    }

    public List<Message> setContacts() {
        return chat;
    }

}
