import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DashboardService } from 'src/app/services/dashboard.service';
import { SearchServiceService } from 'src/app/services/search-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  constructor(
    private service: SearchServiceService,
    private dashboardService: DashboardService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllBooks();
  }

  public searchData: any;

  public datas: any;
  public books: any;
  public thrillerBooks: any;
  public techBooks: any;
  public actionBooks: any;
  public horrorBooks: any;
  public comicBooks: any;
  public romanceBooks: any;

  getSearchData() {
    this.service.getSearchedData(this.searchData).subscribe((data) => {
      this.datas = data;
      console.log(data);
    });
  }

  getAllBooks() {
    this.dashboardService.getAllBooks().subscribe((data) => {
      this.books = data;
      this.thrillerBooks = this.books.filter((doc) =>
        doc.bookGenre.includes('Thriller')
      );
      this.techBooks = this.books.filter((doc) =>
        doc.bookGenre.includes('Tech')
      );
      this.actionBooks = this.books.filter((doc) =>
        doc.bookGenre.includes('Action')
      );
      this.horrorBooks = this.books.filter((doc) =>
        doc.bookGenre.includes('Horror')
      );
      this.comicBooks = this.books.filter((doc) =>
        doc.bookGenre.includes('Comic')
      );
      this.romanceBooks = this.books.filter((doc) =>
        doc.bookGenre.includes('Romance')
      );

      // console.log(this.romanceBooks);
    });
  }

  toBookProfile(title: string) {
    this.dashboardService.booktitle = title;
    console.log(this.dashboardService.booktitle);
    this.router.navigate(['/bookprofile'], { state: { bookTitle: title } });
  }
}
