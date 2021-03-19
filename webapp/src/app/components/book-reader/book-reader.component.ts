import { FormGroup, FormControl } from '@angular/forms';
import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BookPathServicesService } from 'src/app/services/book-path-services.service';
import { ThemePalette } from '@angular/material/core';
import { SharedService } from '../../shared/shared.service';
import { MatDialog } from '@angular/material/dialog';
import { DictionaryComponent } from '../dictionary/dictionary.component';
@Component({
  selector: 'app-book-reader',
  templateUrl: './book-reader.component.html',
  styleUrls: ['./book-reader.component.css'],
})
export class BookReaderComponent implements OnInit {
  constructor(
    private pdfBookService: BookPathServicesService,
    private location: Location,
    private shared: SharedService,
    public dialog: MatDialog
  ) {}

  color: ThemePalette = 'accent';

  userEmail: any;
  pdfMode: boolean = false;
  page: any = 2;
  jumPage: number;
  pdfSrc: any = '';
  isDisabled: boolean = false;
  innerbtn: boolean = false;
  lastpage: number = 107;
  notes: string;
  sent: Boolean = false;
  val: Boolean = false;
  ngOnInit(): void {
    // this.getbookPath();
    this.pdfSrc = '/assets/The-Sign-of-the-Four.pdf';
    this.userEmail = this.shared.getMessage();
    console.log(this.shared.getMessage());
  }

  setPage() {
    if (this.jumPage === null || this.jumPage === undefined) {
      window.alert('fill the page No');
    } else if (this.jumPage >= 0 && this.jumPage <= this.lastpage) {
      this.page = this.jumPage;
    } else {
      window.alert(
        `the page Value mus be less than or equal to ${this.lastpage}`
      );
    }
  }

  pageNext() {
    if (this.page <= this.lastpage) {
      this.page++;
    }
  }

  pagePrev() {
    if (this.page > 0) this.page--;
  }

  onFileSelected() {
    let img: any = document.querySelector('#file');

    if (typeof FileReader !== 'undefined') {
      let reader = new FileReader();
      reader.onload = (e: any) => {
        this.pdfSrc = e.target.result;
      };
      reader.readAsArrayBuffer(img.files[0]);
    }
  }
  getbookPath() {
    this.pdfBookService.getBookPath().subscribe((data) => {
      this.pdfSrc = data;
      console.log(data);
    });
  }
  pdfModeswitch(state: boolean) {
    this.pdfMode = state;
  }
  perviouspage() {
    this.location.back();
  }

  autoTicks = true;
  disabled = false;
  invert = false;
  max = 1.5;
  min = 0;
  showTicks = false;
  step = 0.08;
  thumbLabel = false;
  value = 1;
  vertical = false;
  tickInterval = 1;

  getSliderTickInterval(): number | 'auto' {
    if (this.showTicks) {
      return this.autoTicks ? 'auto' : this.tickInterval;
    }
    return 0;
  }

  sendNote() {
    console.log(this.userEmail, this.page, this.notes);
    let previousRead = {
      bookTitle: 'Call Center',
      bookAuthor: 'Singh Brothers',
      bookDescription: 'Biography ',
      notes: [{ notes: this.notes, page: this.page }],
      lastpage: this.lastpage,
    };
    try {
      if (this.notes.length != 0) {
        this.pdfBookService
          .updateNotes(this.userEmail, previousRead)
          .subscribe((data) => {
            console.log(data);
            this.sent = true;
            this.notes = '';
          });
      }
    } catch (error) {
      this.val = true;
    }
  }

  closedAlert() {
    this.sent = false;
  }
  resetTextarea() {
    this.val = false;
  }
  openDialog(): void {
    const dialogRef = this.dialog.open(DictionaryComponent, {
      // width: '250px'
    });
    dialogRef.afterClosed().subscribe((result) => {
      // console.log('The dialog was closed');
    });
  }
}
