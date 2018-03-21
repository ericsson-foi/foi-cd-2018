import { Component, OnInit, Input } from '@angular/core';
import { Application } from './domain/application.model';
import { ApplicationServiceService } from './application-service.service';

@Component({
  selector: 'app-applications',
  templateUrl: './applications.component.html',
  styleUrls: ['./applications.component.css']
})
export class ApplicationsComponent implements OnInit {

  detailOpened = false;
  isEdit = false;

  applications: Application[] = [];

  application: Application = new Application('name', 'email', 'position', 'company');

  constructor(private applicationService: ApplicationServiceService) { }

  ngOnInit() {    
    this.applicationService.getList().subscribe(
      data => {this.applications = data},
        error => () => {},
      () => {}
      );
  }

  getClass() {
    if (this.detailOpened) {
      return 'modal show';
    } else {
      return 'modal fade';
    }

  }

  close() {
    this.detailOpened= false;
  }

  onAddItem() {
    this.application = new Application('name', 'email', 'position', 'company1');
    this.detailOpened = true;  
    this.isEdit = false;  
  }

  save() {
    console.log(this.application);
    if (!this.isEdit) {
      this.applications.push(this.application);
      //todo insert to database
    } else {
      //TODO edit to database
    }
    this.detailOpened = false; 


  }

  edit(application: Application) {
    this.isEdit = true;
    this.application = application;
    this.detailOpened = true;
    console.log(application);
  }

  remove(rowNumber: number) {
    console.log(rowNumber);
    this.applications.splice(rowNumber, 1);
    //todo remove from database

  }

}
