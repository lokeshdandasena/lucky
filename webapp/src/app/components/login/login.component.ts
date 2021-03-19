import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { timer } from 'rxjs';
import { animate, style, transition, trigger } from '@angular/animations';

import { SharedService } from '../../shared/shared.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [
    trigger('moveup', [
      transition(':leave', [
        animate(200, style({ transform: 'translateY(-70px)' })),
      ]),
    ]),
  ],
})
export class LoginComponent implements OnInit {
  constructor(
    private loginService: LoginService,
    private router: Router,
    private shared: SharedService
  ) {}
  showAlert: boolean = false;
  showPassword: boolean = false;

  userEmail: any;

  emailRegEx = '^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$';

  loginForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.pattern(this.emailRegEx),
    ]),
    password: new FormControl('', Validators.required),
  });
  
  login() {
  this.loginService.useremail=this.loginForm.value.email;
  console.log(this.loginService.useremail);
    this.userEmail = this.loginForm.value.email;
  
    this.loginService.login(this.loginForm.value).subscribe(
      (data: any) => {
        if (!data.token || !data.role) {
        } else {
          this.getEmailID();
          this.router.navigate(['/dashboard']);
        }
      },
      (error) => {
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
        }, 1500);
        console.log(error.error);
      }
    );
  
  }

  getEmailID() {
    console.log(this.userEmail);
    this.shared.setMessage(this.userEmail);
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }
  ngOnInit(): void {}
}
