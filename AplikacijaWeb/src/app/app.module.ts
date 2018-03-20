import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';


import { AppComponent } from './app.component';
import { DropdownDirective } from './shared/dropdown.directive';
import { ApplicationsComponent } from './applications/applications.component';
import { ApplicationsDetailComponent } from './applications/applications-detail/applications-detail.component';
import { ApplicationServiceService } from './applications/application-service.service';
import { HttpClientModule, HttpClient } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    DropdownDirective,
    ApplicationsComponent,
    ApplicationsDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [ApplicationServiceService,HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
