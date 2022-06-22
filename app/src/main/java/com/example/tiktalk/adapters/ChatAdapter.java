package com.example.tiktalk.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktalk.R;
import com.example.tiktalk.models.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{

    public static final int SENT_BY_ME = 1;
    public static final int SENT_BY_OTHER = 2;

    class ChatViewHolder extends RecyclerView.ViewHolder{
        private final TextView content;
        private final TextView created;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            this.content = itemView.findViewById(R.id.message_content);
            this.created = itemView.findViewById(R.id.msg_created);
        }
    }

    private final LayoutInflater mInflater;
    private List<Message> chat;

    public ChatAdapter(Context context){ mInflater = LayoutInflater.from(context); }

    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == SENT_BY_ME) {
            itemView = mInflater.inflate(R.layout.sent_by_me_layout, parent, false);
        }
        else {
            itemView = mInflater.inflate(R.layout.sent_by_other_layout, parent, false);
        }
        return new ChatAdapter.ChatViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ChatViewHolder holder, int position) {
        if(chat != null) {
            //final Message current = chat.get(holder.getAdapterPosition());
            final Message current = chat.get(position);
            holder.content.setText(current.getContent());
            holder.created.setText(current.getCreated());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(chat.get(position).isSent()) {
            return SENT_BY_ME;
        }
        return SENT_BY_OTHER;
    }

    public void setChat(List<Message> s) {
        chat = s;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if(chat != null) {
            return chat.size();
        }
        return 0;
    }

    public List<Message> getChat() {
        return chat;
    }
}
