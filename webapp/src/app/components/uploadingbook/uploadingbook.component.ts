import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
@Component({
  selector: 'app-uploadingbook',
  templateUrl: './uploadingbook.component.html',
  styleUrls: ['./uploadingbook.component.css'],
})
export class UploadingbookComponent implements OnInit {
  reactiveForm: any = FormGroup;
  toppingList: string[] = [
    'Thriller',
    'Tech',
    'Comic',
    'Action',
    'Horror',
    'Romance',
  ];
  constructor(private httpClient: HttpClient, private fb: FormBuilder) {
    this.reactiveForm = this.fb.group({
      bookTitle: new FormControl('', [Validators.required]),
      authorName: new FormControl('', [Validators.required]),
      language: new FormControl('', [Validators.required]),
      bookGenre: new FormControl('', [Validators.required]),
      totalPages: new FormControl('', [Validators.required]),
      readingTimeInMinutes: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      file: new FormControl('', [Validators.required]),
      image: new FormControl('', [Validators.required]),
    });
  }
  message: any;
  selectedFile: File;
  selectedFile1: File;
  imageError: string;
  isImageSaved: boolean;
  cardImageBase64: string;
  ngOnInit(): void {}

  fileChangeEvent(fileInput: any) {
    const image = fileInput.target.files[0];
    this.selectedFile1 = image;

    if (fileInput.target.files && fileInput.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        const image = new Image();
        image.src = e.target.result;
        image.onload = (rs) => {
          const img_height = rs.currentTarget['height'];
          const img_width = rs.currentTarget['width'];
          console.log(img_height, img_width);
          const imgBase64Path = e.target.result;
          this.cardImageBase64 = imgBase64Path;
          this.isImageSaved = true;
          // this.previewImagePath = imgBase64Path;
        };
      };

      reader.readAsDataURL(fileInput.target.files[0]);
    }
  }

  public onFileChanged(event) {
    const file = event.target.files[0];
    this.selectedFile = file;
  }
  get f() {
    return this.reactiveForm.controls;
  }
  uploadbook(submitForm: FormGroup) {
    const item = submitForm.value;
    const uploadFileData = new FormData();

    uploadFileData.append('item', JSON.stringify(item));
    uploadFileData.append('file', this.selectedFile);
    uploadFileData.append('image', this.selectedFile1);
    this.httpClient
      .post('http://localhost:8080/api/v1/book/upload', uploadFileData, {
        observe: 'response',
      })
      .subscribe(
        (response) => {
          console.log(response);
          if (response.status === 201) {
            this.message = ' uploaded successfully';
          } else {
            this.message = ' uploading not successfull';
          }
        },
        (err) => console.log('Error Occured duringng saving: ' + err.message)
      );
  }
}
