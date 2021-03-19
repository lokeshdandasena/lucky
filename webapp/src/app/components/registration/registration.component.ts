import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  private user;
  hide = true;
  signupForm: any = FormGroup;
  mobNumberPattern = '^((\\+91-?)|0)?[0-9]{10}$';
  emailPattern = '^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$';
  passwordPattern =
    '(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-zd$@$!%*?&].{8,}';

  constructor(
    private httpClient: HttpClient,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.signupForm = this.fb.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      phone: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required, Validators.min(3)]),
    });
  }

  title = 'ImageUploaderFrontEnd';

  public selectedFile;
  public event1;
  imgURL: any;

  public onFileChanged(event) {
    console.log(event);

    const file = event.target.files[0]; //takes only one file
    this.selectedFile = file;
    // Below part is used to display the selected image
    let reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]); // read the binary data and encode it as base64 data url
    reader.onload = (event2) => {
      this.imgURL = reader.result;
    };
  }

  get f() {
    return this.signupForm.controls;
  }

  signup(submitForm: FormGroup) {
    const uploadData = new FormData();
    const item = this.signupForm.value;
    uploadData.append('item', JSON.stringify(item));
    uploadData.append('myfile', this.selectedFile);
    console.log(item);
    console.log(this.selectedFile);

    this.httpClient
      .post('http://localhost:8082/api/v1/upload', uploadData, {
        observe: 'response',
      })
      .subscribe(
        (response) => {
          console.log(response);
          this.signupForm.reset();
          this.router.navigate(['/login']);
        },
        (err) => console.log('Error Occured duringng saving: ' + err)
      );
  }

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required]),
      phone: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      // Userrole:this.Userrole
    });
  }
}
