import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id!: number;
  user: User=new User();

  constructor(private userService: UserService,private route: ActivatedRoute,private router: Router){}

  ngOnInit(): void{
    //for getting id from particular Route
    this.id = this.route.snapshot.params['id'];
    this.userService.getUserById(this.id).subscribe(data => (
      this.user = data
    ),error => console.log(error));
  }


  //this method will call saveUser method
  onSubmit(){
    this.userService.updateUser(this.id,this.user).subscribe(data => {
      this.goToUserList();
    },
    error=>console.error(error));
  }
  
  //Now after saving the new user ,It will route to user list page.
  goToUserList(){
    this.router.navigate(['/users']);
  } 

}
