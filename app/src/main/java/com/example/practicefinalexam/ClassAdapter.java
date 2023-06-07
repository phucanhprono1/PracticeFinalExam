package com.example.practicefinalexam;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{
    private List<CreditClass> list;
    private ClassClickListener clickListener;
    interface ClassClickListener{
        void onClassClick(CreditClass creditClass);
    }
    public ClassAdapter(List<CreditClass> list,ClassClickListener clickListener){
        this.list = list;
        this.clickListener = clickListener;
    }
    public void setList(List<CreditClass> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ClassAdapter.ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassViewHolder(View.inflate(parent.getContext(),R.layout.item_class,null));
    }

    @Override
    public void onBindViewHolder(ClassAdapter.ClassViewHolder holder, int position) {
        holder.name.setText(list.get(position).name);
        holder.description.setText(list.get(position).description);
        holder.number.setText("Số sinh viên: "+list.get(position).stuNumber);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clickListener.onClassClick(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView name,description,number;

        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.description = itemView.findViewById(R.id.description);
            this.number = itemView.findViewById(R.id.stu_number1);
        }
    }
}
