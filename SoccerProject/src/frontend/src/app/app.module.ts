import { BrowserModule }              from '@angular/platform-browser';
import { NgModule }                   from '@angular/core';
import { FormsModule, ReactiveFormsModule }  from '@angular/forms';
import { HttpModule }                 from '@angular/http';
import { FileUploadModule }           from 'ng2-file-upload';
import { PaginationModule }           from 'ng2-bootstrap';

// sign component
import { SigninComponent }            from './auth/signin.component';
import { SignupComponent }            from './auth/signup.component';

// dashboard component
import { DashboardComponent }         from './dashboard/dashboard.component';
import { UserComponent }              from "./dashboard/user.component";

// service
import { AuthGuard }                  from "./guard/auth.guard";
import { AppComponent }               from './app.component';
import { AppRoutingModule }           from './app-routing.module';
import { RestDataSource, PagerService, AuthService, Base64Service } from './service/index';
// pipe
import { TruncatePipe }               from "./pipe/truncate";
import { FilterModelPipe }            from "./pipe/filter";

// component
import { CategoryComponent } from './dashboard/category/category.component';
import { NewsManagementComponent } from './dashboard/news-management/news-management.component';
import { CreateNewsComponent } from './dashboard/news-management/create-news/create-news.component';
import { SchedulesComponent } from './dashboard/schedules/schedules.component';


// Ckeditor
import { CKEditorModule } from 'ng2-ckeditor';
import { SafeHtmlPipePipe } from './pipe/safe-html-pipe.pipe';
import { ImagesComponent } from './dashboard/images/images.component';
import { UpdateImagesComponent } from './dashboard/images/update-images/update-images.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SigninComponent,
    SignupComponent,
    UserComponent,
    FilterModelPipe,
    TruncatePipe,
    CategoryComponent,
    NewsManagementComponent,
    CreateNewsComponent,
    SchedulesComponent,
    SafeHtmlPipePipe,
    ImagesComponent,
    UpdateImagesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    FileUploadModule,
    AppRoutingModule,
    ReactiveFormsModule,
    CKEditorModule,
    PaginationModule.forRoot()
  ],
  providers: [
    RestDataSource,
    AuthService,
    Base64Service,
    AuthGuard,
    PagerService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
