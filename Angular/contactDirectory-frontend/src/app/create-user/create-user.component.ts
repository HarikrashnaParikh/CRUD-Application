import { Component } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {
  user: User = new User();
  
  constructor(private userService: UserService,private router: Router){
  }
  //this method(function) will call userService method createuser with data(user) as parameter
  saveUser(){
    this.userService.createUser(this.user).subscribe(data =>{
      console.log(data);
      this.goToUserList();
    },
    error => console.log(error));
  }

  //Now after saving the new user ,It will route to user list page.
  goToUserList(){
    this.router.navigate(['/users']);
  } 

  //this method will call saveUser method
  onSubmit(){
    this.saveUser();
  }
}
