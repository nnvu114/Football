import { NgModule }                   from '@angular/core';
import { RouterModule, Routes }       from '@angular/router';
import { AuthGuard }                  from "./guard/auth.guard";

// sign component
import { SigninComponent }            from './auth/signin.component';
import { SignupComponent }            from './auth/signup.component';

// dashboard component
import { DashboardComponent }         from './dashboard/dashboard.component';
import { UserComponent }              from "./dashboard/user.component";
import { CategoryComponent }          from './dashboard/category/category.component';
import { NewsManagementComponent }    from './dashboard/news-management/news-management.component';
import { CreateNewsComponent } from './dashboard/news-management/create-news/create-news.component';
import { SchedulesComponent } from './dashboard/schedules/schedules.component';
import { ImagesComponent } from './dashboard/images/images.component';
import { UpdateImagesComponent } from './dashboard/images/update-images/update-images.component';

const routes: Routes = [
    { path: 'signin',           component: SigninComponent},
    { path: 'signup',           component: SignupComponent},
    { path: 'home',        canActivate: [AuthGuard], component: DashboardComponent, children: [
        { path: 'category',     component: CategoryComponent},
        { path: 'news',         component: NewsManagementComponent},
        { path: 'news/create',  component: CreateNewsComponent},
        { path: 'news/:id/edit',  component: CreateNewsComponent},
        { path: 'schedule',  component: SchedulesComponent},
        { path: 'images',  component: ImagesComponent},
        { path: 'images/create',  component: UpdateImagesComponent},
        { path: 'images/:id/edit',  component: UpdateImagesComponent},
        { path: '**',           redirectTo: 'category'},
    ]},
    { path: '**',               redirectTo: 'signin'}
];

@NgModule({
    imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}