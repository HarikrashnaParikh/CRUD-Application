import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL= "http://localhost:8080/api/users";

  constructor(private httpClient: HttpClient) { }

  //connecting to getusers api 
  getUsersList() : Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}`);
  }
  //string template `${}` this is used for to write directly after that. for ex.`${this.baseURL}/1`
  
  //cannecting to post createuser api
  createUser(user: User) : Observable<any>{
    return this.httpClient.post(`${this.baseURL}`, user);
  }     

  //17. Connecting angular with get user rest api
  getUserById(id: number):Observable<User>{
    return this.httpClient.get<User>(`${this.baseURL}/${id}`);
  }

  //18.Connecting angular with update user rest api
  updateUser(id: number,user: User) : Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, user);
  }

  //20.connecting angular with delete user rest api
  deleteUser(id: number) : Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }


}
