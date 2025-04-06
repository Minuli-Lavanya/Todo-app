import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface Task {
  id: number;  // Add this because backend generates it
  title: string;
  description: string;
  completed: boolean;
}

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [FormsModule,CommonModule,HttpClientModule ],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent implements OnInit {
  
  tasks: Task[] = [];
  newTask: Partial<Task> = {title: '', description: '', completed: false };

  private apiUrl = 'http://localhost:8090/api/tasks'; //backend URL

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadTasks();
  }

  loadTasks() {
    this.http.get<Task[]>(`${this.apiUrl}/all`).subscribe(data => this.tasks = data);
    // console.log(this.tasks)   
  }

  addTask() { 
    this.http.post<Task>(`${this.apiUrl}/create`, this.newTask)
    .subscribe(() => {
      this.loadTasks();
      this.newTask = {title: '', description: '', completed: false };
    });
  }

  completeTask(id: number) {
    this.http.put(`${this.apiUrl}/${id}/complete`, {}).subscribe(() => {
      this.loadTasks();
    });
  }

}
