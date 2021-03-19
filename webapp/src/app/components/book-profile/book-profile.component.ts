import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { title } from 'process';
import { BookPathServicesService } from 'src/app/services/book-path-services.service';
import { BookProfileServiceService } from 'src/app/services/book-profile-service.service';
import { DashboardService } from 'src/app/services/dashboard.service';
import { LoginService } from 'src/app/services/login.service';
import { DashboardComponent } from '../dashboard/dashboard.component';

@Component({
  selector: 'app-book-profile',
  templateUrl: './book-profile.component.html',
  styleUrls: ['./book-profile.component.css'],
})
export class BookProfileComponent implements OnInit {
  constructor(
    private router: Router,
    private bookpathservicesservice: BookPathServicesService,
    private service: BookProfileServiceService,
    private title: DashboardService,
    private loginservice: LoginService
  ) {}

  bookTitle = this.title.booktitle;
  authorName: any;
  language: any;
  bookGenre: any;
  description: any;
  uploadedOn: any;
  coverimage: any;

  details: any;

  email = this.loginservice.useremail;

  // getbookPath() {
  //   this.bookpathservicesservice.getBookPath().subscribe((data) => {
  //     this.details = data;

  //   });
  // }
  getbookdetails() {
    this.service.getbookdetails(this.bookTitle).subscribe((data) => {
      this.details = data;
      console.log(data);
    });
  }
  ngOnInit(): void {
    // this.getbookPath();
    this.getbookdetails();
    console.log(this.email);
  }
  toBookProfile() {
    this.router.navigate(['/bookview']);
  }
}
