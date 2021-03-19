import { Component, OnInit, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-view-notes',
  templateUrl: './view-notes.component.html',
  styleUrls: ['./view-notes.component.css'],
})
export class ViewNotesComponent implements OnInit {
  constructor() {}
  public mineData: any;
  ngOnInit(): void {}

  ngAfterViewInit() {
    const ids: any = document.getElementById('accordionExample');
    ids.addEventListener('show.bs.collapse', (e: any) => {
      e.className += 'opened';
      e.target.parentElement.classList.add('opened');
      // console.log(e);
    });
    ids.addEventListener('hide.bs.collapse', (e: any) => {
      e.target.parentElement.classList.remove('opened');
      // console.log(e);
    });
  }
}
