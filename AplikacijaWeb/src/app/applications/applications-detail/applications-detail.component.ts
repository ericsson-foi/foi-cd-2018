import { Component, OnInit, Input } from '@angular/core';
import { Application } from '../domain/application.model';

@Component({
  selector: 'app-applications-detail',
  templateUrl: './applications-detail.component.html',
  styleUrls: ['./applications-detail.component.css']
})
export class ApplicationsDetailComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  @Input() application: Application;

}
