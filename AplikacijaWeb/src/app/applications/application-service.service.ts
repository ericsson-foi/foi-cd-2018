import { Injectable, Inject } from '@angular/core';
//import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';  // debug
import 'rxjs/add/operator/catch';
import {Application} from './domain/application.model';

@Injectable()
export class ApplicationServiceService {
  
  constructor(private http: HttpClient) { }

  
  applicationList:Application[];

  
  
  public getList(): Observable<Application[]> {
    return this.http.get<Application[]>(`http://localhost:8080/application/getAll`);      
  }

  

  addNew(newApplication:Application) {
    this.applicationList.push(newApplication);
    //TODO insert to database
  }

  

  afterEdit(application: Application) {    
  //TODO update in database
  }

}
