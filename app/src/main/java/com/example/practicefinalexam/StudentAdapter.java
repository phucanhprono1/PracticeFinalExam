package com.example.practicefinalexam;

import static android.view.View.inflate;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
    List<Student> list;
    public StudentAdapter(List<Student> list){
        this.list = list;
        notifyDataSetChanged();
    }
    public void setList(List<Student> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new StudentViewHolder(inflate(parent.getContext(),R.layout.item_student,null));
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.name.setText("Tên: "+list.get(position).name);
        holder.year.setText("SV Năm: " +list.get(position).year1);
        holder.que.setText("Quê: "+list.get(position).que);
        holder.dob.setText("Ngày sinh: "+list.get(position).dob);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name,dob,que,year;
        public StudentViewHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.student_name);
            dob = itemView.findViewById(R.id.student_dob);
            que = itemView.findViewById(R.id.student_que);
            year = itemView.findViewById(R.id.student_year);
        }
    }
}
